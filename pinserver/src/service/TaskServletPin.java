
package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;

import dao.JsonDateValueProcessor;
import dao.Task;
import dao.User;
import db.DBconnection;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * Servlet implementation class TaskServlet
 */
@WebServlet("/TaskServletPin")
public class TaskServletPin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskServletPin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 得到所有task
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO -generated method stub
		doPost(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 发起task
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		java.sql.ResultSet rs=null;
		PrintWriter out=response.getWriter();
		
		JSONObject res=null;
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonarray=new JSONArray();
		
		JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        
        int i_userid=0;
        int i_taskid=0;
    	String v_username=null;
    	v_username=request.getParameter("username");
    	//v_username="ghj";
    	i_userid=User.getI_userid(v_username);
        int pageNum = 0;// 表示当前要显示的页面数
        int maxPage = 0;// 最大页
        int taskCount = 0;// 得到查询出来的所有数据的数目
        int pageCount=0;//每页的记录数

		try {
			Connection con1=(Connection) DBconnection.getConnector();
			Statement stmt=con1.createStatement();
			rs=stmt.executeQuery("select * from t_task where i_taskid in(select i_taskid  from t_contact where i_userid="+i_userid+")");
		    List<Task> list=new ArrayList<Task>();
		
			while(rs.next())
			{
				Task task=new Task();
				
				task.setI_taskid(rs.getInt("i_taskid"));
		
				task.setD_publishtime((Date)rs.getObject("d_publishtime"));
				
				task.setD_endtime((Date)rs.getObject("d_endtime"));
				
				task.setV_place(rs.getString("v_place"));
				
	         	task.setV_information(rs.getString("v_information"));
	         	
				
				task.setI_personcount(rs.getInt("i_personcount"));
				
				task.setI_current_personcount(rs.getInt("i_current_personcount"));
				
				
				task.setV_username(User.getV_username(rs.getInt("i_userid")));
				
				task.setI_tag(rs.getInt("i_tag"));
				
				task.setGroupID(rs.getString("groupID"));
				
				task.setV_nikname(User.getV_nickname(rs.getInt("i_userid")));
				
				list.add(task);
			}
	
	          if(!list.isEmpty())
	          {
		       Map<String, List<Task>> map = new HashMap<String, List<Task>>();
               map.put("result",list);
			   res=JSONObject.fromObject(map,jsonConfig);
			   out.print(res.toString());
		       out.flush();
	          }		     
	      	else
		     {
			JSONObject jsonobj1 =new JSONObject() ;
			jsonobj1 .put("result","2");
			out.println(jsonobj1);
		     }
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
       
		}
}
