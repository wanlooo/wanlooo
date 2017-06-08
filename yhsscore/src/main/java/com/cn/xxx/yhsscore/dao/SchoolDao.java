package com.cn.xxx.yhsscore.dao;

import org.springframework.stereotype.Repository;

import com.cn.xxx.commons.dao.BaseDao;
import com.cn.xxx.yhsscore.model.SchoolDO;

/**
 * @author xulong
 */
@Repository
public class SchoolDao extends BaseDao<SchoolDO> {

	protected SchoolDao() {
		super(SchoolDO.class);
	}


}
