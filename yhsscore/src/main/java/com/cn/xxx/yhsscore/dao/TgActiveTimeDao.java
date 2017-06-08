package com.cn.xxx.yhsscore.dao;

import org.springframework.stereotype.Repository;

import com.cn.xxx.commons.dao.BaseDao;
import com.cn.xxx.yhsscore.model.TgActiveTimeDO;

@Repository
public class TgActiveTimeDao extends BaseDao<TgActiveTimeDO> {

	public TgActiveTimeDao() {
		super(TgActiveTimeDO.class);
	}
	
	

}