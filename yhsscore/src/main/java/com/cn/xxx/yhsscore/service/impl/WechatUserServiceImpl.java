package com.cn.xxx.yhsscore.service.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.xxx.wxcore.util.AdvancedUtil;
import com.cn.xxx.wxcore.vo.WeixinUserInfo;
import com.cn.xxx.yhsscore.dao.WechatUserDao;
import com.cn.xxx.yhsscore.model.WechatUserDO;
import com.cn.xxx.yhsscore.service.WechatUserService;
@Service
public class WechatUserServiceImpl implements WechatUserService {
	
	private Logger log = LoggerFactory.getLogger(WechatUserServiceImpl.class);
	@Autowired
	private WechatUserDao wechatUserDao ;
	@Override
	public WechatUserDO getWechatUserDOByOpenid(String openid) {
		return wechatUserDao.queryWechatUserDOByOpenid(openid);
	}
	@Override
	public void addOrUpdateWechatUser(WechatUserDO wechat) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public WechatUserDO getWechatUserById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public WechatUserDO queryOrSaveWechatUserByOpenid(String openid) {
		WechatUserDO user = wechatUserDao.queryWechatUserDOByOpenid(openid);
		if(user != null){
			return user;
		}
		//去微信接口获取用户信息并保存
		WeixinUserInfo wechatUser = AdvancedUtil.getUserInfo(openid);
		WechatUserDO wechatUserDO = new WechatUserDO();
		try {
			BeanUtils.copyProperties(wechatUserDO, wechatUser);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		} 
		wechatUserDao.saveOrUpdate(wechatUserDO);
		return wechatUserDO ;
	}

}
