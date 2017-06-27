package com.xiaoq.routes;

import com.jfinal.config.Routes;
import com.xiaoq.controller.LoginController;
import com.xiaoq.controller.RegisterController;
import com.xiaoq.controller.UpdateUserInfoController;

public class UserRoute extends Routes {

	@Override
	public void config() {
		add("/user", LoginController.class);
		add("/regist", RegisterController.class);
		add("/userInfo", UpdateUserInfoController.class);
	}

}
