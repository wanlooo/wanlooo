package com.cn.xxx.yhsscore.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.xxx.commons.util.CollectionUtil;
import com.cn.xxx.yhsscore.dao.ContractDao;
import com.cn.xxx.yhsscore.model.ContractDO;
import com.cn.xxx.yhsscore.model.UserDO;
import com.cn.xxx.yhsscore.service.ContractService;
import com.cn.xxx.yhsscore.service.UserService;

@Service
public class ContractServiceImpl implements ContractService{
	
//	private static final Logger LOGGER = LoggerFactory.getLogger(AddressServiceImpl.class);
	
	@Autowired
	protected ContractDao contractDao;
	@Autowired
	private UserService userService ;

	@Override
	public void addContract(ContractDO contract) throws Exception {
		UserDO user = this.userService.getCacheUser();
		contract.setUser(user);
		this.contractDao.saveOrUpdate(contract);
	}

	@Override
	public void updateContract(ContractDO contract) throws Exception {
		ContractDO co = this.contractDao.queryObjectById(ContractDO.class, contract.getId());
		if(co == null){
			throw new Exception("获取修改联系人信息失败");
		}
		UserDO u = co.getUser();
		this.userService.validateUser(u);
		
		co.setName(contract.getName());
		co.setMobileNo(contract.getMobileNo());
		co.setIdType(contract.getIdType());
		co.setIdNo(contract.getIdNo());
		this.contractDao.saveOrUpdate(co);
	}

	@Override
	public void deleteContractById(Long id) throws Exception {
		ContractDO co = this.contractDao.queryObjectById(ContractDO.class, id);
		if(co == null){
			throw new Exception("获取修改联系人信息失败");
		}
		UserDO u = co.getUser();
		this.userService.validateUser(u);
		this.contractDao.deleteObjectLogical(co);
	}

	@Override
	public List<ContractDO> getList() throws Exception {
		UserDO user = this.userService.getCacheUser();
		Set<ContractDO> set = user.getContract();
		List<ContractDO> list = new ArrayList<ContractDO>();
		for(ContractDO a:set){
			if(!a.isDeleted()){
				list.add(a);
			}
		}
		CollectionUtil.sortById(list, "desc");
		return list;
	}
}
