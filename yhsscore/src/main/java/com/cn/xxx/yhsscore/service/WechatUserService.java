package com.cn.xxx.yhsscore.service;

import com.cn.xxx.yhsscore.model.WechatUserDO;

public interface WechatUserService {

	WechatUserDO getWechatUserDOByOpenid(String openid);
	
	void addOrUpdateWechatUser(WechatUserDO wechat);
	
	WechatUserDO getWechatUserById(Integer id);
	
	WechatUserDO queryOrSaveWechatUserByOpenid(String openid);
	
}
