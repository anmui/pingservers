package service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import dao.User;
import db.DBconnection;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class Register
 */
@WebServlet("/MyInformationUpdate")
public class MyInformationUpdate extends HttpServlet {
    private static final long serialVersionUID = 1L;
   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyInformationUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
  /*

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
     doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    Connection conn=null;
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		String v_username=null;
	    v_username=request.getParameter("v_username");
	    
	    //System.out.print(v_username);
	    
		String v_nickname=request.getParameter("v_nickname");
        String v_grade=request.getParameter("v_grade");
        String v_dormnumber=request.getParameter("v_dormnumber");
        String v_motto=request.getParameter("v_motto");
        String v_major=request.getParameter("v_major");
        String v_sex=request.getParameter("v_sex");
        

        JSONObject jsonobj1 = new JSONObject(); 
        JSONObject jsonobj2 = new JSONObject(); 
        jsonobj1.put("result", "update successful");
        jsonobj2.put("result","update failure");
        JSONObject jsonobj5 = new JSONObject(); 
		jsonobj5.put("result","连接异常");
		
		 //v_username="784374600";
		 
   if(v_username!=null)
   {
	    conn=(Connection) DBconnection.getConnector();
		if(conn!=null)
	  {
			String updatesql="update t_user set v_nickname='"+v_nickname+"',v_grade='"+v_grade+"',v_dormnumber='"+v_dormnumber+"',v_motto='"+v_motto+"',v_major='"+v_major+"',v_sex='"+v_sex+"' where v_username='"+v_username+"'";
		    Statement stm=null;
			int is=0;
		try
		{
		stm=(Statement) conn.createStatement();
        is=stm.executeUpdate(updatesql);
        if(is!=0)
        {
        	out.println(jsonobj1);	
        }
        else
        	out.println(jsonobj2);	
	        }catch(SQLException e)
			{
				e.printStackTrace();
			}        
         }
		else
		{
			JSONObject jsonobj3 = new JSONObject(); 
		    jsonobj3.put("result","服务器异常");
			out.println(jsonobj3);
		}
    }
        else 
        {
 			 out.println(jsonobj5);
        }
	
      	}
}
