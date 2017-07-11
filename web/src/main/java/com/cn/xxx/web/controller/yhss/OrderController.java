package com.cn.xxx.web.controller.yhss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.xxx.commons.controller.BaseController;
import com.cn.xxx.web.factory.ProductOrderFactory;
import com.cn.xxx.web.vo.request.ProductOrderCreateVO;
import com.cn.xxx.web.vo.response.OrderVO;
import com.cn.xxx.yhsscore.model.OrderDO;
import com.cn.xxx.yhsscore.service.OrderService;
import com.cn.xxx.yhsscore.util.OrderType;

@Controller
@Scope("prototype")
@RequestMapping("/order")
public class OrderController extends BaseController{
	
	private  Logger log = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private ProductOrderFactory productOrderFactory ;
	@Autowired
	private OrderService productOrderService;
	
	
	@ResponseBody
	@RequestMapping(value="/create/product",method=RequestMethod.POST)
	public Map<String,Object> createProduct(@RequestBody ProductOrderCreateVO order) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		//校验参数
		this.productOrderFactory.validateProductOrderCreate(order);
		//转换参数
		OrderDO orderDO = this.productOrderFactory.convertProductOrderCreateParam(order);
		//生成订单
		orderDO = this.productOrderService.create(orderDO);
		
		result.put("orderNo", orderDO.getOrderNo());
		return result;
	}
	@ResponseBody
	@RequestMapping(value="/getList",method=RequestMethod.GET)
	public Map<String,Object> getList(String type) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		List<OrderDO> orderDOList = productOrderService.getListByUser(type);
		List<OrderVO> orderVOList = new ArrayList<OrderVO>();
		if(type == null){//查询所有订单
			for(OrderDO orderDO:orderDOList){
				if(OrderType.PRODUCT.equals(orderDO.getType())){//商品订单
					OrderVO orderVO = this.productOrderFactory.convertOrderDO2OrderVO(orderDO);
					orderVOList.add(orderVO);
				}
			}
		}else if(OrderType.PRODUCT.equals(type)){//查询商品订单
			orderVOList = this.productOrderFactory.convertOrderVOList2OrderDOList(orderDOList);
		}
		
		result.put("data", orderVOList);
		return result;
	}
	@ResponseBody
	@RequestMapping(value="/getOrder/{orderNO}",method=RequestMethod.GET)
	public Map<String,Object> getOrder(@PathVariable String orderNO) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		OrderDO orderDO = this.productOrderService.getOrderByUser(orderNO);
		OrderVO orderVO = new OrderVO();
		if(OrderType.PRODUCT.equals(orderDO.getType())){
			orderVO = this.productOrderFactory.convertOrderDO2OrderVO(orderDO);
		}
		
		result.put("data", orderVO);
		return result;
	}
	
}
