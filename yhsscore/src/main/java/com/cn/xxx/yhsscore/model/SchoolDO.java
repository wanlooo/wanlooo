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

/**
 *学校信息表
 */
@Entity
@Table(name = "YHSS_SCHOOL")
@JsonIgnoreProperties({"lastUpdate","deleted","creator","lastModifier","lastModifierName","handler","hibernateLazyInitializer"})
public class SchoolDO extends AbstractBaseDO {
	
	private static final long serialVersionUID = 1L;
	
	private String schoolname;//学校名称
	private String college;//学院/系别
	private String profession;//专业
	private String studentid;//学生证号码
	
	private ImagesDO images;
	
	@OneToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "IMAGES_ID")
	public ImagesDO getImages() {
		return images;
	}
	public void setImages(ImagesDO images) {
		this.images = images;
	}
	@Column(name="SCHOOL_NAME")
	public String getSchoolname() {
		return schoolname;
	}
	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}
	@Column(name="COLLEGE")
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	@Column(name="PROFESSION")
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	@Column(name="STUDENT_ID")
	public String getStudentid() {
		return studentid;
	}
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	
}
