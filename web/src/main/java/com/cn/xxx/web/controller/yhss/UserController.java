package com.cn.xxx.web.controller.yhss;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.xxx.web.factory.UserFactory;
import com.cn.xxx.web.vo.response.UserVO;
import com.cn.xxx.yhsscore.model.UserDO;
import com.cn.xxx.yhsscore.service.UserService;

/**
 * @author chenshuibin
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserFactory userFactory;
	
	@ResponseBody
	@RequestMapping(value="/editPassword",method=RequestMethod.GET)
	public Map<String,Object> editPassword(@RequestParam(required=true) String password) throws Exception{
		boolean ret = this.userService.editPassword(password);
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("success", ret);
		return result ;
	}
	@ResponseBody
	@RequestMapping(value="/getUserInfo",method=RequestMethod.GET)
	public Map<String,Object> getUserInfo() throws Exception{
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("success", true);
		UserDO userDO = this.userService.getCacheUser();
//		UserVO userVO = this.userFactory.convertUserDO2UserVO(userDO);
		result.put("data", userDO);
		return result ;
	}
	

}
