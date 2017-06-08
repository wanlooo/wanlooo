package com.cn.xxx.yhsscore.service;

import java.util.List;

import com.cn.xxx.yhsscore.model.TgActiveDO;

public interface TuanGouActiveService {
	
	List<TgActiveDO> getList() throws Exception;
	
	TgActiveDO getDetail(long id) throws Exception;

}
