package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

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

import db.DBconnection;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
     //	System.out.println(request.getRequestURL());
		String v_username=request.getParameter("username");
        String v_password=request.getParameter("password");
        
       System.out.println(v_username+" "+v_password);
        	
//	    String v_username="031602408";
//	    String v_password="161312";
//	    String v_realname="何守成";
        if(v_username!=null&&v_password!=null)
        {
	    conn=(Connection) DBconnection.getConnector();
		if(conn!=null)
	  {
			String regsql="insert into t_user(v_username,v_password) values('"+v_username+"','"+v_password+"')";
			String querysql="select v_username from t_user where v_username='"+v_username+"'";
			Statement stm=null;
			ResultSet rs=null;
			int is=0;
		try
		{
		stm=(Statement) conn.createStatement();
        rs=(ResultSet) stm.executeQuery(querysql);
        JSONObject jsonobj1 = new JSONObject(); 
        JSONObject jsonobj2 = new JSONObject(); 
        JSONObject jsonobj3 = new JSONObject(); 
        jsonobj1.put("result","successul");
        jsonobj2.put("result","username already exist");
		jsonobj3.put("result","注册失败");
        if(rs.next())
        {
        	out.println(jsonobj2);
        }
        else  if(( is=stm.executeUpdate(regsql))!=0)
	        {
	        	 out.println(jsonobj1);
	        	 System.out.println("注册成功！");
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
			JSONObject jsonobj4 = new JSONObject(); 
			jsonobj4.put("result","服务器异常");
			out.println(jsonobj4);
		}
        }
        else 
        {
        	 JSONObject jsonobj5 = new JSONObject(); 
 			 jsonobj5.put("result","连接异常");
 			 out.println(jsonobj5);
        }
	
      	}
}
