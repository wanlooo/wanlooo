package com.cn.xxx.yhsscore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.xxx.commons.util.CollectionUtil;
import com.cn.xxx.yhsscore.dao.MsActiveDao;
import com.cn.xxx.yhsscore.model.MsActiveDO;
import com.cn.xxx.yhsscore.service.MiaoShaActiveService;
@Service
public class MiaoShaActiveServiceImpl implements MiaoShaActiveService {

	@Autowired
	MsActiveDao msActiveDao ;

	@Override
	public List<MsActiveDO> getList() throws Exception {
		List<MsActiveDO> list = this.msActiveDao.queryAll(MsActiveDO.class);
		CollectionUtil.sortById(list, "asc");
		return list;
	}

	@Override
	public MsActiveDO getDetail(long id) throws Exception {
		return this.msActiveDao.queryObjectById(MsActiveDO.class, id);
	}
	

}
