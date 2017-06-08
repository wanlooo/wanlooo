package com.cn.xxx.yhsscore.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.cn.xxx.commons.model.AbstractBaseDO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "YHSS_HOTEL")
//学校信息表
@JsonIgnoreProperties({"lastUpdate","deleted","creator","lastModifier","lastModifierName","handler","hibernateLazyInitializer"})
public class HotelDO extends AbstractBaseDO {
	
	private static final long serialVersionUID = 1L;
	
	private String hotelName;
	private String desc;
	private String note1;
	private String note2;
	private ImagesDO images ;
	
	@OneToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	@JoinColumn(name = "IMAGES_ID")
	public ImagesDO getImages() {
		return images;
	}
	public void setImages(ImagesDO images) {
		this.images = images;
	}
	
	@Column(name="HOTEL_NAME")
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	@Column(name="DESCRIPTION")
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
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
