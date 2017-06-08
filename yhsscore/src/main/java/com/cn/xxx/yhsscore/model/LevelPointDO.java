package com.cn.xxx.yhsscore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cn.xxx.commons.model.AbstractBaseDO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 会员等级积分规则表  
 */

@Entity
@Table(name = "YHSS_LEVEL_POINT")
@JsonIgnoreProperties({"lastUpdate","deleted","creator","lastModifier","lastModifierName"})
public class LevelPointDO extends AbstractBaseDO {
	
	private static final long serialVersionUID = 1L;
	
	private String level;
	private String point;
	private String note1;
	private String note2;
	
	@Column(name="LEVEL")
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	@Column(name="POINT")
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	@Column(name="NOTE1")
	public String getNote1() {
		return note1;
	}
	public void setNote1(String note1) {
		this.note1 = note1;
	}
	@Column(name="NOTE2")
	public String getNote2() {
		return note2;
	}
	public void setNote2(String note2) {
		this.note2 = note2;
	}
	
}
