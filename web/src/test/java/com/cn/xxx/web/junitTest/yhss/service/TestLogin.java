package com.cn.xxx.web.junitTest.yhss.service;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.cn.xxx.web.junitTest.JunitContext;
import com.cn.xxx.yhsscore.model.SchoolDO;
import com.cn.xxx.yhsscore.model.SecretSecurityDO;
import com.cn.xxx.yhsscore.model.UserDO;
import com.cn.xxx.yhsscore.model.WechatUserBindDO;
import com.cn.xxx.yhsscore.service.WechatUserBindService;
import com.cn.xxx.yhsscore.vo.resp.LoginVO;
import com.cn.xxx.yhsscore.vo.resp.RegistVO;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestLogin extends JunitContext {

	private ObjectMapper om = new ObjectMapper();
	private WechatUserBindService  wechatUserBindService ;
	
	@Before
	public void prepare() {
		wechatUserBindService = (WechatUserBindService) ctx.getBean("wechatUserBindServiceImpl");
	}
	
	@Test
	public void testLogin() throws Exception{
		LoginVO login= new LoginVO();
		login.setLoginName("calchen");
		login.setPassword("123456");
		String openid = "oEybKwtejlI4Ng9fUBc6NrGkO_E8";
		
		WechatUserBindDO wub = this.wechatUserBindService.doBind(login, openid);
		System.out.println(wub);
	}
	@Test
	public void testRegist() throws Exception{
		RegistVO regist= new RegistVO();
		UserDO user = new UserDO();
		user.setUserName("calchen");
		user.setPassword("123456");
		user.setPhoneNo("18217296042");
		
		SchoolDO s = new SchoolDO();
		s.setCollege("信管学院");
		s.setSchoolname("江西财大");
		user.setSchool(s);
		
		Set<SecretSecurityDO> ss= new HashSet<SecretSecurityDO>();
		for(int i=0;i<3;i++){
			SecretSecurityDO ss1 = new SecretSecurityDO();
			ss1.setSecurityTitle("在哪？");
			ss1.setSecurityAnswer("上海");
			ss.add(ss1) ;
		}
		
		user.setSecurity(ss);
//		regist.setUser(user);
		
		String openid = "oEybKwtejlI4Ng9fUBc6NrGkO_E8";
		
		UserDO u = this.wechatUserBindService.doRegist(regist, openid);
		System.out.println(u);
	}
	
}
