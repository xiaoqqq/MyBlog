package com.xiaoq.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;
import com.xiaoq.module.User;
import com.xiaoq.service.LoginService;
import com.xiaoq.utils.MD5Utils;

public class LoginController extends Controller {
	public void index() {
	}

	/**
	 * 登录动作
	 */
	public void login() {
		String user = this.getPara("username");
		String pwd = this.getPara("password");
		// 将传来的password转成md5
		String md5PassWord = MD5Utils.MD5Encode(pwd, "UTF-8", false);
		User userModule = LoginService.getUserInfo(user, pwd);
		JSONObject object = new JSONObject();// 外层json
		JSONObject infos = new JSONObject();// 成功以后的用户信息
		JSONArray data = new JSONArray();// 承载用户信息的array
		if (userModule == null) {// 用户名或密码错误
			object.put("errorCode", 0);
			object.put("msg", "用户名或密码错误");
			object.put("data", data);
			this.renderJson(object);
		} else if (userModule != null
				&& !userModule.get("password").equals(md5PassWord)) {// 密码错误，请核对
			object.put("errorCode", 0);
			object.put("msg", "密码错误，请核对");
			object.put("data", data);
			this.renderJson(object);
		} else {// 登录成功,返回成功登录信息
			object.put("errorCode", 1);
			object.put("msg", "登录成功");
			// 用户信息
			infos.put("id", userModule.getInt("id"));
			infos.put("username", userModule.get("username"));
			infos.put("nickname", userModule.get("nickname"));
			infos.put("sex", userModule.getInt("sex"));
			infos.put("usertype", userModule.getInt("usertype"));
			infos.put("nickname", userModule.get("nickname"));
			infos.put("mobile", userModule.get("mobile"));
			infos.put("score", userModule.getInt("score"));
			infos.put("token", LoginService.insertTOKEN(user));
			// 添加值data数组中
			data.add(infos);
			object.put("data", data);
			this.renderJson(object);
		}
	}
}
