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

import dao.User;
import db.DBconnection;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		String v_username=request.getParameter("username");
        String v_password=request.getParameter("password");
        //System.out.println(v_username+" "+v_password);
        	
   if(v_username!=null&&v_password!=null)
   {
	 
	    conn=(Connection) DBconnection.getConnector();
		if(conn!=null)
	  {
			String querysql="select v_username from t_user where v_username='"+v_username+"' and v_password='"+v_password+"'";
		    Statement stm=null;
			ResultSet rs=null;
		try
		{
		stm=(Statement) conn.createStatement();
        rs=(ResultSet) stm.executeQuery(querysql);
        JSONObject jsonobj1 = new JSONObject(); 
        JSONObject jsonobj2 = new JSONObject(); 
        
        jsonobj1.put("result","successul");
        jsonobj2.put("result","error: username or password");
    
        if(rs.next())
        {
        	out.println(jsonobj1);
        	System.out.println("登录成功！");
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
        	 JSONObject jsonobj5 = new JSONObject(); 
 			 jsonobj5.put("result","连接异常");
 			 out.println(jsonobj5);
        }
	
      	}
}
