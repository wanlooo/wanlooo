package com.cn.xxx.yhsscore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.xxx.commons.util.CollectionUtil;
import com.cn.xxx.yhsscore.dao.TgActiveTimeDao;
import com.cn.xxx.yhsscore.model.TgActiveTimeDO;
import com.cn.xxx.yhsscore.service.TuanGouActiveTimeService;
@Service
public class TuanGouActiveTimeServiceImpl implements TuanGouActiveTimeService {

	@Autowired
	TgActiveTimeDao tgActiveTimeDao ;

	@Override
	public List<TgActiveTimeDO> getList() throws Exception {
		List<TgActiveTimeDO> list = this.tgActiveTimeDao.queryAll(TgActiveTimeDO.class);
		CollectionUtil.sortById(list, "asc");
		return list;
	}


}
