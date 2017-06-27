package com.xiaoq.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.xiaoq.common.Sqls;
import com.xiaoq.module.User;

public class UpdateUserInfoService {

	public Record updateUserInfo(String token, String nickname, int sex,
			String email, String mobile) {
		List<User> list = new User().find(Sqls.UPDATE_USER_INFO, token);
		System.out.println("fuck " + list.size());
		if (list.size() > 0) { // 用户存在
			Record record = Db.findFirst(Sqls.UPDATE_USER_INFO, token);
			record.set("nickname", nickname).set("sex", sex)
					.set("email", email).set("mobile", mobile);
			Db.update("t_user", record);
			return record;
		} else { // 用户不存在
			return null;
		}

	}
}
