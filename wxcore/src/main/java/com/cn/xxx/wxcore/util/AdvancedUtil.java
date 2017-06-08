package com.cn.xxx.wxcore.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cn.xxx.wxcore.exception.WechatErrorException;
import com.cn.xxx.wxcore.thread.TokenThread;
import com.cn.xxx.wxcore.vo.QrcodeVO;
import com.cn.xxx.wxcore.vo.Token;
import com.cn.xxx.wxcore.vo.WechatError;
import com.cn.xxx.wxcore.vo.WeixinOauth2;
import com.cn.xxx.wxcore.vo.WeixinUserInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 微信接口工具类
 * 关于定义参数的说明： 
 * 1：ACCESS_TOKEN格式不能换成其他的
 * 2.微信接口url:private static String 定义为static数据前提为不能用String.replace方法替换动态数据
 * 
 */
public class AdvancedUtil {
	private static Logger log = LoggerFactory.getLogger(AdvancedUtil.class);
//	private static Gson gson = new Gson();
	private static ObjectMapper mapper = new ObjectMapper();
	
	private static String appid = "wx27f6f3d259049ed7";// 第三方用户唯一凭证
	private static String appsecret = "926237044f4be2a675b4f6340da58659";// 第三方用户唯一凭证密钥
	
	static{
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	//获取token
	private static String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	public static Token getToken(){
		Token token = null;
		String requestUrl = token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
		
		try {
			Map<String,Object> ret = RestUtil.doGet(requestUrl);
			if((Boolean)ret.get("success")){
				String json = (String)ret.get("data") ;
				if(json.contains("errcode")){
					WechatError e = mapper.readValue((String) ret.get("data"), WechatError.class);
					log.error(e.toString());
				}else{
					token =  mapper.readValue((String)ret.get("data"), Token.class);
					log.info("access_token:"+token.getAccess_token());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return token ;
	}
	//token失效时重新获取token
	public static Token reGetToken(){
		Token token = getToken();
		if(null != token){
			TokenThread.token = token ;
		}
		return token ;
	}
	//微信高级接口请求-含token失效重复获取机制-泛型
	@SuppressWarnings("unchecked")
	public static <T> T doWechatAdvanced(String requestUrl, String method,String inString,Class<T> c){
		log.info("doWechat begin");
		T t = null;
		String request_url = requestUrl ;
		if(requestUrl.contains("ACCESS_TOKEN")){
			request_url = requestUrl.replace("ACCESS_TOKEN", TokenThread.token.getAccess_token()) ;
		}
		try {
			t = doRest(request_url, method, inString, c);
		} catch (WechatErrorException e) {
			if(e.getErrcode().equals("40001") && requestUrl.contains("ACCESS_TOKEN")){//token重新请求及请求重发机制
				Token token = reGetToken();
				request_url = requestUrl.replace("ACCESS_TOKEN", token.getAccess_token());
				try {
					t = doRest(request_url, method, inString, c);
				} catch (WechatErrorException e1) {
					if(t instanceof WechatError){//进入的参数为WechatError
						t = (T) new WechatError(e1.getErrcode(),e1.getErrmsg());
					}
				}
			}else if(t instanceof WechatError){//进入的参数为WechatError
					t = (T) new WechatError(e.getErrcode(),e.getErrmsg());
			}
		}
		return t ;
	}
	// https统一请求-泛型
	private static <T> T doRest(String requestUrl, String method,String inString, Class<T> c) throws WechatErrorException  {
		log.info("doRest begin");
		if(requestUrl.contains("ACCESS_TOKEN")){
			requestUrl = requestUrl.replace("ACCESS_TOKEN", TokenThread.token.getAccess_token()) ;
		}
		T t = null;
		Map<String, Object> ret = new HashMap<String, Object>();
		try {
			if ("GET".equals(method)) {
				ret = RestUtil.doGet(requestUrl);
			} else if ("POST".equals(method)) {
				ret = RestUtil.doPost(requestUrl, inString);
			} else {
				return null;
			}
			if ((Boolean) ret.get("success")) {
				String json = (String) ret.get("data");
				if (json.contains("errcode")) {
//					WechatError e = gson.fromJson((String) ret.get("data"),WechatError.class);
					WechatError e = mapper.readValue((String) ret.get("data"),WechatError.class);
					
//					log.error(e.toString());
					throw new WechatErrorException(e.getErrcode(), e.getErrmsg());
				} else {
					t = mapper.readValue((String) ret.get("data"), c);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
	
	
	//获取用户信息
	public static WeixinUserInfo getUserInfo(String openId){
		WeixinUserInfo weixinUserInfo = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
		requestUrl = requestUrl.replace("OPENID", openId);
		// 获取用户信息
		weixinUserInfo = doWechatAdvanced(requestUrl, "GET", null, WeixinUserInfo.class);
		return weixinUserInfo;
	}
	
	//创建临时二维码
	private static String qrcode_url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
	public static QrcodeVO createQrcode(){
		QrcodeVO qrcode = null ;
		//临时二维码有效期5分钟（300秒），场景值ID为当前秒数
		String scene_id = System.currentTimeMillis()/1000 + "";
		String inString = "{\"expire_seconds\":300,\"action_name\":\"QR_SCENE\",\"action_info\":{\"scene\":{\"scene_id\":"+scene_id+"}}}";
		qrcode = doWechatAdvanced(qrcode_url, "POST", inString, QrcodeVO.class);
		if(qrcode != null){
			qrcode.setScene_id(scene_id);
		}
		return qrcode ;
	}

	//授权链接获取用户openid -- scope为snsapi_base
	public static WeixinOauth2 getOauth2(String code) {
		log.info(code);
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		WeixinOauth2 wa = null;
		requestUrl = requestUrl.replace("APPID", appid).replace("SECRET", appsecret).replace("CODE", code);
		wa = doWechatAdvanced(requestUrl, "GET", null, WeixinOauth2.class);
		return wa;
	}
	
	//客服接口-发消息
	private static String custom_send_url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
	//文本类型：text
	public static boolean sendCustomTextMessage(String openid,String message) throws UnsupportedEncodingException{
		message = new String(message.getBytes(),"UTF-8");
		String inString = "{\"touser\":\"OPENID\",\"msgtype\":\"text\",\"text\":{\"content\":\"TEXT\"}}";
		inString = inString.replace("OPENID", openid).replace("TEXT", message);
		WechatError we = doWechatAdvanced(custom_send_url, "POST", inString, WechatError.class);
		return we!=null && we.getErrcode().equals("0") ? true:false ;
	}
	
	
	public static void main(String args[]) {
//		String accessToken = "o8-HFwHsYG0jhHAEJr01eWCpWC6AOe5k4E4eMtYpXmRStBJhxvw9EYVkSoRHTrKHsT8Hk2rrpyNw4eUzQLtPJH8ajvfRNup9_Bepc9xVN4cKCCeAGAVNN";
		System.out.println(System.currentTimeMillis()/1000);
	}
}
