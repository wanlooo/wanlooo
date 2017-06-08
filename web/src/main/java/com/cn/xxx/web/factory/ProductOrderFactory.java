package com.cn.xxx.web.factory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.xxx.commons.util.BeanUtil;
import com.cn.xxx.web.vo.request.ProductOrderCreateVO;
import com.cn.xxx.web.vo.response.OrderVO;
import com.cn.xxx.web.vo.response.ProductOrderVO;
import com.cn.xxx.yhsscore.model.MsActiveDO;
import com.cn.xxx.yhsscore.model.OrderDO;
import com.cn.xxx.yhsscore.model.ProductOrderDO;
import com.cn.xxx.yhsscore.model.TgActiveDO;
import com.cn.xxx.yhsscore.service.MiaoShaActiveService;
import com.cn.xxx.yhsscore.service.TuanGouActiveService;
import com.cn.xxx.yhsscore.util.OrderType;

/**
 * @author chenshuibin
 * 商品订单处理 
 */
@Service
public class ProductOrderFactory {
	@Autowired
	private MiaoShaActiveService msService ;
	@Autowired
	private TuanGouActiveService tgService ;

	public void validateProductOrderCreate(ProductOrderCreateVO order) throws Exception{
		if(!OrderType.MIAOSHA.equals(order.getOrderType()) && !OrderType.TUANGOU.equals(order.getOrderType()) ){
			throw new Exception("订单类型有误！");
		}else if(order.getActiveId() == null || order.getExpress()==null){
			throw new Exception("订单参数不足！");
		}
	}
	public OrderDO convertProductOrderCreateParam(ProductOrderCreateVO o) throws Exception{
		OrderDO order = new OrderDO();
		order.setType("PRODUCT");//商品订单
		order.setStatus("WP");
		order.setAmount(o.getAmount());
		order.setOrderAddress(o.getExpress());
		order.getOrderAddress().setStatus("WD");//待发货
		order.setUuid(o.getUuid());
		
		ProductOrderDO productOrder = new ProductOrderDO();
		productOrder.setType(o.getOrderType());
		productOrder.setAmount(o.getAmount());
		productOrder.setRemark(o.getRemark());
		productOrder.setProperties(o.getProperties());
		productOrder.setQuantity(o.getQuantity());
		productOrder.setOrderStatus("WP");
		
		if(o.getOrderType().equals(OrderType.MIAOSHA)){
			MsActiveDO msa = this.msService.getDetail(o.getActiveId());
			if(msa == null){
				throw new Exception("活动信息有误！");
			}
			productOrder.setMiaosha(msa);
			productOrder.setProduct(msa.getProduct());
		}else{
			TgActiveDO tga = this.tgService.getDetail(o.getActiveId());
			if(tga == null){
				throw new Exception("活动信息有误！");
			}
			productOrder.setTuangou(tga);
			productOrder.setProduct(tga.getProduct());
		}
		
		Set<ProductOrderDO> pos = new HashSet<ProductOrderDO>();
		pos.add(productOrder);
		order.setProductOrders(pos);
		
		return order ;
	}
	
	public OrderVO convertOrderDO2OrderVO(OrderDO orderDO){
		OrderVO orderVO = new OrderVO();
		//复制相同属性
		BeanUtil.copyProperties(orderVO, orderDO);
		//子订单赋值
		List<ProductOrderVO> povs = new ArrayList<ProductOrderVO>();
		Set<ProductOrderDO> pods = orderDO.getProductOrders();
		for(ProductOrderDO pod : pods){
			ProductOrderVO pov = new ProductOrderVO();
			pov.setProduct(pod.getProduct());
			BeanUtil.copyProperties(pov, pod);
			
			povs.add(pov);
		}
		orderVO.setPorders(povs);
		
		return orderVO ;
	}
	public List<OrderVO> convertOrderVOList2OrderDOList(List<OrderDO> orderDOList){
		List<OrderVO> orderVOList = new ArrayList<OrderVO>();
		for(OrderDO orderDO : orderDOList){
			OrderVO orderVO = this.convertOrderDO2OrderVO(orderDO);
			orderVOList.add(orderVO);
		}
		return orderVOList ;
	}
	
}
