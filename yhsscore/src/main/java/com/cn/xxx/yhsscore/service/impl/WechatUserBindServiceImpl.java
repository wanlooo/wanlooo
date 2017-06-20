package com.cn.xxx.yhsscore.service.impl;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.xxx.commons.util.JsonUtils;
import com.cn.xxx.wxcore.util.AdvancedUtil;
import com.cn.xxx.wxcore.vo.WeixinUserInfo;
import com.cn.xxx.yhsscore.dao.MemberLevelRulesDao;
import com.cn.xxx.yhsscore.dao.SchoolDao;
import com.cn.xxx.yhsscore.dao.SecretSecurityDao;
import com.cn.xxx.yhsscore.dao.UserDao;
import com.cn.xxx.yhsscore.dao.WechatUserBindDao;
import com.cn.xxx.yhsscore.dao.WechatUserDao;
import com.cn.xxx.yhsscore.model.SecretSecurityDO;
import com.cn.xxx.yhsscore.model.UserDO;
import com.cn.xxx.yhsscore.model.WechatUserBindDO;
import com.cn.xxx.yhsscore.model.WechatUserDO;
import com.cn.xxx.yhsscore.service.UserService;
import com.cn.xxx.yhsscore.service.WechatUserBindService;
import com.cn.xxx.yhsscore.service.WechatUserService;
import com.cn.xxx.yhsscore.vo.resp.LoginVO;
import com.cn.xxx.yhsscore.vo.resp.RegistVO;
@Service
public class WechatUserBindServiceImpl implements WechatUserBindService {

	private Logger logger = LoggerFactory.getLogger(WechatUserBindServiceImpl.class);
	
	@Autowired
	private WechatUserBindDao wechatUserBindDao ;
	@Autowired
	private WechatUserDao wechatUserDao ;
	@Autowired 
	private WechatUserService wechatUserService ;
	@Autowired 
	private UserDao userDao ;
	@Autowired 
	private UserService userService ;
	@Autowired 
	private SchoolDao schoolDao ;
	@Autowired 
	private SecretSecurityDao secretSecurityDao ;
	@Autowired
	private MemberLevelRulesDao memberLevelRulesDao;
	
