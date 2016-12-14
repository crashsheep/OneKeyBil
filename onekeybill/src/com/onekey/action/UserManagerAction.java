package com.onekey.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class UserManagerAction extends ActionSupport implements SessionAware{

	private Map session;
	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session=arg0;
	}
	
	@Action(value = "UserManager", results = { @Result(name = "success", location = "/WEB-INF/UserManager.jsp"),
			@Result(name = "fail", type="redirect",location = "/login.jsp")})
	public String execute() {
		if (session.get("username") == null) {
			return "fail";	}	
		else{
			return SUCCESS;
		}
	}
}