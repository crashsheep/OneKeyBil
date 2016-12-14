package com.onekey.service;

import java.util.List;






import org.json.JSONArray;
import org.json.JSONObject;

import com.onekey.dao.impl.UserDAO;
import com.onekey.entity.User;
import com.onekey.util.Base64;

public class test {

	public static void main(String[] args) {
		String password=Base64.getBase64("admin");
		System.out.println(password);
		System.out.println(Base64.getFromBase64(password));
	}

}
