package com.xiaoq.common;

/**
 * Sql语句的工具类
 * 
 * @author user
 * 
 */
public class Sqls {

	public static String LOGIN_SQL = "SELECT * FROM t_user WHERE username=?";// sql语句中？可以防止sql注入，多参数多？
	public static String REGIST_SQL = "INSERT INTO t_user(username,PASSWORD) VALUES(?,?)";
}
