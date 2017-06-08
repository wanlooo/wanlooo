package com.cn.xxx.web.controller.yhss;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.xxx.wxcore.util.AdvancedUtil;
import com.cn.xxx.wxcore.vo.WeixinOauth2;
import com.cn.xxx.yhsscore.model.WechatUserBindDO;
import com.cn.xxx.yhsscore.service.WechatUserBindService;

/** 
 * @ClassName: EntryController 
 *  
 */
@Controller
@Scope("prototype")
@RequestMapping("/wechat/entry")
public class EntryController {

	Logger log = LoggerFactory.getLogger(EntryController.class);
	
	@Autowired
	private WechatUserBindService wechatUserBindService;
	/*不绑定可跳转页面*/
	private static Map<String,String> loginIgnoreStateUrl = new HashMap<String,String>();
	/*必须先绑定才可跳转页面*/
	private static Map<String,String> stateUrl = new HashMap<String,String>();
	
	static{//
		loginIgnoreStateUrl.put("msHome", "/yhssui/miaosha.html");
		
		stateUrl.put("login", "/yhssui/login.html");
		stateUrl.put("hyuserinfo", "/yhssui/userInfo.html");
		
	}
	
	/**
	 * 微信跳转控制主入口
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping(value="", method=RequestMethod.GET)
	public void freeTripEntry(HttpServletRequest request, HttpServletResponse response, 
			                            @RequestParam(value="code", required = true) String code, 
			                            @RequestParam(value="state", required = true) String state) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
        StringBuilder builder = new StringBuilder();  
        builder.append("<script type=\"text/javascript\">");
        WeixinOauth2 wo= AdvancedUtil.getOauth2(code);
        //String openId = "oAAukuKgEMhXsTI3GZMjl7CHL6oU";
		if(wo == null || wo.getOpenid() == null){
            builder.append("alert('获取微信信息失败!');");
            builder.append("</script>");
            out.print(builder.toString());
            return ;
		}
		request.getSession().setAttribute("openid", wo.getOpenid());
		
		String nextPage = ""; 
		if(loginIgnoreStateUrl.containsKey(state)){//不需要绑定记录的页面
			nextPage = request.getRequestURL().toString().replace(request.getRequestURI().toString(), "") + loginIgnoreStateUrl.get(state); 
		}else if(stateUrl.containsKey(state)){//需要绑定记录的页面且map中配置了路径
			WechatUserBindDO userBind = this.wechatUserBindService.getWechatUserBindDOAllByOpenid(wo.getOpenid());
			if(userBind == null ){//无绑定记录
				nextPage = request.getRequestURL().toString().replace(request.getRequestURI().toString(), "") + stateUrl.get("login") +"?tag="+state; 
			}else{
				request.getSession().setAttribute("user", userBind.getUser());
				nextPage = request.getRequestURL().toString().replace(request.getRequestURI().toString(), "") + stateUrl.get(state);  
			}
		}else{ 
			builder.append("alert('错误入口！');");
	        builder.append("</script>");
	        out.print(builder.toString());
	        return;
		}
		
		log.info("[loginPage:"+nextPage + " openId:"+wo.getOpenid());
		builder.append("window.top.location.href='"+nextPage+"';");
        builder.append("</script>");
        out.print(builder.toString());
	}
	
	
}
