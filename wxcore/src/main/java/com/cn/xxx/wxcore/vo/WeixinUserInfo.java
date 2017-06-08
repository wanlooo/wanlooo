package com.cn.xxx.wxcore.vo;

/**
 * 微信用户的基本信息
 * 
 */
public class WeixinUserInfo {
	// 用户的标识
	private String openid;
	// 关注状态（1是关注，0是未关注），未关注时获取不到其余信息
	private Integer subscribe;
	// 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
	private String subscribe_time;
	// 昵称
	private String nickname;
	// 用户的性别（1是男性，2是女性，0是未知）
	private Integer sex;
	// 用户所在国家
	private String country;
	// 用户所在省份
	private String province;
	// 用户所在城市
	private String city;
	// 用户的语言，简体中文为zh_CN
	private String language;
	// 用户头像
	private String headimgurl;
	// 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
	private String unionid;
	// 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注 
	private String remark;
	// 用户所在的分组ID
	private String groupid;
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public Integer getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(Integer subscribe) {
		this.subscribe = subscribe;
	}
	public String getSubscribe_time() {
		return subscribe_time;
	}
	public void setSubscribe_time(String subscribe_time) {
		this.subscribe_time = subscribe_time;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
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
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
}
