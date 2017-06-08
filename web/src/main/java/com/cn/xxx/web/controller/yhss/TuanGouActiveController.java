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
import com.cn.xxx.yhsscore.model.TgActiveDO;
import com.cn.xxx.yhsscore.model.TgActiveTimeDO;
import com.cn.xxx.yhsscore.service.TuanGouActiveService;
import com.cn.xxx.yhsscore.service.TuanGouActiveTimeService;

@Controller
@Scope("prototype")
@RequestMapping("/tuangou")
public class TuanGouActiveController extends BaseController{
	
	@Autowired
	TuanGouActiveService tuanGouActiveService;
	@Autowired
	TuanGouActiveTimeService tuanGouActiveTimeService ;
	
	@ResponseBody
	@RequestMapping(value="/getList",method=RequestMethod.GET)
	public Map<String,Object> getList() throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		List<TgActiveDO> list = this.tuanGouActiveService.getList();
		result.put("tuangouActiveList", list);
		List<TgActiveTimeDO> listI = this.tuanGouActiveTimeService.getList();
		result.put("tuangouActiveTime", listI);
		return result ;
	}

	@ResponseBody
	@RequestMapping(value="/getDetail/{id}",method=RequestMethod.GET)
	public Map<String,Object> getDetail(@PathVariable Long id) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		TgActiveDO data = this.tuanGouActiveService.getDetail(id);
		result.put("tuangouActiveDetail",data);
		List<TgActiveTimeDO> listI = this.tuanGouActiveTimeService.getList();
		result.put("tuangouActiveTime", listI);
		return result;
	}
}
