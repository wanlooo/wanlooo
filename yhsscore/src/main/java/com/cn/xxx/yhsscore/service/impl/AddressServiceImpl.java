package com.cn.xxx.yhsscore.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.xxx.commons.util.CollectionUtil;
import com.cn.xxx.yhsscore.dao.AddressDao;
import com.cn.xxx.yhsscore.model.AddressDO;
import com.cn.xxx.yhsscore.model.UserDO;
import com.cn.xxx.yhsscore.service.AddressService;
import com.cn.xxx.yhsscore.service.UserService;

@Service
public class AddressServiceImpl implements AddressService{
	
//	private static final Logger LOGGER = LoggerFactory.getLogger(AddressServiceImpl.class);
	
	@Autowired
	protected AddressDao addressDao;
	@Autowired
	private UserService userService ;

	@Override
	public void addAddress(AddressDO address) throws Exception {
		UserDO user = this.userService.getCacheUser();
		address.setUser(user);
		this.addressDao.saveOrUpdate(address);
		
		if(address.isDefault()){
			Set<AddressDO> set = user.getAddress();
			if(set != null && set.size()>1){
				for(AddressDO ad : set){
					if(ad.getId()!=address.getId()){
						ad.setDefault(false);
					}
				}
				this.addressDao.updateObjects(set);
			}
		}
	}

	@Override
	public void updateAddress(AddressDO address) throws Exception {
		AddressDO ad = this.addressDao .queryObjectById(AddressDO.class, address.getId());
		if(ad == null){
			throw new Exception("获取修改地址信息失败");
		}
		UserDO u = ad.getUser();
		this.userService.validateUser(u);
		if(address.getAddress() != null){
			ad.setAddress(address.getAddress());
		}
		if(address.getName() != null){
			ad.setName(address.getName());
		}
		if(address.getPhoneno() != null){
			ad.setPhoneno(address.getPhoneno());
		}
		this.addressDao.saveOrUpdate(ad);
	}

	@Override
	public void deleteAddressById(Long id) throws Exception {
		AddressDO address = this.addressDao .queryObjectById(AddressDO.class, id);
		if(address == null){
			throw new Exception("删除地址不存在");
		}
		UserDO u = address.getUser();
		this.userService.validateUser(u);
		this.addressDao.deleteObjectLogical(address);
	}

	@Override
	public List<AddressDO> getList() throws Exception {
		UserDO user = this.userService.getCacheUser();
		Set<AddressDO> set = user.getAddress();
		List<AddressDO> list = new ArrayList<AddressDO>();
		for(AddressDO a:set){
			if(!a.isDeleted()){
				list.add(a);
			}
		}
		CollectionUtil.sortById(list, "desc");
		return list;
	}

	@Override
	public void resetDefaultAddress(Long id)  throws Exception{
		AddressDO address = this.addressDao .queryObjectById(AddressDO.class, id);
		if(address == null){
			throw new Exception("变更地址不存在");
		}
		UserDO u = address.getUser();
		this.userService.validateUser(u);
		Set<AddressDO> set = u.getAddress();
		if(set != null && set.size()>0){
			for(AddressDO ad : set){
				if(ad.getId()==id){
					ad.setDefault(true);
				}else{
					ad.setDefault(false);
				}
			}
			this.addressDao.updateObjects(set);
		}
	}
	

}
