package com.cn.xxx.yhsscore.service;

import java.util.List;

import com.cn.xxx.yhsscore.model.HotelTripDO;

public interface HotelTripService {
	
	List<HotelTripDO> getHotelTripList();
	
	HotelTripDO getHotelTripDetail(long id);

}
