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
@Table(name = "YHSS_HOTEL_STOCK")
//酒店库存表
@JsonIgnoreProperties({"lastUpdate","deleted","creator","lastModifier","lastModifierName","hotelTrip"})
public class HotelStockDO extends AbstractBaseDO {
	
	private static final long serialVersionUID = 1L;
	
	private Date date;
	private Integer amount ; //剩余人数数量
	private Integer singleRoom ;//单人房数量
	private Integer doubleRoom ;//双人房数量
	private HotelTripDO hotelTrip ;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "HOTEL_TRIP_ID")
	public HotelTripDO getHotelTrip() {
		return hotelTrip;
	}
	public void setHotelTrip(HotelTripDO hotelTrip) {
		this.hotelTrip = hotelTrip;
	}
	@Column(name = "DATE")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Column(name = "AMOUNT")
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	@Column(name = "SINGLE_ROOM")
	public Integer getSingleRoom() {
		return singleRoom;
	}
	public void setSingleRoom(Integer singleRoom) {
		this.singleRoom = singleRoom;
	}
	@Column(name = "DOUBLE_ROOM")
	public Integer getDoubleRoom() {
		return doubleRoom;
	}
	public void setDoubleRoom(Integer doubleRoom) {
		this.doubleRoom = doubleRoom;
	}
	
}
