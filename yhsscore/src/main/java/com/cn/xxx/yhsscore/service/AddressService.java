package com.cn.xxx.yhsscore.service;

import java.util.List;

import com.cn.xxx.yhsscore.model.AddressDO;

public interface AddressService {
	
	public void addAddress(AddressDO address) throws Exception;
	
	public void updateAddress(AddressDO address) throws Exception;
	
	public void resetDefaultAddress(Long id)  throws Exception;
	
	public void deleteAddressById(Long id) throws Exception;
	
	public List<AddressDO> getList() throws Exception;
	
}
