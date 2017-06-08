package com.cn.xxx.yhsscore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cn.xxx.yhsscore.dao.OrderDao;
import com.cn.xxx.yhsscore.model.OrderDO;
import com.cn.xxx.yhsscore.model.UserDO;
import com.cn.xxx.yhsscore.service.OrderService;
import com.cn.xxx.yhsscore.service.UserService;
public abstract class AbstractOrderServiceImpl implements OrderService {
	
	@Autowired
	protected UserService userService;
	@Autowired
	OrderDao orderDao ;
	
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

}
