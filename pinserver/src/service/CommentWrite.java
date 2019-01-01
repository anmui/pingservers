package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import dao.JsonDateValueProcessor;
import dao.User;
import dao.Task;
import db.DBconnection;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * Servlet implementation class Register
 */
@WebServlet("/CommentWrite")
public class CommentWrite extends HttpServlet {
    private static final long serialVersionUID = 1L;
   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentWrite() {
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
		
		
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
        int i_userid=0;
        int i_taskid=0;
    	String v_username=null;
    	String v_comment=null;
    	 String v_commenttime=null;
        String taskid=null;
    
     
    	
    	JSONObject jsonobj1 = new JSONObject(); 
		JSONObject jsonobj2 = new JSONObject(); 
		JSONObject jsonobj3 = new JSONObject(); 
		
	    v_username=request.getParameter("v_username");
	    v_comment=request.getParameter("v_comment");	 
	    v_commenttime=request.getParameter("v_commenttime");
	    taskid=request.getParameter("taskid");
    	
    	
	    /**
	     * 测试数据
	     */
//	    taskid="2";
//	    v_username="031602408";
//	    v_comment="Good!";
//	    v_commenttime="2018-12-23 23:04:05";
	    
	    i_taskid=Integer.parseInt(taskid);
	    
   if(v_username!=null)
   {
	
	   i_userid=User.getI_userid(v_username);
	   
	   

		Connection con=null;
		Statement stm=null;
		ResultSet rs=null;
		
		int is=0;
	    String commensql="insert into t_comment(i_userid,i_taskid,v_comment,v_commenttime) values(?,?,?,?)";
		
		PreparedStatement psmt = null;
		
		
		try {
			con=(Connection) DBconnection.getConnector();
			stm=(Statement) con.createStatement();
			psmt = (PreparedStatement) con.prepareStatement(commensql);
	
			psmt.setInt(1, i_userid);
			psmt.setInt(2, i_taskid);
			psmt.setString(3, v_comment);
			psmt.setString(4, v_commenttime);
			int ps=psmt.executeUpdate();
			if(ps>0)
			{
			
				System.out.println("评论成功"+" "+i_userid+" "+i_taskid);
				jsonobj1.put("result","1");
				out.println(jsonobj1);
			}
			else
			     {
				jsonobj1.put("result","2");
				out.println(jsonobj1);
			     }
			
   }catch (SQLException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
   }
	}
	}
}
