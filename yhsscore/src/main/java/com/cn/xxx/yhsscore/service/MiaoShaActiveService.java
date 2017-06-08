package com.cn.xxx.yhsscore.service;

import java.util.List;

import com.cn.xxx.yhsscore.model.MsActiveDO;

public interface MiaoShaActiveService {
	
	List<MsActiveDO> getList() throws Exception;
	
	MsActiveDO getDetail(long id) throws Exception;

}
