package com.cn.xxx.yhsscore.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.cn.xxx.commons.model.AbstractBaseDO;

@MappedSuperclass
public class AbstractActiveDO extends AbstractBaseDO {
	private static final long serialVersionUID = 2516071385435721847L;

	private String activeNo;//活动编号
	private String title;//活动标题（如为空取商品名称）
	private String desc;//描述（如为空取商品描述）
	private Date start;//活动开始日期（只有开始日期为永久活动）
	private Date end;//活动结束日期（只有结束日期为立即生效活动）
	private String point;//活动积分

	private ImagesDO images ;

	@OneToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	@JoinColumn(name = "IMAGES_ID")
	public ImagesDO getImages() {
		return images;
	}
	public void setImages(ImagesDO images) {
		this.images = images;
	}

	@Column(name="ACTIVE_NO")
	public String getActiveNo() {
		return activeNo;
	}
	public void setActiveNo(String activeNo) {
		this.activeNo = activeNo;
	}
	@Column(name="TITLE")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Lob
	@Column(name="DESCRIPTION")
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="START")
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="END")
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	@Column(name="POINT")
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	
}
