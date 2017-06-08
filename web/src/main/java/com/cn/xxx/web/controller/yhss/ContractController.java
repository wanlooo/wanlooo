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
import com.cn.xxx.yhsscore.model.ContractDO;
import com.cn.xxx.yhsscore.service.ContractService;

@Controller
@Scope("prototype")
@RequestMapping("/contract")
public class ContractController extends BaseController{
	
	@Autowired
	private ContractService contractService;
	
	@ResponseBody
	@RequestMapping(value="/getList",method=RequestMethod.GET)
	public Map<String,Object> getList() throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		List<ContractDO> list = this.contractService.getList();
		result.put("data", list);
		return result ;
	}
	
	@ResponseBody
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Map<String,Object> update(@RequestBody ContractDO contractDO) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		this.contractService.updateContract(contractDO);
		result.put("success", true);
		return result ;
	}
	
	@ResponseBody
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Map<String,Object> regist(@RequestBody ContractDO contractDO) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		this.contractService.addContract(contractDO);
		result.put("success", true);
		return result ;
	}
	
	@ResponseBody
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public Map<String,Object> delete(@PathVariable Long id) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		this.contractService.deleteContractById(id);;
		result.put("success", true);
		return result ;
	}
}
