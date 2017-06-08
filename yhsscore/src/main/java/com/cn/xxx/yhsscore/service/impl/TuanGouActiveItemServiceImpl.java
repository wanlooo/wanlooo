package com.cn.xxx.yhsscore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.xxx.commons.util.CollectionUtil;
import com.cn.xxx.yhsscore.dao.TgActiveItemDao;
import com.cn.xxx.yhsscore.model.TgActiveItemDO;
import com.cn.xxx.yhsscore.service.TuanGouActiveItemService;
@Service
public class TuanGouActiveItemServiceImpl implements TuanGouActiveItemService {

	@Autowired
	TgActiveItemDao tgActiveItemDao ;

	@Override
	public List<TgActiveItemDO> getList() throws Exception {
		List<TgActiveItemDO> list = this.tgActiveItemDao.queryAll(TgActiveItemDO.class);
		CollectionUtil.sortById(list, "asc");
		return list;
	}


}
