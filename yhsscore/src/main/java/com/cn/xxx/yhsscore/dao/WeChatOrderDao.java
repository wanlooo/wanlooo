package com.cn.xxx.yhsscore.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cn.xxx.commons.dao.BaseDao;
import com.cn.xxx.yhsscore.model.OrderDO;

/**
 * @author xulong
 */
@Repository
public class WeChatOrderDao extends BaseDao<OrderDO>{

	protected WeChatOrderDao() {
		super(OrderDO.class);
	}

	@SuppressWarnings("unchecked")
	public List<OrderDO> queryAllOrdersByPage(int pagesize, int pagenum) {
		int limitstart = (pagenum - 1) * pagesize;
		int limitcount = pagesize;
		String query = "from OrderDO oo order by oo.created desc";
		Query myquery = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createQuery(query);
		myquery.setFirstResult(limitstart);
		myquery.setMaxResults(limitcount);
		List<OrderDO> list = (List<OrderDO>) myquery.list();
		return list;
	}

	public OrderDO queryOrderByOrderNO(String orderNo) {
		String hql  = "from OrderDO oo where oo.deleted =false and oo.orderNo=?";
		List<OrderDO> list = (List<OrderDO>) this.getHibernateTemplate().find(hql,orderNo);
		return list!=null && list.size()>0 ? list.get(0):null ;
	}

	public OrderDO queryAllOrdersById(String userId) {
		String hql  = "from OrderDO oo where oo.deleted =false and oo.userId=?";
		List<OrderDO> list = (List<OrderDO>) this.getHibernateTemplate().find(hql,userId);
		return list!=null && list.size()>0 ? list.get(0):null ;
	}

	public OrderDO queryOrdersByStatus(String orderStatus) {
		String hql  = "from OrderDO oo where oo.deleted =false and oo.orderStatus=?";
		List<OrderDO> list = (List<OrderDO>) this.getHibernateTemplate().find(hql,orderStatus);
		return list!=null && list.size()>0 ? list.get(0):null ;
	}

	public void updateWeChatOrder(OrderDO order) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().merge(order);
	}

}