package com.cn.xxx.yhsscore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cn.xxx.commons.model.AbstractBaseDO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author xulong
 *
 */
@Entity
@Table(name="YHSS_TG_ACTIVE_TIME")
//团购时限表
@JsonIgnoreProperties({"lastUpdate","deleted","creator","lastModifier","lastModifierName"})
public class TgActiveTimeDO extends AbstractBaseDO {
	private static final long serialVersionUID = 1L;
	
	private String bargainStart;//团购开始时间（大于之前所有bargain_end）
	private String bargainEnd;//砍价时间（精确到分）
	private String saleEnd;//销售时间（精确到分）

	@Column(name="BARGAIN_START")
	public String getBargainStart() {
		return bargainStart;
	}
	public void setBargainStart(String bargainStart) {
		this.bargainStart = bargainStart;
	}
	@Column(name="BARGAIN_END")
	public String getBargainEnd() {
		return bargainEnd;
	}
	public void setBargainEnd(String bargainEnd) {
		this.bargainEnd = bargainEnd;
	}
	@Column(name="SALE_END")
	public String getSaleEnd() {
		return saleEnd;
	}
	public void setSaleEnd(String saleEnd) {
		this.saleEnd = saleEnd;
	}

}
