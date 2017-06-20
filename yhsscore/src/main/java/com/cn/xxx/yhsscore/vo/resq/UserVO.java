package com.cn.xxx.yhsscore.vo.resq;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.cn.xxx.yhsscore.vo.resp.AddressVO;
import com.cn.xxx.yhsscore.vo.resp.ContractVO;
import com.cn.xxx.yhsscore.vo.resp.PointDetailVO;
import com.cn.xxx.yhsscore.vo.resp.SchoolVO;
import com.cn.xxx.yhsscore.vo.resp.SecretSecurityVO;

/**
 * @author xulong
 */
public class UserVO implements Serializable {
	
	private static final long serialVersionUID = 5823870080049448402L;
	private String userid;//系统按规则生成
	private String nickName;//昵称
	private String userName;//用户名	
	private String password;
	private String confirmPassword;//密码确认
	private String userType;//1-学生,2-社会人群,3-后台管理人员
	private String status;//用户状态
	private String gender;
	private String idName;//证件名称
	private String idType;//证件类型
	private String idNo;//证件号码
	private String imageUrl ;
	private String imageDate ;//最近一次换头像的时间
	private Date birthDate;
	private String phoneNo;//座机号码
	private String mobileNo;//移动号码
	private String email;
	private String qq;
	private String visiteCode;//邀请码
	private String visitedId;//上一级会员的ID（关联）
	private String company;//当前公司名称
	private String country;//当前所属国家
	private String area;//当前所属地区
	private String province;//当前所属省份
	private String city;//当前所属城市（市级）
	private String county;//当前所属县郡
//	private String address;//当前街道地址
	private String postcode;//当前邮编
	
	private UserVO visteUser;//邀请自己的上级用户
	private SchoolVO school ;//学校信息
	private Set<ContractVO> contract ;
	private Set<AddressVO> address ;
	private Set<SecretSecurityVO> security;
	private Set<PointDetailVO> pointDetails;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getIdName() {
		return idName;
	}
	public void setIdName(String idName) {
		this.idName = idName;
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
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
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
	public String getVisitedId() {
		return visitedId;
	}
	public void setVisitedId(String visitedId) {
		this.visitedId = visitedId;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public UserVO getVisteUser() {
		return visteUser;
	}
	public void setVisteUser(UserVO visteUser) {
		this.visteUser = visteUser;
	}
	public SchoolVO getSchool() {
		return school;
	}
	public void setSchool(SchoolVO school) {
		this.school = school;
	}
	public Set<ContractVO> getContract() {
		return contract;
	}
	public void setContract(Set<ContractVO> contract) {
		this.contract = contract;
	}
	public Set<AddressVO> getAddress() {
		return address;
	}
	public void setAddress(Set<AddressVO> address) {
		this.address = address;
	}
	public Set<SecretSecurityVO> getSecurity() {
		return security;
	}
	public void setSecurity(Set<SecretSecurityVO> security) {
		this.security = security;
	}
	public Set<PointDetailVO> getPointDetails() {
		return pointDetails;
	}
	public void setPointDetails(Set<PointDetailVO> pointDetails) {
		this.pointDetails = pointDetails;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getImageDate() {
		return imageDate;
	}
	public void setImageDate(String imageDate) {
		this.imageDate = imageDate;
	}
	
}
