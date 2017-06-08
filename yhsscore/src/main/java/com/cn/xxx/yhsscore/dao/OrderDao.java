package com.cn.xxx.yhsscore.dao;

import org.springframework.stereotype.Repository;

import com.cn.xxx.commons.dao.BaseDao;
import com.cn.xxx.yhsscore.model.OrderDO;

@Repository
public class OrderDao extends BaseDao<OrderDO> {

	public OrderDao() {
		// TODO Auto-generated constructor stub
		super(OrderDO.class);
	}
	
	

}