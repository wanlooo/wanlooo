package com.cn.xxx.yhsscore.dao;

import org.springframework.stereotype.Repository;

import com.cn.xxx.commons.dao.BaseDao;
import com.cn.xxx.yhsscore.model.TgActiveDO;

@Repository
public class TgActiveDao extends BaseDao<TgActiveDO> {

	public TgActiveDao() {
		super(TgActiveDO.class);
	}
	
	

}