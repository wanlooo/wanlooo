package com.cn.xxx.web.controller.yhss;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.xxx.commons.controller.BaseController;
import com.cn.xxx.commons.util.JsonUtil;
import com.cn.xxx.commons.util.JsonUtils;
import com.cn.xxx.yhsscore.model.UserDO;
import com.cn.xxx.yhsscore.model.WechatUserBindDO;
import com.cn.xxx.yhsscore.service.UserService;
import com.cn.xxx.yhsscore.service.WechatUserBindService;
import com.cn.xxx.yhsscore.vo.resp.LoginVO;
import com.cn.xxx.yhsscore.vo.resp.RegistVO;

@Controller
@Scope("prototype")
@RequestMapping("/bind")
public class UserBindController extends BaseController{
	private Logger LOGGER = LoggerFactory.getLogger(UserBindController.class);
	
	@Autowired
	private WechatUserBindService wechatUserBindService ;
	@Autowired
	private UserService userService ;
	
	@ResponseBody
	@RequestMapping(value="/regist",method=RequestMethod.POST)
	public Map<String,Object> regist(@RequestBody RegistVO regist) throws Exception{
		//数据有效性校验
		validateRegist(regist);
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("success", true);
		String openid = (String) this.request.getSession().getAttribute("openid");
//		String openid = "oEybKwtejlI4Ng9fUBc6NrGkO_E8";
		UserDO userInfo = this.wechatUserBindService.doRegist(regist, openid);
		if (userInfo == null) {
			result.put("success", false);
			result.put("reason", "新增用户失败");
		}else {
			request.getSession().setAttribute("user", userInfo);
		}
		return result ;
	}
	private void validateRegist(RegistVO regist) throws Exception{
		if(regist.getUser()==null){
			throw new Exception("用户信息缺失");
		}
		if(regist.getUser().getSchool()==null){
			throw new Exception("用户学校信息缺失");
		}
		LOGGER.info(JsonUtil.writeValueAsString(regist));
		LOGGER.info("security-size>>>"+regist.getUser().getSecurity().size());
		if(regist.getUser().getSecurity()==null || regist.getUser().getSecurity().size() != 3){
			throw new Exception("用户密保问题有误");
		}
	}
	@ResponseBody
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public Map<String,Object> login(@RequestBody LoginVO login) throws Exception{
		//数据有效性校验
		validateLogin(login);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		String openid = (String) this.request.getSession().getAttribute("openid");
//		String openid = "oEybKwtejlI4Ng9fUBc6NrGkO_E8";
		this.wechatUserBindService.doBind(login, openid);
		return result;
	}
	private void validateLogin(LoginVO login) throws Exception{
		if(login.getLoginName()==null || login.getPassword()==null){
			throw new Exception("登录信息不完整");
		}
	} 
	@ResponseBody
	@RequestMapping(value="/isLogin",method=RequestMethod.GET)
	public Map<String,Object> isLogin() throws Exception{
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("success", true);
		String openid = (String) this.request.getSession().getAttribute("openid");
		UserDO user = (UserDO) this.request.getSession().getAttribute("user");
		if(openid == null){
			result.put("success", false);
		}else if(user == null){
			WechatUserBindDO wub = this.wechatUserBindService.getWechatUserBindDOAllByOpenid(openid);
			if(wub == null){
				result.put("success", false);
			}else{
				this.request.getSession().setAttribute("user", wub.getUser());
			}
		}
		
		return result ;
	}
	
	@ResponseBody
	@RequestMapping(value="/checkUserName",method=RequestMethod.GET)
	public Map<String,Object> checkUserName(String userName) throws Exception{
		Map<String,Object> result = new HashMap<String, Object>();
		boolean exists = this.userService.userNameExists(userName);
		result.put("success", !exists);
		return result ;
	}
	
	@ResponseBody
	@RequestMapping(value="/queryUser",method=RequestMethod.GET)
	public UserDO queryUser(String userName) throws Exception{
		UserDO user = new UserDO();
		user=this.userService.doFindUserByUserName(userName);
		return user ;
	}
}
