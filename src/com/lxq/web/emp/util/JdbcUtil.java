package com.lxq.web.emp.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtil {
	/*
	 *数据库工具类，封装了jdbc的常用操作
	 *1.准备驱动类名className,准备数据库的连接参数url,user,password
	 *2.创建Properties集合
	 *3.创建输入流的对象 
	 *4.把输入流中的数据加载到Properties集合中
	 *5.创建并注册驱动对象：Driver，通过getProperty()方法获取配置文件中的数据
	 *6.获取配置文件中各连接参数url,user,password的值
	 *7.把获取数据库连接对象的操作对外封装成一个方法getConnection()
	 *8.把关闭数据库并释放资源的操作对外封装成一个方法colseDb()
	 */
	
	 //1.准备驱动类名className,准备数据库的连接参数url,user,password
     private static String className;
     private static String url;
     private static String username;
     private static String password;
     
     static{
    	 //2.创建Properties集合
         Properties pro=new Properties();
         InputStream in=null;
         try {
        	//3.创建输入流的对象
			//in=new FileInputStream("jdbc.properties");
        	 in = JdbcUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
			//4.把输入流中的数据加载到Properties集合中
			pro.load(in);
		//5.创建并注册驱动对象：Driver，通过getProperty()方法获取配置文件中的数据
			className=pro.getProperty("driver");
			Class.forName(className);
			//6.获取配置文件中各连接参数url,username,password的值
			url=pro.getProperty("url");
			username=pro.getProperty("username");
			password=pro.getProperty("password");
		 } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(in!=null) in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
     }
     
     //7.把获取数据库连接对象的操作对外封装成一个方法getConnection()
     public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url, username, password);
	 } 
     
     //8.把关闭数据库并释放资源的操作对外封装成一个方法colseDb()
     public static void closeDb(ResultSet rs,Statement state,Connection conn){
    	try {
			if(rs!=null) rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	try {
			if(state!=null) state.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	try {
			if(conn!=null) conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }
     
     public static void closeDb(Statement state,Connection conn){
    	 closeDb(null,state,conn);
      }

}
