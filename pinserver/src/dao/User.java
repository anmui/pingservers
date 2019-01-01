package dao;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import com.sun.org.apache.regexp.internal.RE;

import db.DBconnection;

public class User {

	public static int getI_userid(String v_username)
	{
		Connection con1=null;
		con1=(Connection) DBconnection.getConnector();
		int i=0;
		String querysql1="select i_userid from t_user where v_username='"+v_username+"'";
	
		Statement stm=null;
		ResultSet rs=null;
		try {
			stm=(Statement) con1.createStatement();
			rs=(ResultSet) stm.executeQuery(querysql1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(rs.next())
			{
			 i= rs.getInt("i_userid");
			 con1.close();
			System.out.println(i);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	
	public static String getV_username(int i_userid)
	{
		Connection con1=null;
		con1=(Connection) DBconnection.getConnector();
	    String v_username=null;
		String querysql1="select v_username from t_user where i_userid='"+i_userid+"'";
	
		Statement stm=null;
		ResultSet rs=null;
		try {
			stm=(Statement) con1.createStatement();
			rs=(ResultSet) stm.executeQuery(querysql1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(rs.next())
			{
				v_username= rs.getString("v_username");
			 con1.close();
			// System.out.println(v_username);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return v_username;
	}
	
	

	
	//通过i_userid获取性别
	public static String getV_sex(int i_userid)
	{
		Connection con1=null;
		con1=(Connection) DBconnection.getConnector();
	    String v_sex=null;
		String querysql1="select v_sex from t_user where i_userid="+i_userid+"";
	
		Statement stm=null;
		ResultSet rs=null;
		try {
			stm=(Statement) con1.createStatement();
			rs=(ResultSet) stm.executeQuery(querysql1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(rs.next())
			{
			v_sex=rs.getString("v_sex");
			con1.close();
			//System.out.println(v_sex);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return v_sex;
		
	}
	
	 public static boolean isExit(int i_userid,int i_taskid)
	 {
		 Connection con1=null;
		 con1=(Connection) DBconnection.getConnector();
		 Statement stm=null;
		 ResultSet rs=null;
		 boolean bool=false;
	     String querysql1="select i_userid,i_taskid from t_contact where i_userid="+i_userid+" and i_taskid="+i_taskid+"";
	     try {
			stm=(Statement) con1.createStatement();
			rs=(ResultSet) stm.executeQuery(querysql1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			if(rs.next())
			{
				bool=true;
				con1.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return bool;
	      
	 }
	 

	 
	 
	 public static String getV_nickname(int i_userid)
	 {
		 Connection con1=null;
			con1=(Connection) DBconnection.getConnector();
			String v_nickname=null;
			String querysql1="select v_nickname from t_user where i_userid="+i_userid+"";
		
			Statement stm=null;
			ResultSet rs=null;
			try {
				stm=(Statement) con1.createStatement();
				rs=(ResultSet) stm.executeQuery(querysql1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(rs.next())
				{
					v_nickname=rs.getString("v_nickname");
					con1.close();
				    System.out.println(v_nickname);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return v_nickname;
	 }
	
}
