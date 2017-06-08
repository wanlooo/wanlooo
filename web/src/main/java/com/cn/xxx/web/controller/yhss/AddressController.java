package com.cn.xxx.web.controller.yhss;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.xxx.commons.controller.BaseController;
import com.cn.xxx.yhsscore.model.AddressDO;
import com.cn.xxx.yhsscore.service.AddressService;
import com.cn.xxx.yhsscore.util.NullValidateUtil;

@Controller
@Scope("prototype")
@RequestMapping("/address")
public class AddressController extends BaseController{
	
	@Autowired
	private AddressService addressService;
	
	@ResponseBody
	@RequestMapping(value="/getList",method=RequestMethod.GET)
	public Map<String,Object> getList() throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		List<AddressDO> list = this.addressService.getList();
		result.put("data", list);
		return result ;
	}
	
	@ResponseBody
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Map<String,Object> update(@RequestBody AddressDO addressDO) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		NullValidateUtil.validate(addressDO, "update");
		this.addressService.updateAddress(addressDO);
		result.put("success", true);
		return result ;
	}
	
	@ResponseBody
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Map<String,Object> regist(@RequestBody AddressDO addressDO) throws Exception{
		NullValidateUtil.validate(addressDO, "add");
		Map<String, Object> result = new HashMap<String, Object>();
		this.addressService.addAddress(addressDO);
		result.put("success", true);
		return result ;
	}
	
	@ResponseBody
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public Map<String,Object> delete(@PathVariable Long id) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		this.addressService.deleteAddressById(id);;
		result.put("success", true);
		return result ;
	}

	@ResponseBody
	@RequestMapping(value="/setDefault/{id}",method=RequestMethod.GET)
	public Map<String,Object> setDefault(@PathVariable Long id) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		this.addressService.resetDefaultAddress(id);
		result.put("success", true);
		return result ;
	}
	
}
