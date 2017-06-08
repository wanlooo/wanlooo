package com.cn.xxx.yhsscore.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "YHSS_HOTEL_TRIP")
//酒店活动表
@JsonIgnoreProperties({"lastUpdate","deleted","creator","lastModifier","lastModifierName"})
public class HotelTripDO extends AbstractActiveDO {
	
	private static final long serialVersionUID = 1L;
	private String cityFrom ; //出发地
	private String cityTo ;  //目的地
	private String note1;
	private String note2;

	private HotelDO hotel ;
	private HotelThemeDO hotelTheme ;
	private Set<HotelPriceDO> hotelPrice ;
	private Set<HotelStockDO> hotelStock ;
	
	@OneToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	@JoinColumn(name = "TRIP_HOTEL_ID")
	public HotelDO getHotel() {
		return hotel;
	}
	public void setHotel(HotelDO hotel) {
		this.hotel = hotel;
	}
	
	@OneToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	@JoinColumn(name = "HOTEL_THEME_ID")
	public HotelThemeDO getHotelTheme() {
		return hotelTheme;
	}
	public void setHotelTheme(HotelThemeDO hotelTheme) {
		this.hotelTheme = hotelTheme;
	}
	@OneToMany(mappedBy = "hotelTrip",fetch=FetchType.LAZY)
	public Set<HotelPriceDO> getHotelPrice() {
		return hotelPrice;
	}
	public void setHotelPrice(Set<HotelPriceDO> hotelPrice) {
		this.hotelPrice = hotelPrice;
	}
	@OneToMany(mappedBy = "hotelTrip",fetch=FetchType.LAZY)
	public Set<HotelStockDO> getHotelStock() {
		return hotelStock;
	}
	public void setHotelStock(Set<HotelStockDO> hotelStock) {
		this.hotelStock = hotelStock;
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
	@Column(name="CITY_FROM")
	public String getCityFrom() {
		return cityFrom;
	}
	public void setCityFrom(String cityFrom) {
		this.cityFrom = cityFrom;
	}
	@Column(name="CITY_TO")
	public String getCityTo() {
		return cityTo;
	}
	public void setCityTo(String cityTo) {
		this.cityTo = cityTo;
	}
}
