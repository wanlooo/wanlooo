package com.cn.xxx.yhsscore.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cn.xxx.commons.dao.BaseDao;
import com.cn.xxx.yhsscore.model.WechatUserDO;

@Repository
public class WechatUserDao extends BaseDao<WechatUserDO> {
	
	protected WechatUserDao() {
		super(WechatUserDO.class);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public WechatUserDO queryWechatUserDOByOpenid(String openid) {
//		String hql  = "from WechatUserDO where deleted = false and openid=?";
//		List<WechatUserDO> list = (List<WechatUserDO>) this.getHibernateTemplate().find(hql, openid);
		
		Session s = getHibernateTemplate().getSessionFactory().getCurrentSession();		
		Criteria criteria = s.createCriteria(WechatUserDO.class,"u");
		criteria.add(Restrictions.eq("u.deleted", false));
		criteria.add(Restrictions.eq("u.openid",openid));
		List<WechatUserDO> list = criteria.list();
		
		return list!=null && list.size()>0 ? list.get(0):null ;
	}


}
