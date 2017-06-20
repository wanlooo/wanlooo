package com.cn.xxx.yhsscore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.cn.xxx.yhsscore.dao.MemberLevelRulesDao;
import com.cn.xxx.yhsscore.model.MemberLevelRulesDO;
import com.cn.xxx.yhsscore.service.MemberLevelRulesService;

public class MemberLevelRulesServiceImpl implements MemberLevelRulesService {
	
	@Autowired
	private MemberLevelRulesDao memberLevelRulesDao;

	@Override
	public MemberLevelRulesDO getRulesByLevel(Integer level) {
		MemberLevelRulesDO result = memberLevelRulesDao.getRulesByLevel(level);
		return result;
	}

}
