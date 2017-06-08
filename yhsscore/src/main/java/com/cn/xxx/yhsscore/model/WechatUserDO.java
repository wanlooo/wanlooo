package com.cn.xxx.yhsscore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cn.xxx.commons.model.AbstractBaseDO;

/**
 */
@Entity
@Table(name = "YHSS_WECHAT_USER")
public class WechatUserDO extends AbstractBaseDO {
	private static final long serialVersionUID = 1L;
	// 用户的标识，对当前公众号唯一。
	// 请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
	private String openid;
	// 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
	private String subscribe;
	// 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
	private String subscribe_time;
	// 用户昵称
	private String nickname;
	// 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	private int sex;
	// 用户所在国家，如中国为CN
	private String country;
	// 用户所在省份
	private String province;
	// 用户所在城市
	private String city;
	// 用户的语言，简体中文为zh_CN
	private String language;
	// 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
	private String headimgurl;
	// 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
	private String unionid;
	// 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
	private String remark;
	// 用户所在的分组ID
	private String groupid;

	@Column(length = 8)
	public String getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}

	@Column(name = "openid", length = 64, nullable = false)
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	@Column(name = "nick_name", length = 64)
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column
	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	@Column(length = 16)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(length = 16)
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(length = 16)
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(length = 8)
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Column(length = 512, name = "head_img_url")
	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	@Column(length = 16)
	public String getSubscribe_time() {
		return subscribe_time;
	}

	public void setSubscribe_time(String subscribe_time) {
		this.subscribe_time = subscribe_time;
	}

	@Column(length = 128)
	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	@Column(length = 32)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(length = 8)
	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

}

