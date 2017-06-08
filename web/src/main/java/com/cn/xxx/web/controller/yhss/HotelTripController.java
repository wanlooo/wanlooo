package com.cn.xxx.web.controller.yhss;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.xxx.commons.controller.BaseController;
import com.cn.xxx.yhsscore.model.HotelTripDO;
import com.cn.xxx.yhsscore.service.HotelTripService;
import com.cn.xxx.yhsscore.util.NullValidateUtil;

@Controller
@Scope("prototype")
@RequestMapping("/hotelTrip")
public class HotelTripController extends BaseController{
	
	private  Logger log = LoggerFactory.getLogger(HotelTripController.class);
	@Autowired
	HotelTripService hotelTripService ;
	
	@ResponseBody
	@RequestMapping(value="/getList",method=RequestMethod.GET)
	public Map<String,Object> getList() throws Exception{
		log.info("---begain getList---");
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		List<HotelTripDO> list = this.hotelTripService.getHotelTripList();
		/*ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		// do various things, perhaps:
		String someJsonString = mapper.writeValueAsString(list);
		System.out.println(someJsonString);*/
		result.put("data",list);
		return result;
	}
	@ResponseBody
	@RequestMapping(value="/getDetail/{id}",method=RequestMethod.GET)
	public Map<String,Object> getDetail(@PathVariable Long id) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		HotelTripDO ht = this.hotelTripService.getHotelTripDetail(id);
		result.put("data",ht);
		return result;
	}
}
