package com.xiaoq.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.xiaoq.service.UpdateUserInfoService;

/**
 * 更新用户信息的controller
 * 
 * @author user
 * 
 */
public class UpdateUserInfoController extends Controller {
	public void index() {
	}

	public void update() {
		String token = this.getPara("token");
		String nickname = this.getPara("nickname");
		int sex = this.getParaToInt("sex");
		String email = this.getPara("email");
		String mobile = this.getPara("mobile");
		UpdateUserInfoService updateUserInfoService = new UpdateUserInfoService();
		Record updateUserInfo = updateUserInfoService.updateUserInfo(token,
				nickname, sex, email, mobile);
		JSONObject object = new JSONObject();// 外层json
		JSONObject infos = new JSONObject();// 成功以后的用户信息
		JSONArray data = new JSONArray();// 承载用户信息的array
		if (updateUserInfo == null) { // 更新失败
			object.put("errorCode", 0);
			object.put("msg", "更新失败,没找到相应的用户信息");
			object.put("data", data);
			this.renderJson(object);
		} else { // 成功
			object.put("errorCode", 1);
			object.put("msg", "更新成功");
			object.put("data", data);
			this.renderJson(object);
			// 用户信息
			infos.put("username", updateUserInfo.get("username"));
			infos.put("nickname", updateUserInfo.get("nickname"));
			infos.put("sex", updateUserInfo.getInt("sex"));
			infos.put("usertype", updateUserInfo.getInt("usertype"));
			infos.put("nickname", updateUserInfo.get("nickname"));
			infos.put("mobile", updateUserInfo.get("mobile"));
			infos.put("score", updateUserInfo.getInt("score"));
			infos.put("token", updateUserInfo.get("token"));
			infos.put("id", updateUserInfo.getInt("id"));
			// 添加值data数组中
			data.add(infos);
			object.put("data", data);
			this.renderJson(object);
		}
	}
}
