package com.cn.xxx.yhsscore.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.cn.xxx.commons.dao.BaseDao;
import com.cn.xxx.yhsscore.model.WechatUserBindDO;

@Repository
public class WechatUserBindDao extends BaseDao<WechatUserBindDO> {
	
	protected WechatUserBindDao() {
		super(WechatUserBindDO.class);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public WechatUserBindDO queryWechatUserBindDOAllByOpenid(String openid) {
		String hql = "select ub from WechatUserBindDO ub left join fetch ub.user u left join fetch ub.wechatUser wu where ub.deleted = false and wu.openid = ?";
//		String hql  = "select ub from WechatUserBindDO ub , WechatUserDO u where ub.deleted = false and ub.wechatUserId=u.id and u.deleted=false  and u.openid=?";
		List<WechatUserBindDO> list = (List<WechatUserBindDO>) this.getHibernateTemplate().find(hql, openid);
		return list!=null && list.size()>0 ? list.get(0):null ;
	}
	@SuppressWarnings("unchecked")
	public WechatUserBindDO queryWechatUserBindDOAllByUserName(String userName) {
		String hql = "select ub from WechatUserBindDO ub left join fetch ub.user u left join fetch ub.wechatUser wu where ub.deleted = false and u.userName = ?";
//		String hql  = "select ub from WechatUserBindDO ub , WechatUserDO u where ub.deleted = false and ub.wechatUserId=u.id and u.deleted=false  and u.openid=?";
		List<WechatUserBindDO> list = (List<WechatUserBindDO>) this.getHibernateTemplate().find(hql, userName);
		return list!=null && list.size()>0 ? list.get(0):null ;
	}
	@SuppressWarnings("unchecked")
	public WechatUserBindDO queryWechatUserBindDOByLoginName(String loginName) {
		String hql = "select ub from WechatUserBindDO ub left join fetch ub.user u  where ub.deleted = false and u.phoneNo = ? or u.userName = ?";
		List<WechatUserBindDO> list = (List<WechatUserBindDO>) this.getHibernateTemplate().find(hql, loginName,loginName);
		return list!=null && list.size()>0 ? list.get(0):null ;
	}
	
	@SuppressWarnings("unchecked")
	public WechatUserBindDO queryWechatUserBindDOByUserId(Long id) {
		String hql = "select ub from WechatUserBindDO ub left join fetch ub.user u  where ub.deleted = false and u.id = ?";
		List<WechatUserBindDO> list = (List<WechatUserBindDO>) this.getHibernateTemplate().find(hql, id);
		return list!=null && list.size()>0 ? list.get(0):null ;
	}
	@SuppressWarnings("unchecked")
	public WechatUserBindDO queryWechatUserBindDOByWechatUserId(Long id) {
		String hql = "select ub from WechatUserBindDO ub left join fetch ub.wechatUser  wu  where ub.deleted = false and wu.id = ?";
		List<WechatUserBindDO> list = (List<WechatUserBindDO>) this.getHibernateTemplate().find(hql, id);
		return list!=null && list.size()>0 ? list.get(0):null ;
	}
	
	@SuppressWarnings("unchecked")
	public WechatUserBindDO queryWechatUserBindDOById(Long id) {
		String hql  = "from WechatUserBindDO ub where ub.deleted = false and  ub.id=?";
		List<WechatUserBindDO> list = (List<WechatUserBindDO>) this.getHibernateTemplate().find(hql, id);
		return list!=null && list.size()>0 ? list.get(0):null ;
	}

	@SuppressWarnings("unchecked")
	public List<WechatUserBindDO> queryWechatUserBindDOByParams(String hql,Object[] params,int length,int start) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();	
		Query query = session.createQuery(hql);
		if(params!= null && params.length > 0){
			int i = 0;
			for(Object param : params){
				query.setParameter(i, param);
				i++ ;
			}
		}
		query.setFirstResult((start-1)*length);
		query.setMaxResults(length);
		return query.list();
		/*if(params == null || params.length < 1){
			return (List<WechatUserBindDO>) this.getHibernateTemplate().find(hql);
		}else{
			return (List<WechatUserBindDO>) this.getHibernateTemplate().find(hql, params);
			
		}*/
	}


}
