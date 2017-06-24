package com.xiaoq.routes;

import com.jfinal.config.Routes;
import com.xiaoq.controller.LoginController;
import com.xiaoq.controller.RegisterController;

public class UserRoute extends Routes {

	@Override
	public void config() {
		add("/user", LoginController.class);
		add("/regist", RegisterController.class);
	}

}
