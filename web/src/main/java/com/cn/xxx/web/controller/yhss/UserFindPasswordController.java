package com.cn.xxx.web.controller.yhss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.xxx.commons.controller.BaseController;
import com.cn.xxx.yhsscore.model.SecretSecurityDO;
import com.cn.xxx.yhsscore.model.UserDO;
import com.cn.xxx.yhsscore.service.UserService;
import com.cn.xxx.yhsscore.service.WechatUserBindService;
import com.cn.xxx.yhsscore.vo.resp.CheckSecurityVO;

@Controller
@Scope("prototype")
@RequestMapping("/user")
public class UserFindPasswordController extends BaseController{
	
	@Autowired
	private WechatUserBindService wechatUserBindService ;
	@Autowired
	private UserService userService ;
	
	@ResponseBody
	@RequestMapping(value="/resetPassword",method=RequestMethod.POST)
	public Map<String,Object> resetPassword(@RequestBody UserDO user) throws Exception{
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("success", true);
		boolean ret = this.userService.resetPassword(user);
		if(!ret){
			result.put("success", false);
			result.put("reason", "用户名不存在");
		}
		return result ;
	}
	
	@ResponseBody
	@RequestMapping(value="/checkSecurity",method=RequestMethod.POST)
	public Map<String,Object> checkSecurity(@RequestBody CheckSecurityVO checkSecurity) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		int metch = 0 ;
		UserDO user = this.userService.findUserByLoginName(checkSecurity.getUserName());
		if(user!=null){
			Set<SecretSecurityDO> security = user.getSecurity();
			for(SecretSecurityDO ss:security){
				for(SecretSecurityDO sc:checkSecurity.getSecurity()){
					if(ss.getSecurityTitle().equals(sc.getSecurityTitle())){
						metch++;
						if(!ss.getSecurityAnswer().equals(sc.getSecurityAnswer())){
							result.put("success", false);
							result.put("reason", "问题（"+sc.getSecurityTitle()+"）答案有误 ");
							return result ;
						}
					}
				}
			}
			if(metch != 3){
				result.put("success", false);
				result.put("reason", "密保问题对应有误，请及时联系网站管理人员 ");
			}
		}else{
			result.put("success", false);
			result.put("reason", "用户名不存在");
		}
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/getSecurityList",method=RequestMethod.GET)
	public Map<String,Object> isLogin(@RequestParam(required=true)String userName) throws Exception{
		Map<String,Object> result = new HashMap<String, Object>();
		List<String> securityTitles = new ArrayList<String>();
		result.put("success", true);
		UserDO user = this.userService.findUserByLoginName(userName);
		if(user!=null){
			Set<SecretSecurityDO> security = user.getSecurity();
			for(SecretSecurityDO ss:security){
				securityTitles.add(ss.getSecurityTitle());
			}
			result.put("securityTitles", securityTitles);
		}else{
			result.put("success", false);
			result.put("reason", "用户名不存在");
		}
		return result ;
	}
}
