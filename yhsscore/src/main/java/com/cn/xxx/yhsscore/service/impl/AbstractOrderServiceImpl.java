package com.cn.xxx.yhsscore.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.cn.xxx.yhsscore.dao.OrderDao;
import com.cn.xxx.yhsscore.dao.ProductOrderDao;
import com.cn.xxx.yhsscore.model.OrderDO;
import com.cn.xxx.yhsscore.model.ProductOrderDO;
import com.cn.xxx.yhsscore.model.UserDO;
import com.cn.xxx.yhsscore.service.OrderService;
import com.cn.xxx.yhsscore.service.UserService;
public abstract class AbstractOrderServiceImpl implements OrderService {
	
	@Autowired
	protected UserService userService;
	@Autowired
	protected OrderDao orderDao ;
	@Autowired
	protected ProductOrderDao productOrderDao ;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderDO> getListByUser(String type) throws Exception{
		UserDO user = this.userService.getCacheOnlyUser();
		StringBuffer hql = new StringBuffer();
		hql.append("select o from OrderDO o left join o.user u left join fetch o.orderAddress");
		hql.append(" where o.deleted = 0 and u.id = ?");
		List<OrderDO> list = (List<OrderDO>) this.orderDao.query(hql.toString(), user.getId());
		return list ;
	}
	@Override
	public List<OrderDO> getRecentListByUser(int months) throws Exception{
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public OrderDO getOrderByUser(String orderNO) throws Exception {
		UserDO user = this.userService.getCacheOnlyUser();
		StringBuffer hql = new StringBuffer();
		hql.append("select o from OrderDO o left join o.user u left join fetch o.orderAddress");
		hql.append(" where o.deleted = 0 and u.id = ? and o.orderNo = ?");
		List<OrderDO> list = (List<OrderDO>) this.orderDao.query(hql.toString(), user.getId(),orderNO);
		return list!=null && list.size()>0 ? list.get(0) : null ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public OrderDO updateOrderStatus(String orderNO,String status) throws Exception {
		UserDO user = this.userService.getCacheOnlyUser();
		StringBuffer hql = new StringBuffer();
		hql.append("select o from OrderDO o left join o.user u left join fetch o.orderAddress");
		hql.append(" where o.deleted = 0 and u.id = ? and o.orderNo = ?");
		List<OrderDO> list = (List<OrderDO>) this.orderDao.query(hql.toString(), user.getId(),orderNO);
		if (list==null) {
			return null;
		}else if (list.size()==0) {
			return null;
		}else {
			OrderDO order = list.get(0);
			order.setStatus(status);
			orderDao.saveOrUpdate(order);
			Set<ProductOrderDO> productOrders = order.getProductOrders();
			for (ProductOrderDO productOrder : productOrders) {
				productOrder.setOrderStatus(status);
				productOrderDao.saveOrUpdate(productOrder);
			}
			return order;
		}
	}
}
