package com.cn.xxx.yhsscore.service;

import java.util.List;

import com.cn.xxx.yhsscore.model.HotActiveDO;

public interface HotActiveService {
	
	List<HotActiveDO> getList() throws Exception;
	
	HotActiveDO getDetail(long id) throws Exception;

}
