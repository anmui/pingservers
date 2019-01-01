package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

import dao.JsonDateValueProcessor;
import dao.JsonUtils;
import dao.Task;
import dao.User;
import db.DBconnection;
import javafx.scene.input.DataFormat;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
/**
 * Servlet implementation class TaskServlet
 */
@WebServlet("/Join")
public class Join extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Join() {
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
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		Date date = new Date();
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :HH:mm:ss");
		
		String parse=dateFormat.format(date);
		Date date1=null;
		
		try {
			date1=dateFormat.parse(parse);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JSONObject jsonobj1 = new JSONObject(); 
		JSONObject jsonobj2 = new JSONObject(); 
		JSONObject jsonobj3 = new JSONObject(); 
		
		int i_userid=28;
		int i_taskid=6;
		String v_username=null;
		
		//从客户端获得i_userid和i_taskid
		
	    v_username=request.getParameter("v_username");
	    i_userid=User.getI_userid(v_username);
		i_taskid=Integer.parseInt(request.getParameter("i_taskid"));
	
		System.out.println(v_username+" "+i_taskid);
		
		Connection con=null;
		Statement stm=null;
		ResultSet rs=null;
		
		PrintWriter out=response.getWriter();
		
		jsonobj1.put("result","加入任务成功");
		jsonobj2.put("result","人数已满");
		String inquerysql="select * from t_task where i_taskid="+i_taskid+"";
		int i_personcount=0;
		int i_current_personcount=0;
		int is=0;
	    String joinsql="insert into t_contact(i_userid,i_taskid,i_type,d_createtime) values(?,?,?,?)";
		
		PreparedStatement psmt = null;
		
		String joinupdatesql="update t_task set i_current_personcount=i_current_personcount+1 where i_taskid="+i_taskid+"";
		System.out.println("i_taskid:"+" "+i_taskid+" i_userid"+" "+i_userid);
		
		
		if(User.isExit(i_userid, i_taskid))
		{
			jsonobj3.put("result", "您已加入该任务");
			response.getWriter().println(jsonobj3);
			//System.out.println("不能重复加入");
		}
		else
		{
		try {
			con=(Connection) DBconnection.getConnector();
			stm=con.createStatement();
			psmt = (PreparedStatement) con.prepareStatement(joinsql);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			psmt.setInt(1, i_userid);
			psmt.setInt(2, i_taskid);
			psmt.setInt(3, 2);
			psmt.setObject(4, date1);
			int ps=psmt.executeUpdate();
			if(ps>0)
			{
				is=1;
				//System.out.println("psmt 成功"+" "+ps);
				
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		  
		try
		{
		
			rs=(ResultSet) stm.executeQuery(inquerysql);
			
			if(rs.next())
			{
				i_personcount=rs.getInt("i_personcount")	;
				i_current_personcount=rs.getInt("i_current_personcount");
				//System.out.println("旧的当前人数："+i_current_personcount);
			if(i_current_personcount>=i_personcount)
			{
				//System.out.println("人数已满 "+i_current_personcount+" "+i_personcount);
				out.print(jsonobj2);
			}
			else if(is==1)
			{
		
				if(stm.executeUpdate(joinupdatesql)>0)
				{
				   //  System.out.println("加入任务成功!");
				     //System.out.println("新的当前人数："+(i_current_personcount+1)+" "+"人数限制： "+i_personcount);
				     out.println(jsonobj1);
				}
			}
			else
			{
		        System.out.println("加入任务失败");
			}
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	      }
	}

}
