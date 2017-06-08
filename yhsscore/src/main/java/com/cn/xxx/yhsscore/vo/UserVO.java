package com.cn.xxx.yhsscore.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xulong
 */
public class UserVO implements Serializable {
	
	private static final long serialVersionUID = 5823870080049448402L;
	private String userid;//系统按规则生成
	private String nick_name;//昵称
	private String password;
	private String confirmPassword;//密码确认
	private String user_type;//1-学生,2-社会人群,3-后台管理人员
	private String status;//用户状态
	private String gender;
	private String id_name;//证件名称
	private String id_type;//证件类型
	private String id_no;//证件号码
	private Date birth_date;
	private String phoneno;//座机号码
	private String mobileno;//移动号码
	private String email;
	private String qq;
	private String visite_code;//邀请码
	private String visitedid;//上一级会员的ID（关联）
	private String company;//当前公司名称
	private String country;//当前所属国家
	private String area;//当前所属地区
	private String province;//当前所属省份
	private String city;//当前所属城市（市级）
	private String county;//当前所属县郡
	private String address;//当前街道地址
	private String postcode;//当前邮编
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
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
	public String getId_name() {
		return id_name;
	}
	public void setId_name(String id_name) {
		this.id_name = id_name;
	}
	public String getId_type() {
		return id_type;
	}
	public void setId_type(String id_type) {
		this.id_type = id_type;
	}
	public String getId_no() {
		return id_no;
	}
	public void setId_no(String id_no) {
		this.id_no = id_no;
	}
	public Date getBirth_date() {
		return birth_date;
	}
	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
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
	public String getVisite_code() {
		return visite_code;
	}
	public void setVisite_code(String visite_code) {
		this.visite_code = visite_code;
	}
	public String getVisitedid() {
		return visitedid;
	}
	public void setVisitedid(String visitedid) {
		this.visitedid = visitedid;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
}
