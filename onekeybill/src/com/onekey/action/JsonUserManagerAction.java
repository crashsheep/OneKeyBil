package com.onekey.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.onekey.dao.impl.UserDAO;
import com.onekey.entity.User;
import com.onekey.util.Base64;
import com.opensymphony.xwork2.ActionSupport;
@ParentPackage("json-default")
@Namespace("/")
public class JsonUserManagerAction extends ActionSupport implements ServletResponseAware,ServletRequestAware{

	private String data;
	private HttpServletResponse response;
	private HttpServletRequest request;

	public void setServletRequest(HttpServletRequest arg0) {
		this.request=arg0;
	}
	public void setServletResponse(HttpServletResponse arg0) {
		this.response=arg0;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	@Action(value = "user_load")
	public String findAllUser() throws JSONException{
		UserDAO userdao=new UserDAO();
		List<User> userlist=userdao.findAll();
		JSONArray jsonMembers = new JSONArray();
		for (User user : userlist) {
			JSONObject json = new JSONObject();
			json.put("id", user.getId().toString());
			json.put("name", user.getName().toString());
			json.put("create_time", user.getCreateTime().toString());
			json.put("type", user.getType().toString());
			jsonMembers.put(json);
		}
		data = jsonMembers.toString();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out;	
		try {
			out = response.getWriter();
			out.print(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	@Action(value = "user_add")
	public String addUser(){
		String username=request.getParameter("manager");
		String password=request.getParameter("password");
		String type=request.getParameter("type");
		User user=new User();
		user.setName(username);
		user.setPassword(Base64.getBase64(password));
		user.setCreateTime(new Timestamp(System.currentTimeMillis()));
		user.setType(type);
		UserDAO userDAO=new UserDAO();
		userDAO.save(user);
		response.setCharacterEncoding("UTF-8");
		PrintWriter out;	
		try {
			out = response.getWriter();
			out.print("1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Action(value = "user_del")
	public String deleteUser(){
		String ids=request.getParameter("ids");
		String[] id=ids.split(",");
		UserDAO userDAO=new UserDAO();
		for(int i=0;i<id.length;i++){
			//System.out.println(id[i]);
			User user=userDAO.findById(Integer.parseInt(id[i]));
			userDAO.delete(user);
		}
		response.setCharacterEncoding("UTF-8");
		PrintWriter out;	
		try {
			out = response.getWriter();
			out.print(id.length);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Action(value = "user_update")
	public String updateUser(){
		String id=request.getParameter("id");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String type=request.getParameter("type");
		System.out.println(id+" "+username+" "+password+" "+type);
		UserDAO userDAO=new UserDAO();
		User user=userDAO.findById(Integer.parseInt(id));
		user.setName(username);
		user.setPassword(Base64.getBase64(password));
		user.setType(type);
		userDAO.save(user);
		response.setCharacterEncoding("UTF-8");
		PrintWriter out;	
		try {
			out = response.getWriter();
			out.print("1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}

