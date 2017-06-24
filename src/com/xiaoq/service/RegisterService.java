package com.xiaoq.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.xiaoq.common.Sqls;
import com.xiaoq.module.User;
import com.xiaoq.utils.MD5Utils;

public class RegisterService {

	public Record RegisterUserInfo(String username, String password) {
		// 判断用户名是否存在
		List<User> list = new User().find(Sqls.LOGIN_SQL, username);
		if (list.size() <= 0) {
			// 先将密码进行md5加密
			String md5PassWord = MD5Utils.MD5Encode(password, "UTF-8", false);
			Record user = new Record().set("username", username).set(
					"password", md5PassWord);
			Db.save("t_user", user);
			return user;
		} else {
			return null;
		}

	}

}
