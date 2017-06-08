package com.cn.xxx.yhsscore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.xxx.commons.util.CollectionUtil;
import com.cn.xxx.yhsscore.dao.HotActiveDao;
import com.cn.xxx.yhsscore.model.HotActiveDO;
import com.cn.xxx.yhsscore.service.HotActiveService;
@Service
public class HotActiveServiceImpl implements HotActiveService {

	@Autowired
	HotActiveDao hotActiveDao ;

	@Override
	public List<HotActiveDO> getList() throws Exception {
		List<HotActiveDO> list = this.hotActiveDao.queryAll(HotActiveDO.class);
		CollectionUtil.sortById(list, "asc");
		return list;
	}

	@Override
	public HotActiveDO getDetail(long id) throws Exception {
		return this.hotActiveDao.queryObjectById(HotActiveDO.class, id);
	}
}
