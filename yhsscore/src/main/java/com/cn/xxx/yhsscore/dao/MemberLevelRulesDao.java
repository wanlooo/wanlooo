package com.cn.xxx.yhsscore.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cn.xxx.commons.dao.BaseDao;
import com.cn.xxx.yhsscore.model.MemberLevelRulesDO;

/**
 * @author houzm
 * @date 2017年6月17日
 * @descripte 
 */
@Repository
public class MemberLevelRulesDao extends BaseDao<MemberLevelRulesDO>{

	protected MemberLevelRulesDao() {
		super(MemberLevelRulesDO.class);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 根据等级查询等级规则详情
	 * @param level
	 * @return
	 */
	public MemberLevelRulesDO getRulesByLevel(Integer level){
		String hql = "from MemberLevelRulesDO where deleted = 0 and memberLevel="+level;
		@SuppressWarnings("unchecked")
		List<MemberLevelRulesDO> result = (List<MemberLevelRulesDO>) this.query(hql);
		return result!=null&&result.size()>0?result.get(0):null;
	}

}
