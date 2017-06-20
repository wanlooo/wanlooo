package com.cn.xxx.yhsscore.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.cn.xxx.commons.model.AbstractBaseDO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * 会员信息表
 */
@Entity
@Table(name="YHSS_USER")
@JsonIgnoreProperties({"lastUpdate","deleted","creator","lastModifier","lastModifierName"})
public class UserDO extends AbstractBaseDO {
	private static final long serialVersionUID = 1L;
	
	private String userId;//系统按规则生成 学生-ST开头  社会人员-EM开头  管理人员-AD开头
	private String nickName;//昵称
	private String userName;//用户名	
	private String password;
	private String userType;//student-学生,employee-社会人群,admin-后台管理人员
	private String status;//用户状态
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
	private String visiteCode;//邀请码 用户自己的邀请码
	private Long currentPoints;//当前总积分
	private UserDO visiteUser ; //邀请自己的上级用户
	private SchoolDO school ;//学校信息
	private Set<ContractDO> contract ;
	private Set<AddressDO> address ;
	private Set<SecretSecurityDO> security;
	private MemberLevelRulesDO memberLevelRules;
	private Set<OrderDO> orders;
	
	@OneToMany(mappedBy="user",fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	public Set<OrderDO> getOrders() {
		return orders;
	}
	public void setOrders(Set<OrderDO> orders) {
		this.orders = orders;
	}
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="VISITE_USER_ID")
	public UserDO getVisiteUser() {
		return visiteUser;
	}
	public void setVisiteUser(UserDO visiteUser) {
		this.visiteUser = visiteUser;
	}
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="SCHOOL_ID")
	public SchoolDO getSchool() {
		return school;
	}
	public void setSchool(SchoolDO school) {
		this.school = school;
	}
	@OneToMany(mappedBy="user",fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	public Set<ContractDO> getContract() {
		return contract;
	}
	public void setContract(Set<ContractDO> contract) {
		this.contract = contract;
	}
	@OneToMany(mappedBy="user",fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	public Set<AddressDO> getAddress() {
		return address;
	}
	public void setAddress(Set<AddressDO> address) {
		this.address = address;
	}
	@OneToMany(mappedBy="user",fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	public Set<SecretSecurityDO> getSecurity() {
		return security;
	}
	public void setSecurity(Set<SecretSecurityDO> security) {
		this.security = security;
	}
	@ManyToOne(fetch=FetchType.LAZY, optional=true, cascade={CascadeType.ALL})
	@JoinColumn(name="MEMBER_LEVEL",referencedColumnName="MEMBER_LEVEL")
	public MemberLevelRulesDO getMemberLevelRules() {
		return memberLevelRules;
	}
	public void setMemberLevelRules(MemberLevelRulesDO memberLevelRules) {
		this.memberLevelRules = memberLevelRules;
	}
	@Column(name="USER_ID")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name="NICK_NAME")
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	@Column(length = 100,name="PASSWORD")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="USER_TYPE")
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	@Column(name="STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name="GENDER")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Column(name="NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="ID_TYPE")
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	@Column(name="ID_NO")
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	@Column(name="BIRTHDATE")
	@Temporal(TemporalType.DATE)
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	@Column(name="PHONE_NO")
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	@Column(name="EMAIL")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="QQ")
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	@Column(name="VISETE_CODE")
	public String getVisiteCode() {
		return visiteCode;
	}
	public void setVisiteCode(String visiteCode) {
		this.visiteCode = visiteCode;
	}
	@Column(name="USER_NAME")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name="IMAGE_URL")
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	@Column(name="IMAGE_DATE")
	public String getImageDate() {
		return imageDate;
	}
	public void setImageDate(String imageDate) {
		this.imageDate = imageDate;
	}
	@Column(name="CURRENT_POINTS")
	public Long getCurrentPoints() {
		return currentPoints;
	}
	public void setCurrentPoints(Long currentPoints) {
		this.currentPoints = currentPoints;
	}
	

}
