package com.cn.xxx.web.vo.response;

import java.util.Date;
import java.util.Set;

import com.cn.xxx.yhsscore.model.PointDetailDO;
import com.cn.xxx.yhsscore.model.SchoolDO;

public class UserVO {
	private Long id ;
	private String nickName;//昵称
	private String userName;//用户名	
	private String gender;//性别
	private String name ;//姓名
	private String imageUrl ;
	private String imageDate ;//最近一次换头像的时间
	private String idType;//证件类型 ID-身份证 PP-护照
	private String idNo;//证件号码
	private Date birthDate;
	private String phoneNo;
	private String email;
	private String qq;
	private String visiteCode;
	
	private SchoolDO school ;
	private Set<PointDetailDO> pointDetails;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getVisiteCode() {
		return visiteCode;
	}
	public void setVisiteCode(String visiteCode) {
		this.visiteCode = visiteCode;
	}
	public SchoolDO getSchool() {
		return school;
	}
	public void setSchool(SchoolDO school) {
		this.school = school;
	}
	public Set<PointDetailDO> getPointDetails() {
		return pointDetails;
	}
	public void setPointDetails(Set<PointDetailDO> pointDetails) {
		this.pointDetails = pointDetails;
	}
	public String getImageDate() {
		return imageDate;
	}
	public void setImageDate(String imageDate) {
		this.imageDate = imageDate;
	}

}
