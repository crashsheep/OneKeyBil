package com.onekey.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class UserLogout extends ActionSupport implements SessionAware{

	private Map session;

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}
	
	@Action(value = "logout", results = { @Result(name = "success", location = "/login.jsp"),
			})
	public String logout() {
		if (session.get("username") == null) {
			return SUCCESS;
		} else {
			session.put("username",null);
			return SUCCESS;
		}
	}

}
