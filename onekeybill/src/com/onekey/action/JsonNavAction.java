package com.onekey.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.onekey.dao.impl.EasyuiNavDAO;
import com.onekey.entity.EasyuiNav;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("json-default")
@Namespace("/")
public class JsonNavAction extends ActionSupport implements
		ServletResponseAware {

	private String data;
	private int id;

	private HttpServletResponse response;

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	// @Action(value = "nav", results = { @Result(name = "success", type =
	// "json", params = {"root", "data" }) })
	@Action(value = "nav")
	public String nav() throws JSONException {
		EasyuiNavDAO navDAO = new EasyuiNavDAO();

		List<EasyuiNav> navList = navDAO.findAll(id);
		JSONArray jsonMembers = new JSONArray();
		for (EasyuiNav nav : navList) {
			JSONObject json = new JSONObject();
			json.put("id", nav.getId().toString());
			json.put("text", nav.getText().toString());
			json.put("state", nav.getState().toString());
			json.put("iconCls", nav.getIconCls().toString());
			json.put("url", nav.getUrl().toString());
			jsonMembers.put(json);
		}
		data = jsonMembers.toString();
		response.setCharacterEncoding("UTF-8");

		try {
			response.getWriter().write(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
