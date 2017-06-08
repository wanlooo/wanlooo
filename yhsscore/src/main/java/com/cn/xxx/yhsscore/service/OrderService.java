package com.cn.xxx.yhsscore.service;

import java.util.List;

import com.cn.xxx.yhsscore.model.OrderDO;

public interface OrderService {

	OrderDO create(OrderDO order) throws Exception ;
	
	List<OrderDO> getListByUser(String type) throws Exception ;
	
	List<OrderDO> getRecentListByUser(int months) throws Exception ;
	
	OrderDO getOrderByUser(String orderNO) throws Exception ;
}
