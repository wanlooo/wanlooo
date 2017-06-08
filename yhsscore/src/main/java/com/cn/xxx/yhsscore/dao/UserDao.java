package com.cn.xxx.yhsscore.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cn.xxx.commons.dao.BaseDao;
import com.cn.xxx.yhsscore.model.UserDO;

/**
 * @author xulong
 */
@Repository
public class UserDao extends BaseDao<UserDO> {

	public UserDao() {
		// TODO Auto-generated constructor stub
		super(UserDO.class);
	}

	public UserDO searchUserByUserName(String userName) throws Exception {
		// TODO Auto-generated method stub
		String hql = "from UserDO u where u.userName = :userName";
		UserDO user = (UserDO) this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setString("userName", userName).uniqueResult();
		return user;
	}

	public UserDO searchUserByPhoneNo(String phoneNo) {
		// TODO Auto-generated method stub
		String hql = "from UserDO u where u.deleted=false and u.phoneNo = :phoneNo";
		UserDO user = (UserDO) this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setString("phoneNo", phoneNo).uniqueResult();
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public UserDO queryUserDOByVisiteCode(String visiteCode) {
		String hql = "from UserDO u where u.deleted=false and u.visiteCode = ?";
		List<UserDO> list = (List<UserDO>) this.getHibernateTemplate().find(hql, visiteCode);
		return list!=null && list.size()>0 ? list.get(0):null;
	}

	public String findNickNameByUserId(String userId) throws Exception {
		// TODO Auto-generated method stub
		try {
			String hql = "FROM UserDO u where u.deleted = false and u.userId = :userId";
			UserDO u = (UserDO) this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setString("userId", userId).uniqueResult();
			return u.getNickName();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public String findEncodedPasswordByUserId(String userId) throws Exception {
		// TODO Auto-generated method stub
		try {
			String hql = "FROM UserDO u where u.deleted = false and u.userId = :userId";
			UserDO u = (UserDO) this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setString("userId", userId).uniqueResult();
			return u.getPassword();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public UserDO searchUserByNickName(String nick_name) {
		// TODO Auto-generated method stub
		try {
			String hql = "FROM UserDO u where u.deleted = false and u.nick_name = :nick_name";
			UserDO user = (UserDO) this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setString("nick_name", nick_name).uniqueResult();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public UserDO searchUsersByOrderStatus(String orderStatus) {
		// TODO Auto-generated method stub
		String hql = "from UserDO u where u.deleted=false and u.orderStatus = :orderStatus";
		UserDO user = (UserDO) this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setString("orderStatus", orderStatus).uniqueResult();
		return user;
	}
	@SuppressWarnings("unchecked")
	public UserDO queryUserDOByUserNameOrPhoneNo(String loginName){
		String hql  = "from UserDO u where u.deleted=false and u.userName = ? or u.phoneNo = ?";
		List<UserDO> list = (List<UserDO>) this.getHibernateTemplate().find(hql, loginName,loginName);
		return list!=null && list.size()>0 ? list.get(0):null;
	}
	
	@SuppressWarnings("unchecked")
	public List<UserDO> queryUserDOByUserNamesOrPhoneNos(String... loginNames){
		StringBuffer inStr = new StringBuffer();
		for(String loginName:loginNames){
			inStr.append(",'"+loginName+"'");
		}
		
		String param = inStr.substring(1);
		String hql  = "from UserDO u where u.deleted=false and u.userName in("+param+") or u.phoneNo in("+param+")";
		List<UserDO> list = (List<UserDO>) this.getHibernateTemplate().find(hql);
		return list;
	}

	@SuppressWarnings("unchecked")
	public UserDO queryUserByUserName(String userName) {
		// TODO Auto-generated method stub
		String hql = "from UserDO u where u.deleted=false and u.userName = :userName";
		UserDO user = (UserDO) this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setString("userName", userName).uniqueResult();
		return user;
	}
	
}