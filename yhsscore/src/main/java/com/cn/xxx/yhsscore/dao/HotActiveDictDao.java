package com.cn.xxx.yhsscore.dao;

import org.springframework.stereotype.Repository;

import com.cn.xxx.commons.dao.BaseDao;
import com.cn.xxx.yhsscore.model.HotActiveDictDO;

@Repository
public class HotActiveDictDao extends BaseDao<HotActiveDictDO> {

	public HotActiveDictDao() {
		super(HotActiveDictDO.class);
	}
	
	

}