package service;

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
@WebServlet("/MyInformation")
public class MyInformation extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyInformation() {
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
	    //v_username="031602408";
        JSONObject jsonobj1 = new JSONObject(); 
        JSONObject jsonobj2 = new JSONObject(); 
        JSONObject jsonobj4 = new JSONObject(); 
        jsonobj2.put("result","query failure");
        jsonobj4.put("result", "successful");
        JSONObject jsonobj5 = new JSONObject(); 
		jsonobj5.put("result","连接异常");
   if(v_username!=null)
   {
	    
	    conn=(Connection) DBconnection.getConnector();
		if(conn!=null)
	  {
			String querysql="select * from t_user where v_username='"+v_username+"'";
		    Statement stm=null;
			ResultSet rs=null;
		try
		{
		stm=(Statement) conn.createStatement();
        rs=(ResultSet) stm.executeQuery(querysql);
       
  
        while(rs.next())
        {
        	
        	jsonobj1.put("v_nickname",rs.getString("v_nickname"));
        	jsonobj1.put("v_dormnumber", rs.getString("v_dormnumber"));
        	jsonobj1.put("v_motto", rs.getString("v_motto"));
        	jsonobj1.put("v_grade", rs.getString("v_grade"));
        	jsonobj1.put("v_sex", rs.getString("v_sex"));
        	jsonobj1.put("v_major", rs.getString("v_major"));
        }
        if(!jsonobj1.isEmpty())
        {
        	out.println(jsonobj1);
        	out.flush();
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
