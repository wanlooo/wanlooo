package com.cn.xxx.yhsscore.service.impl;

import org.springframework.stereotype.Service;

import com.cn.xxx.yhsscore.model.OrderDO;
import com.cn.xxx.yhsscore.service.OrderService;
@Service("hotelTripOrderService")
public class HotelTripOrderServiceImpl extends AbstractOrderServiceImpl implements OrderService {

	@Override
	public OrderDO create(OrderDO order) throws Exception {
		//验证是否存在重复提交订单操作
		
		//获取酒店价格和库存信息
		
		//验证入住和离开期间酒店库存
		
		//验证订单总价
		
		//保存订单信息
		return null ;
	}



}
