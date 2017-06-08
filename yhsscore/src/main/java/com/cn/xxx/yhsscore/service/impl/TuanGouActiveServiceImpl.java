package com.cn.xxx.yhsscore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.xxx.commons.util.CollectionUtil;
import com.cn.xxx.yhsscore.dao.TgActiveDao;
import com.cn.xxx.yhsscore.model.TgActiveDO;
import com.cn.xxx.yhsscore.service.TuanGouActiveService;
@Service
public class TuanGouActiveServiceImpl implements TuanGouActiveService {

	@Autowired
	TgActiveDao tgActiveDao ;

	@Override
	public List<TgActiveDO> getList() throws Exception {
		List<TgActiveDO> list = this.tgActiveDao.queryAll(TgActiveDO.class);
		CollectionUtil.sortById(list, "asc");
		return list;
	}

	@Override
	public TgActiveDO getDetail(long id) throws Exception {
		return this.tgActiveDao.queryObjectById(TgActiveDO.class, id);
	}
	

}
