package com.cn.xxx.web.controller.yhss;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.xxx.commons.controller.BaseController;
import com.cn.xxx.yhsscore.model.MsActiveDO;
import com.cn.xxx.yhsscore.service.MiaoShaActiveService;

@Controller
@Scope("prototype")
@RequestMapping("/miaosha")
public class MiaoShaActiveController extends BaseController{
	
	@Autowired
	MiaoShaActiveService miaoShaActiveService;
	
	@ResponseBody
	@RequestMapping(value="/getList",method=RequestMethod.GET)
	public Map<String,Object> getList() throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		List<MsActiveDO> list = this.miaoShaActiveService.getList();
		result.put("data", list);
		return result ;
	}

	@ResponseBody
	@RequestMapping(value="/getDetail/{id}",method=RequestMethod.GET)
	public Map<String,Object> getDetail(@PathVariable Long id) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		MsActiveDO ms = this.miaoShaActiveService.getDetail(id);
		result.put("data",ms);
		return result;
	}
}
