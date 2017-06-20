package com.cn.xxx.yhsscore.vo.resp;

import java.io.Serializable;

import com.cn.xxx.yhsscore.model.ImagesDO;

public class SchoolVO implements Serializable{

	private static final long serialVersionUID = -2238656192829837306L;
	private String schoolname;//学校名称
	private String college;//学院/系别
	private String profession;//专业
	private String studentid;//学生证号码
	
	private ImagesDO images;

	public String getSchoolname() {
		return schoolname;
	}

	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getStudentid() {
		return studentid;
	}

	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}

	public ImagesDO getImages() {
		return images;
	}

	public void setImages(ImagesDO images) {
		this.images = images;
	}
	
	
}
