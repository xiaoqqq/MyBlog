package com.xiaoq.config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.xiaoq.module.User;
import com.xiaoq.routes.UserRoute;

public class MyAppConfig extends JFinalConfig {

	// 用于设置全局的设置如编码等
	@Override
	public void configConstant(Constants me) {
		// 设置为开发模式
		me.setDevMode(true);
		// 设置编码格式 utf8
		me.setEncoding("utf-8");
		// 设置view类型为jsp
		me.setViewType(ViewType.JSP);
	}

	// 用于设置路由地址，访问地址
	@Override
	public void configRoute(Routes me) {
		me.add(new UserRoute());
	}

	@Override
	public void configEngine(Engine me) {
		// TODO Auto-generated method stub

	}

	// 用于配置一些插件，如连接数据的插件
	@Override
	public void configPlugin(Plugins me) {
		// 配置JDBC连接
		PropKit.use("Config.properties");
		final String URL = PropKit.get("jdbcUrl");
		final String USERNAME = PropKit.get("user");
		final String PASSWORD = PropKit.get("password");
		final Integer INITIALSIZE = PropKit.getInt("initialSize");
		final Integer MIDIDLE = PropKit.getInt("minIdle");
		final Integer MAXACTIVEE = PropKit.getInt("maxActivee");
		// Druid插件
		DruidPlugin druidPlugin = new DruidPlugin(URL, USERNAME, PASSWORD);
		druidPlugin.set(INITIALSIZE, MIDIDLE, MAXACTIVEE);
		druidPlugin.setFilters("stat,wall");
		me.add(druidPlugin);

		ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin(
				druidPlugin);
		// 添加Model类和数据库表的映射。
		activeRecordPlugin.addMapping("t_user", "id", User.class);
		me.add(activeRecordPlugin);
	}

	// 用于配置拦截器
	@Override
	public void configInterceptor(Interceptors me) {
		// TODO Auto-generated method stub

	}

	// 用于配置全局拦截器
	@Override
	public void configHandler(Handlers me) {
		me.add(new ContextPathHandler("basePath"));
	}

}
