package com.cn.xxx.web.controller.yhss;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.xxx.web.vo.request.PayParamInfo;
import com.cn.xxx.yhsscore.model.OrderDO;
import com.cn.xxx.yhsscore.service.OrderService;
import com.cn.xxx.yhsscore.util.Status;

@Controller
@Scope("prototype")
@RequestMapping("/order")
public class PayController {

	private Logger LOGGER = LoggerFactory.getLogger(PayController.class);
	
	@Autowired
	private OrderService productOrderService;
	
	@ResponseBody
    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public Map<String, Object> getPayUrl(HttpServletRequest request, @RequestBody PayParamInfo paramInfo) {
    	Map<String, Object> result = new HashMap<String, Object>();
    	result.put("success", false);
        LOGGER.info("PayController POST begin ================ " + paramInfo.getOrderNo());
        try {
			OrderDO order = productOrderService.updateOrderStatus(paramInfo.getOrderNo(), Status.ORDER_PF);//修改订单状态已支付
			result.put("success", true);
		} catch (Exception e) {
			LOGGER.info("ERROR  Order {} PayController POST  , Reason {} ",paramInfo.getOrderNo(),e.getMessage());
			result.put("reason", "订单"+paramInfo.getOrderNo()+"支付失败");
		}
		LOGGER.info("PayController POST end ================ " + paramInfo.getOrderNo());
        return result;
    }
}
