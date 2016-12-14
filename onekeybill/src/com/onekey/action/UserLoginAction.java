package com.onekey.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("struts-default")
@Namespace("/")
public class UserLoginAction extends ActionSupport implements SessionAware {

	private Map session;

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}

	@Action(value = "index", results = { @Result(name = "success", location = "/login.jsp"), })
	public String log() {
		return SUCCESS;

	}

	@Action(value = "login", results = { @Result(name = "success", location = "/WEB-INF/admin.jsp"),
			@Result(name = "fail", type="redirect",location = "/login.jsp")})
	public String login() {
		if (session.get("username") != null) {
			return SUCCESS;
		} else {
			return "fail";
		}
	}
	

}
