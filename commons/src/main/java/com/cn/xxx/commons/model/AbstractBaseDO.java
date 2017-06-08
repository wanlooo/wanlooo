package com.cn.xxx.commons.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class AbstractBaseDO implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 2516071385435721847L;

	protected Long id;
	
	protected Date created;
	
	protected Date lastUpdate;
	
	protected Boolean deleted=false;
	
	/**
	 * 创建人id
	 */
	protected Long creator;
	
	/**
	 * 最后修改人id
	 */
	protected Long lastModifier;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="test_my")
	@SequenceGenerator(name="test_my",sequenceName="hib_seq")
	@Column(length = 20)
	public Long getId() {
		return id;
	}
	
	public void setId(final Long id) {
		this.id = id;
	}
	
	@Column(name = "deleted")
	public Boolean isDeleted() {
		return deleted;
	}
	
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created")
	public Date getCreated() {
		return created;
	}
	
	public void setCreated(Date created) {
		this.created = created;
	}
	
	public void setCreated() {
		this.created = new Date();
	}
	
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_update")
	public Date getLastUpdate() {
		return lastUpdate;
	}
	
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	public void setLastUpdate() {
		this.lastUpdate = new Date();
	}
	
	@Column(name = "creator")
	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}
	
	@Column(name = "LAST_MODIFIER")
	public Long getLastModifier() {
		return lastModifier;
	}

	public void setLastModifier(Long lastModifier) {
		this.lastModifier = lastModifier;
	}
	
	/**
	 * the last mofifier's name
	 */
	protected String lastModifierName;

	@Column(name = "LAST_MODIFIER_NAME")
	public String getLastModifierName() {
		return lastModifierName;
	}

	public void setLastModifierName(String lastModifierName) {
		this.lastModifierName = lastModifierName;
	}
}