	@Override
	public WechatUserBindDO getWechatUserBindDOAllByOpenid(String openid) {
		return wechatUserBindDao.queryWechatUserBindDOAllByOpenid(openid);
	}
	@Override
	public boolean checkWechatUserBind(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean checkWechatUserBind(Integer id, String pactlAccount) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean unbindWechatUserBind(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean deleteWechatUserBind(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserDO doRegist(RegistVO regist,String openid) throws Exception {
		UserDO userDO = regist.getUser();
		logger.info(">>>>>>>入参:"+JsonUtils.objectToJson(regist));
		//userName,phoneNo校验
		List<UserDO> list = this.userDao.queryUserDOByUserNamesOrPhoneNos(userDO.getUserName(),userDO.getPhoneNo());
		if (list!=null && list.size()>0) {
			throw new Exception("用户名或手机号已存在");
		}
		
		//根据openid查询WechatUserDO,如无记录则重新插入记录
		WechatUserDO wechatUser= this.wechatUserService.queryOrSaveWechatUserByOpenid(openid);
		if(wechatUser == null){
			logger.info("微信用户信息获取为空，重新拉取用户微信信息进行入库操作");
			WeixinUserInfo userInfo = AdvancedUtil.getUserInfo(openid);
			try {
				BeanUtils.copyProperties(wechatUser, userInfo);
			} catch (Exception e) {
				logger.error(e.getMessage());
				return null;
			} 
			wechatUserDao.saveOrUpdate(wechatUser);
		}

		//从微信中获取nickName
		userDO.setNickName(wechatUser.getNickname());
		userDO.setImageUrl(wechatUser.getHeadimgurl());
		
		//邀请码处理
		//1.设置邀请人id
		if(userDO.getVisiteCode() != null && !userDO.getVisiteCode().trim().equals("")){
			logger.info(".设置邀请人id");
			UserDO visiteUser = this.userDao.queryUserDOByVisiteCode(userDO.getVisiteCode()) ;
			if(visiteUser != null){
				userDO.setVisiteUser(visiteUser);
			}
		}
		logger.info("存学校信息>>>"+JsonUtils.objectToJson(userDO.getSchool()));
		//保存学校信息
		if(userDO.getSchool()!=null){
			this.schoolDao.insertObject(userDO.getSchool());
		}
		//2.生成新邀请码供自己使用
		String visiteCode = "1234";
		userDO.setVisiteCode(visiteCode);
		//密保问题处理
		
		userDao.saveOrUpdate(userDO);
		
		//保存密保问题
		if(userDO.getSecurity()!=null){
			for(SecretSecurityDO ss:userDO.getSecurity()){
				ss.setUser(userDO);
			}
			this.secretSecurityDao.insertObjects(userDO.getSecurity());
		}
		
		//初始化总积分-0分
		userDO.setCurrentPoints(0L);
		//初始化会员等级-0级
		userDO.setMemberLevelRules(memberLevelRulesDao.getRulesByLevel(0));
		bind(userDO, wechatUser);
		
		return userDO ;
	}
	
	
	@Override
	public WechatUserBindDO doBind(LoginVO login,String openid) throws Exception {
		//验证账号
		UserDO user = this.userDao.queryUserDOByUserNameOrPhoneNo(login.getLoginName());
		if(user == null){
			throw new Exception("用户名不存在");
		}else if(!user.getPassword().equals(login.getPassword())){
			throw new Exception("密码不正确");
		}
		WechatUserDO wechatUser = this.wechatUserService.queryOrSaveWechatUserByOpenid(openid);
		if(wechatUser == null){
			throw new Exception("微信用户信息获取失败");
		}
		
		return bind(user, wechatUser) ;
	}
	
	/**绑定原则：
	 * 1、已用户记录为准，一个userId对应一条绑定记录
	 * 2、如果wechatUserId为空，则表明该会员账号对应的绑定记录已解绑，否则为绑定
	**/
	private WechatUserBindDO bind(UserDO user,WechatUserDO wechatUser) throws Exception{
		// 验证绑定记录
		WechatUserBindDO userBind = this.wechatUserBindDao.queryWechatUserBindDOByUserId(user.getId());
		WechatUserBindDO wechatUserBind = this.wechatUserBindDao.queryWechatUserBindDOByWechatUserId(wechatUser.getId());

		if (userBind == null && wechatUserBind == null) {// 没有绑定记录
			userBind = new WechatUserBindDO();
			// userBind.setStatus("BIND");
			userBind.setUser(user);
			userBind.setWechatUser(wechatUser);
			this.wechatUserBindDao.saveOrUpdate(userBind);
		} else if (userBind == null) {// 会员无绑定记录，微信有绑定记录--场景：微信上已绑定一个会员账号，现绑定一个无绑定记录的新会员账号
			userBind = new WechatUserBindDO();
			// userBind.setStatus("BIND");
			userBind.setUser(user);
			userBind.setWechatUser(wechatUser);
			this.wechatUserBindDao.saveOrUpdate(userBind);

			wechatUserBind.setWechatUser(null);
			this.wechatUserBindDao.saveOrUpdate(wechatUserBind);
		} else if (wechatUserBind == null) {// 会员有绑定记录，本微信无记录--场景：本微信上无绑定记录，现绑定一个其他微信绑定过的会员账号（也可能已解绑）
			if (userBind.getWechatUser() != null) {// 要绑定的会员正绑定在其他微信上
				// 向另外一个微信发送解绑信息
				String message = "您的会员账号（" + user.getUserName() + "） 已被微信用户（"
						+ wechatUser.getNickname() + "）绑定，如非您授权操作请及时联系公众号管理人员！";
				AdvancedUtil.sendCustomTextMessage(userBind.getWechatUser()
						.getOpenid(), message);
				// 绑定操作
				userBind.setWechatUser(wechatUser);
			} else {// 会员绑定记录已解绑
				userBind.setWechatUser(wechatUser);
			}
			this.wechatUserBindDao.saveOrUpdate(userBind);
		} else {// 都有记录
			if (userBind.getId() == wechatUserBind.getId()) {// 同一记录
				throw new Exception("您已绑定该用户，无需再次绑定");
			} else {
				// 本微信绑定记录去除
				wechatUserBind.setWechatUser(null);

				// 向另外一个微信发送解绑信息
				String message = "您的会员账号（" + user.getUserName() + "） 已被微信用户（"
						+ wechatUser.getNickname() + "）绑定，如非您授权操作请及时联系公众号管理人员！";
				AdvancedUtil.sendCustomTextMessage(userBind.getWechatUser()
						.getOpenid(), message);
				// 该会员的绑定用户为本微信账号
				userBind.setWechatUser(wechatUser);

				this.wechatUserBindDao.saveOrUpdate(userBind);
				this.wechatUserBindDao.saveOrUpdate(wechatUserBind);
			}
		}
		
		return userBind ;
	}
	

}
