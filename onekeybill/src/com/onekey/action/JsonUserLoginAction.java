package com.onekey.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;


import com.onekey.dao.impl.UserDAO;
import com.onekey.util.Base64;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("json-default")
@Namespace("/")
public class JsonUserLoginAction extends ActionSupport implements SessionAware,
		ServletRequestAware {

	private String data;
	private Map session;
	private HttpServletRequest req;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.req = arg0;
	}

	@Action(value = "validate", results = { @Result(name = "success", type = "json", params = {
			"includeProperties", "data" }) })
	public String vali() {
		UserDAO userDAO=new UserDAO();

		List usr=userDAO.vali(req.getParameter("manager"), Base64.getBase64(req.getParameter("password")));
		if(usr.size()>0)
		{
			session.put("username", req.getParameter("manager"));
			this.data = "1";
			}
		else{
			this.data=null;
		}
		return SUCCESS;
	}

}