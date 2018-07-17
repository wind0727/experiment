package com.thinkgem.jeesite.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.thinkgem.jeesite.common.security.Digests;
import com.thinkgem.jeesite.common.utils.Encodes;
import com.thinkgem.jeesite.modules.experiment.utils.DateUtils;


public class Test {
	
	public static void main(String[] args) throws Exception {
//		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
//		System.out.println(uuid);
		
			String password = "eb0b38f40cc7053c29217f57fe5804e590b6169e7d1018c95cd97921";
			String plain = Encodes.unescapeHtml("admin");
			byte[] salt = Encodes.decodeHex(password.substring(0,16));
			byte[] hashPassword = Digests.sha1(plain.getBytes(), salt, 1024);
			System.out.println(password.equals(Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword)));
		
	}

	public static void main1(String[] args) throws Exception {
		
		 String URL="jdbc:mysql://122.112.238.211:8635/experiment?useUnicode=true&amp;characterEncoding=utf-8";
         String USER="root";
         String PASSWORD="Jxya2018@#";
         //1.加载驱动程序
         Class.forName("com.mysql.jdbc.Driver");
         //2.获得数据库链接
         Connection conn=DriverManager.getConnection(URL, USER, PASSWORD);
         //3.通过数据库的连接操作数据库，实现增删改查（使用Statement类）
         Statement st=conn.createStatement();
         ResultSet rs=st.executeQuery("SELECT id FROM sys_user WHERE id NOT IN (SELECT user_id FROM student) AND user_type=2");
         //4.处理数据库的返回结果(使用ResultSet类)
         while(rs.next()){
        	 String userId = rs.getString("id");
        	 String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        	 
        	 String sql = "INSERT INTO student (id,user_id,create_by,update_by,del_flag) values (?,?,?,?,?)";
        			 
        	Date date = new Date();
        	PreparedStatement pst=conn.prepareStatement(sql);
        	pst.setString(1, uuid);
        	pst.setString(2, userId);
        	pst.setString(3, "1");
//        	pst.setDate(4, date);
        	pst.setString(4, "1");
//        	pst.setDate(6, uuid);
        	pst.setString(5, "0");
        	pst.execute();  
        	System.out.println("uuid ===   " + uuid +  "---insert  成功");
         }
         
         //关闭资源
         rs.close();
         st.close();
         conn.close();

	}
}
