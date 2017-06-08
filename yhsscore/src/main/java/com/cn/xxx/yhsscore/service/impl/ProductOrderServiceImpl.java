package com.cn.xxx.yhsscore.service.impl;

import java.text.DecimalFormat;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.xxx.yhsscore.dao.OrderAddressDao;
import com.cn.xxx.yhsscore.dao.ProductOrderDao;
import com.cn.xxx.yhsscore.model.OrderDO;
import com.cn.xxx.yhsscore.model.ProductOrderDO;
import com.cn.xxx.yhsscore.model.UserDO;
import com.cn.xxx.yhsscore.service.OrderService;
import com.cn.xxx.yhsscore.util.OrderUtil;
import com.cn.xxx.yhsscore.util.OrderType;
@Service("productOrderService")
public class ProductOrderServiceImpl extends AbstractOrderServiceImpl implements OrderService {
	@Autowired
	ProductOrderDao productOrderDao ;
	@Autowired
	OrderAddressDao orderAddressDao ;
	
	@Override
	public OrderDO create(OrderDO order) throws Exception {
		/**获取用户信息**/
		UserDO user = this.userService.getCacheOnlyUser();
		order.setUser(user);
		/**验证是否存在重复提交订单操作**/

		/**生成订单号**/
		String orderNo = OrderUtil.createOrderNO( OrderUtil.LEVEL1 , null , order.getUuid());
		order.setOrderNo(orderNo);
		
		/**验证订单总价**/
		float sum = 0;
		DecimalFormat decimalFormat = new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
		//运费
		if(order.getOrderAddress().getExpressPrice() != null){
			sum += Float.parseFloat(order.getOrderAddress().getExpressPrice());
		}
		//活动订单总费用
		Set<ProductOrderDO> porders = order.getProductOrders();
		for(ProductOrderDO porder:porders){
			//生成子订单
			String orderno = OrderUtil.createOrderNO( OrderUtil.LEVEL2 , porder.getType() , order.getUuid());
			porder.setOrderno(orderno);
			//累加价格
			if(OrderType.MIAOSHA.equals(porder.getType())){
				String price = porder.getMiaosha().getActivePrice();
				sum += porder.getQuantity() * Float.parseFloat(price);
			}else{
				String price = porder.getTuangou().getCurrentPrice();
				sum += porder.getQuantity() * Float.parseFloat(price);
			}
		}
		//订单总额不符，下单失败
		/*if(!decimalFormat.format(sum).equals(decimalFormat.format(order.getAmount()))){
			throw new Exception("订单总额计算有误！");
		}*/
		System.out.println(decimalFormat.format(sum));
		
		/**保存订单信息**/
		this.orderAddressDao.saveOrUpdate(order.getOrderAddress());
		this.orderDao.saveOrUpdate(order);
		for(ProductOrderDO porder:order.getProductOrders()){
			porder.setOrder(order);
			this.productOrderDao.saveOrUpdate(porder);
		}
		
		return order ;
	}


}
