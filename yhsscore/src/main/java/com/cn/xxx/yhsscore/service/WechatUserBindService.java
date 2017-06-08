package com.cn.xxx.yhsscore.service;

import com.cn.xxx.yhsscore.model.UserDO;
import com.cn.xxx.yhsscore.model.WechatUserBindDO;
import com.cn.xxx.yhsscore.vo.resp.LoginVO;
import com.cn.xxx.yhsscore.vo.resp.RegistVO;

public interface WechatUserBindService {

	WechatUserBindDO getWechatUserBindDOAllByOpenid(String openid);
	
	boolean checkWechatUserBind(Integer id);
	
	boolean checkWechatUserBind(Integer id,String pactlAccount);
	
	boolean unbindWechatUserBind(Integer id);
	
	boolean deleteWechatUserBind(Integer id);
	
	UserDO doRegist(RegistVO regist,String openid) throws Exception ;
	
	WechatUserBindDO doBind(LoginVO login,String openid) throws Exception;
	
}
