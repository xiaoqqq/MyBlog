package com.xiaoq.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.xiaoq.service.RegisterService;

public class RegisterController extends Controller {

	public void index() {
	}

	// @Before(POST.class)
	// @ActionKey("/regist")
	public void regist() {
		String username = this.getPara("username");
		String password = this.getPara("password");
		RegisterService registerService = new RegisterService();
		Record registerUserInfo = registerService.RegisterUserInfo(username,
				password);
		JSONObject object = new JSONObject();// 外层json
		JSONArray data = new JSONArray();// 承载用户信息的array
		if (registerUserInfo == null) {// 注册失败
			object.put("errorCode", 0);
			object.put("msg", "注册失败,用户名已存在");
			object.put("data", data);
			this.renderJson(object);
		} else { // 成功
			object.put("errorCode", 1);
			object.put("msg", "注册成功");
			object.put("data", data);
			this.renderJson(object);
		}
	}

}
