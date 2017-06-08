package com.cn.xxx.yhsscore.service;

import java.util.Map;

import com.cn.xxx.yhsscore.model.UserDO;

/**
 * @author xulong
 */
public interface UserService {

	Map<String, Object> doRegisterUser(UserDO user) throws Exception;

	UserDO findUserByPhoneNo(String phoneno);

	UserDO findUserByNickName(String nick_name);
	
	UserDO findUserByLoginName(String loginName);
	
	boolean userNameExists(String userName);
	
	boolean resetPassword(UserDO user);
	
	boolean editPassword(String password) throws Exception;

	UserDO doFindUserByUserName(String userName);
	
	UserDO getCacheUser() throws Exception;
	
	UserDO getCacheOnlyUser() throws Exception;
	
	void validateUser(UserDO user)  throws Exception; 

}
