package com.xiaoq.service;

import com.jfinal.plugin.activerecord.Db;
import com.xiaoq.common.Sqls;
import com.xiaoq.module.User;
import com.xiaoq.utils.TokenUtil;

public class LoginService {
	/**
	 * 获取用户信息
	 * 
	 * @return
	 */
	public static User getUserInfo(String username, String pwd) {
		User user = new User().findFirst(Sqls.LOGIN_SQL, username);// 使用findFirst来实现指定查找，并且查找到的数据会以反射的形式来给User实体类
		return user;// 返回User实体类
	}

	/**
	 * 插入token验证
	 * 
	 * @return
	 */
	public static String insertTOKEN(String username) {
		String key = TokenUtil.generalKey();
		String sql = " UPDATE t_user SET token=? WHERE username=?";
		Db.update(sql, key, username);// 不关联任何实体类的方法，其中有增删改查方法，可以自己来实现下看看
		return key;
	}
}
