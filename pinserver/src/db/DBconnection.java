package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {
    private static Connection con = null;
    public static Connection getConnector()
 {
       String driver = "com.mysql.jdbc.Driver";
	   String URL = "jdbc:mysql://localhost:3306/pin";
	   String user="root";
	   String password="051600";
	try
	{
		Class.forName(driver);
	}
	catch(java.lang.ClassNotFoundException e)
	{
		System.out.println("Connect Successfull.");
		System.out.println("Cant't load Driver");
	}
	try   
	{                                                                               
		con= DriverManager.getConnection(URL,user,password);
//		if(con!=null)
//		{
//		System.out.println("successful");
//		}
	} 
	catch(Exception e)
	{
		System.out.println("Connect fail:" + e.getMessage());
	}
	return con;

}
}
