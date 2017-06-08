package com.cn.xxx.yhsscore.service;

import java.util.List;

import com.cn.xxx.yhsscore.model.ContractDO;

public interface ContractService {
	
	public void addContract(ContractDO contract) throws Exception;
	
	public void updateContract(ContractDO contract) throws Exception;
	
	public void deleteContractById(Long id) throws Exception;
	
	public List<ContractDO> getList() throws Exception;
	
}
