package com.cn.xxx.yhsscore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.xxx.commons.util.CollectionUtil;
import com.cn.xxx.yhsscore.dao.HotelTripDao;
import com.cn.xxx.yhsscore.model.HotelTripDO;
import com.cn.xxx.yhsscore.service.HotelTripService;
@Service
public class HotelTripServiceImpl implements HotelTripService {

	@Autowired
	HotelTripDao hotelTripDao ;
	
	@Override
	public List<HotelTripDO> getHotelTripList() {
		List<HotelTripDO> list = this.hotelTripDao.queryAll(HotelTripDO.class);
		CollectionUtil.sortById(list, "asc");
		return list;
	}

	@Override
	public HotelTripDO getHotelTripDetail(long id) {
		return this.hotelTripDao.queryObjectById(HotelTripDO.class, id);
	}

}
