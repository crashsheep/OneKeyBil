package com.onekey.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * EasyuiNav entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "easyui_nav", catalog = "jiudian")
public class EasyuiNav implements java.io.Serializable {

	// Fields

	private Integer id;
	private String text;
	private String state;
	private String iconCls;
	private String url;
	private Integer nid;

	// Constructors

	/** default constructor */
	public EasyuiNav() {
	}

	/** full constructor */
	public EasyuiNav(String text, String state, String iconCls, String url,
			Integer nid) {
		this.text = text;
		this.state = state;
		this.iconCls = iconCls;
		this.url = url;
		this.nid = nid;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "text", nullable = false, length = 20)
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Column(name = "state", nullable = false, length = 10)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "iconCls", nullable = false, length = 20)
	public String getIconCls() {
		return this.iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	@Column(name = "url", nullable = false, length = 50)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "nid", nullable = false)
	public Integer getNid() {
		return this.nid;
	}

	public void setNid(Integer nid) {
		this.nid = nid;
	}

}