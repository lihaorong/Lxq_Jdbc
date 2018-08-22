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
	 *���ݿ⹤���࣬��װ��jdbc�ĳ��ò���
	 *1.׼����������className,׼�����ݿ�����Ӳ���url,user,password
	 *2.����Properties����
	 *3.�����������Ķ��� 
	 *4.���������е����ݼ��ص�Properties������
	 *5.������ע����������Driver��ͨ��getProperty()������ȡ�����ļ��е�����
	 *6.��ȡ�����ļ��и����Ӳ���url,user,password��ֵ
	 *7.�ѻ�ȡ���ݿ����Ӷ���Ĳ��������װ��һ������getConnection()
	 *8.�ѹر����ݿⲢ�ͷ���Դ�Ĳ��������װ��һ������colseDb()
	 */
	
	 //1.׼����������className,׼�����ݿ�����Ӳ���url,user,password
     private static String className;
     private static String url;
     private static String username;
     private static String password;
     
     static{
    	 //2.����Properties����
         Properties pro=new Properties();
         InputStream in=null;
         try {
        	//3.�����������Ķ���
			//in=new FileInputStream("jdbc.properties");
        	 in = JdbcUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
			//4.���������е����ݼ��ص�Properties������
			pro.load(in);
		//5.������ע����������Driver��ͨ��getProperty()������ȡ�����ļ��е�����
			className=pro.getProperty("driver");
			Class.forName(className);
			//6.��ȡ�����ļ��и����Ӳ���url,username,password��ֵ
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
     
     //7.�ѻ�ȡ���ݿ����Ӷ���Ĳ��������װ��һ������getConnection()
     public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url, username, password);
	 } 
     
     //8.�ѹر����ݿⲢ�ͷ���Դ�Ĳ��������װ��һ������colseDb()
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
