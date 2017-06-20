package com.cn.xxx.yhsscore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cn.xxx.commons.model.AbstractBaseDO;

/**
 * @author usky
 * @date 2017年6月12日
 * @descripte 商品/活动星级规则
 */
@Entity
@Table(name="YHSS_STAR_LEVEL_RULES")
public class StarLevelRulesDO extends AbstractBaseDO {

	private static final long serialVersionUID = 4109510168084946905L;
	private Byte starLevel;//星级
	private Long activePoint;//活动积分
	private Long commodityPoint;//商品积分
	
	@Column(name="STAR_LEVEL")
	public Byte getStarLevel() {
		return starLevel;
	}
	public void setStarLevel(Byte starLevel) {
		this.starLevel = starLevel;
	}
	@Column(name="ACTIVE_POINT")
	public Long getActivePoint() {
		return activePoint;
	}
	public void setActivePoint(Long activePoint) {
		this.activePoint = activePoint;
	}
	@Column(name="COMMODITY_POINT")
	public Long getCommodityPoint() {
		return commodityPoint;
	}
	public void setCommodityPoint(Long commodityPoint) {
		this.commodityPoint = commodityPoint;
	}

}
