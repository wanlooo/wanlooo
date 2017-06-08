package com.cn.xxx.yhsscore.dao;

import org.springframework.stereotype.Repository;

import com.cn.xxx.commons.dao.BaseDao;
import com.cn.xxx.yhsscore.model.MsActiveDO;

@Repository
public class MsActiveDao extends BaseDao<MsActiveDO> {

	public MsActiveDao() {
		super(MsActiveDO.class);
	}
	
	

}