package com.cn.xxx.web.factory;

import org.springframework.stereotype.Service;

import com.cn.xxx.commons.util.BeanUtil;
import com.cn.xxx.web.vo.response.UserVO;
import com.cn.xxx.yhsscore.model.UserDO;
@Service
public class UserFactory {
	
	public UserVO convertUserDO2UserVO(UserDO user){
		UserVO userVO = new UserVO();
		BeanUtil.copyProperties(userVO, user);
		
		return userVO;
	}

}
