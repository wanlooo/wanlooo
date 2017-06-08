package com.cn.xxx.yhsscore.dao;

import org.springframework.stereotype.Repository;

import com.cn.xxx.commons.dao.BaseDao;
import com.cn.xxx.yhsscore.model.HotActiveDO;

@Repository
public class HotActiveDao extends BaseDao<HotActiveDO> {

	public HotActiveDao() {
		super(HotActiveDO.class);
	}
	
	

}