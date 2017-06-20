package com.cn.xxx.yhsscore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cn.xxx.commons.model.AbstractBaseDO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author usky
 * @date 2017年6月12日
 * @descripte 会员等级规则
 */
@Entity
@Table(name="YHSS_MEMBER_LEVEL_RULES")
@JsonIgnoreProperties({"lastUpdate","deleted","creator","lastModifier","lastModifierName"})
public class MemberLevelRulesDO extends AbstractBaseDO {

	private static final long serialVersionUID = 4109510168084946905L;
	
	private Integer memberLevel;//会员等级
	private String integral;//升级到对应会员所需积分
	private String rights;//会员等级特权
	private Double discount;//销售折扣
	private Double addition;//积分获取加成
	private Integer nextIntegral;//下一级所需积分
	private String nextRights;//下一等级特权
	private String upgrateConditions;//升级条件
	
	@Column(name="MEMBER_LEVEL")
	public Integer getMemberLevel() {
		return memberLevel;
	}
	public void setMemberLevel(Integer memberLevel) {
		this.memberLevel = memberLevel;
	}
	@Column(name="MEMBER_INTEGRAL")
	public String getIntegral() {
		return integral;
	}
	public void setIntegral(String integral) {
		this.integral = integral;
	}
	@Column(name="MEMBER_RIGHTS")
	public String getRights() {
		return rights;
	}
	public void setRights(String rights) {
		this.rights = rights;
	}
	@Column(name="MEMBER_DISCOUNT")
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	
	@Column(name="MEMBER_ADDITION")
	public Double getAddition() {
		return addition;
	}
	public void setAddition(Double addition) {
		this.addition = addition;
	}
	@Column(name="NEXT_INTEGRAL")
	public Integer getNextIntegral() {
		return nextIntegral;
	}
	public void setNextIntegral(Integer nextIntegral) {
		this.nextIntegral = nextIntegral;
	}
	@Column(name="NEXT_RIGHTS")
	public String getNextRights() {
		return nextRights;
	}
	public void setNextRights(String nextRights) {
		this.nextRights = nextRights;
	}
	@Column(name="UPGRATE_CONDITIONS")
	public String getUpgrateConditions() {
		return upgrateConditions;
	}
	public void setUpgrateConditions(String upgrateConditions) {
		this.upgrateConditions = upgrateConditions;
	}
	
	

}
