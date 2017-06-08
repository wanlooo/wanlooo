package com.cn.xxx.yhsscore.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cn.xxx.commons.model.AbstractBaseDO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "YHSS_HOTEL_PRICE")
//酒店价格表
@JsonIgnoreProperties({"lastUpdate","deleted","creator","lastModifier","lastModifierName","hotelTrip"})
public class HotelPriceDO extends AbstractBaseDO {
	
	private static final long serialVersionUID = 1L;
	
	private String type ; //类型：SINGLEROOM-单人房，DOUBLEROOM-双人房
	private Date start;
	private Date end;
	private Integer price ;
	private HotelTripDO hotelTrip ;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "HOTEL_TRIP_ID")
	public HotelTripDO getHotelTrip() {
		return hotelTrip;
	}
	public void setHotelTrip(HotelTripDO hotelTrip) {
		this.hotelTrip = hotelTrip;
	}
	@Column(name="START")
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	@Column(name="END")
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	@Column(name="PRICE")
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	@Column(name="TYPE")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
