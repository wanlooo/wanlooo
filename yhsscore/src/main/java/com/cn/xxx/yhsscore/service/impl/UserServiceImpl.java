package com.cn.xxx.yhsscore.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.xxx.commons.service.CacheService;
import com.cn.xxx.commons.util.JsonUtils;
import com.cn.xxx.yhsscore.dao.UserDao;
import com.cn.xxx.yhsscore.model.UserDO;
import com.cn.xxx.yhsscore.service.UserService;
import com.cn.xxx.yhsscore.service.WechatUserService;

/**
 * @author xulong
 */
@Service(value="userService")
public class UserServiceImpl implements UserService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired  
	protected UserDao userDao;
	@Autowired 
	private WechatUserService wechatUserService ;
	@Autowired
	private CacheService request ;
	
	@Override
	public Map<String,Object> doRegisterUser(UserDO user) throws Exception {
		return null ;	
	}

	@Override
	public UserDO findUserByPhoneNo(String phoneno) {
		return userDao.searchUserByPhoneNo(phoneno);
	}

	@Override
	public UserDO findUserByNickName(String nick_name) {
		// TODO Auto-generated method stub
		return userDao.searchUserByNickName(nick_name);
	}


	@Override
	public boolean userNameExists(String userName) {
		return this.userDao.queryUserDOByUserNameOrPhoneNo(userName) == null ? false : true;
	}

	@Override
	public UserDO findUserByLoginName(String loginName) {
		return this.userDao.queryUserDOByUserNameOrPhoneNo(loginName);
	}

	@Override
	public boolean resetPassword(UserDO user) {
		UserDO userDO = this.userDao.queryUserDOByUserNameOrPhoneNo(user.getUserName());
		if(userDO != null){
			userDO.setPassword(user.getPassword());
			this.userDao.saveOrUpdate(userDO);
		}else{
			return false ;
		}
		return true;
	}

	@Override
	public boolean editPassword(String password) throws Exception {
		UserDO userDO = this.getCacheUser();
		if(userDO != null){
			userDO.setPassword(password);
			this.userDao.saveOrUpdate(userDO);
		}else{
			return false ;
		}
		return true;
	}
	@Override
	public UserDO doFindUserByUserName(String userName) {
		return this.userDao.queryUserByUserName(userName);
	}

	@Override
	public UserDO getCacheUser() throws Exception {
		UserDO user = request.getObject("user");
		LOGGER.info("session中用户为：》》》"+JsonUtils.objectToJson(user));
		if(user == null){
			user = this.userDao.queryObjectById(UserDO.class, 2L);
//			throw new Exception("无法获取网站用户信息，请尝试重新进入该页面");
		}else{
			user = this.userDao.queryObjectById(UserDO.class, user.getId());
		}
		return user;
	}
	@Override
	public UserDO getCacheOnlyUser() throws Exception {
		UserDO user = request.getObject("user");
		if(user == null){
			user = this.userDao.queryObjectById(UserDO.class, 2L);
//			throw new Exception("无法获取网站用户信息，请尝试重新进入该页面");
		}
		return user;
	}
	@Override
	public void validateUser(UserDO user) throws Exception {
		UserDO ruser = request.getObject("user");
		if(ruser == null){
//			throw new Exception("无法获取网站用户信息，请尝试重新进入该页面");
			ruser = this.userDao.queryObjectById(UserDO.class, 2L);
		}
		if(ruser.getId()!=user.getId()){
			throw new Exception("无法获取非本用户数据");
		}
	}

	

}
