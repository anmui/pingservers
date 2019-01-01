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

import dao.Comment;
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
@WebServlet("/CommentDisplay")
public class CommentDisplay extends HttpServlet {
    private static final long serialVersionUID = 1L;

   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentDisplay() {
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
				JSONObject jsonObject = new JSONObject();
				JSONObject jsonobj1 = new JSONObject(); 
				String res=null;
				
				
		        int i_taskid=0;
		        
		        String taskid=null;
		    	
		    
		        taskid=request.getParameter("taskid");
		        
		        //taskid="7";
			
		       List<Comment> list=new ArrayList<Comment>();
			    
		        
		   if(taskid!=null)
		   {
			
			    i_taskid=Integer.parseInt(taskid);
			
			   
				Connection con=null;
				Statement stm=null;
				ResultSet rs=null;
				
			
				int is=0;
			    String commentsql="select * from t_comment where i_taskid="+i_taskid+"";
				//String joinsql="insert into t_contact(i_userid,i_taskid,i_type) values(?,?,?)";
				
				PreparedStatement psmt = null;
				
				
				try {
					con=(Connection) DBconnection.getConnector();
					stm=(Statement) con.createStatement();
				    rs=(ResultSet) stm.executeQuery(commentsql);
					
					while(rs.next())
					{
			        Comment comment=new Comment();
					comment.setV_username(User.getV_username(rs.getInt("i_userid")));
					comment.setV_comment(rs.getString("v_comment"));
					comment.setV_commenttime(rs.getString("v_commenttime"));
					comment.setV_nikname(User.getV_nickname(rs.getInt("i_userid")));
					list.add(comment);	
					//System.out.println("评论显示成功"+" "+User.getV_username(rs.getInt("i_userid"))+" "+i_taskid);
								
					}
					if(!list.isEmpty())
					{
						System.out.print(list);
				       Map<String, List<Comment>> map = new HashMap<String, List<Comment>>();
		               map.put("result",list);
					   res=JSONObject.fromObject(map).toString();
		
							
							out.println(res);
							out.flush();
			
		
					}
					else
					     {
						jsonobj1.put("result","3");
						out.println(jsonobj1);
					     }
					
		   }catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
		   }
				
			}
		   else
		   {
			   jsonobj1.put("result","2");
				out.println(jsonobj1);
		   }
  }
}
