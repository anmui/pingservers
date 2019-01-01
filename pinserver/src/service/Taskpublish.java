package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
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
import com.mysql.jdbc.Statement;

import dao.JsonDateValueProcessor;
import dao.JsonUtils;
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
@WebServlet("/Taskpublish")
public class Taskpublish extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Taskpublish() {
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
				Connection con1=(Connection) DBconnection.getConnector();
				JSONObject jsonobj1 = new JSONObject(); 
				JSONObject jsonobj2 = new JSONObject(); 
				JSONObject jsonobj3 = new JSONObject(); 
				JSONObject jsonobj4 = new JSONObject(); 
				PrintWriter out=response.getWriter();
				int i_userid=0;
				int taskid=0;
				Task task=new Task();
				//得到客户端task
				String strjson=request.getParameter("task"); 
		       // strjson="{\"result\":[{\"d_endtime\":\"2018-11-27 12:45:00\",\"i_personcount\":2,\"i_tag\":2,\"v_information\":\"福大 \",\"v_place\":\"fuda \",\"v_username\":\"dcvbbhhh \",\"groupID\":\"28873433\"}]}";
			    
			    if(strjson!=null)
			    {
			    	List<Task> list=new ArrayList<Task>();
			    	try {
						list=JsonUtils.getvips(strjson);
					} catch (ParseException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
			    	
			    	
			    	if(con1!=null)
			    	{
						try {
						//取得taskid
					    String getid="select max(i_taskid) from t_task";
				
					    Statement stmt=(Statement) con1.createStatement();
						ResultSet rs=(ResultSet) stmt.executeQuery(getid);
						
						while(rs.next())
						{ 
						taskid=rs.getInt(1);
						}
						taskid+=1;
					
						//插入数据库
					    String sql="insert into t_task(d_endtime,d_publishtime,i_personcount,i_current_personcount,i_tag,i_taskid,v_information,v_place,i_userid,groupID) values(?,?,?,?,?,?,?,?,?,?)";
						PreparedStatement psmt = null;
						psmt = (PreparedStatement) con1.prepareStatement(sql);
			
						
						for(int i=0;i<list.size();i++)
					    {

							
							psmt.setObject(1, list.get(i).getD_endtime());				
							psmt.setObject(2, list.get(i).getD_publistime());
							psmt.setInt(3, list.get(i).getI_personcount());
							psmt.setInt(4, 0);
							psmt.setInt(5, list.get(i).getI_tag());
							psmt.setInt(6, taskid);
							psmt.setString(7, list.get(0).getV_information());
							psmt.setString(8, list.get(i).getV_place());
							psmt.setInt(9, User.getI_userid(list.get(i).getV_username()));
				            psmt.setString(10, list.get(i).getGroupID());
							if(psmt.executeUpdate()>=1)
							{
								
								jsonobj1.put("result","发布任务成功");
								System.out.println(taskid);
								out.print(jsonobj1);
							}
							else
							{
								jsonobj2.put("result","请稍后再试");
								out.println(jsonobj2);
							}
								
					    }
						}
						 catch (SQLException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
				    }
				    else
				    {
				    	jsonobj3.put("result","服务器异常");
				    	out.println(jsonobj3);
				    }
				}
				else
				{
		    	jsonobj4.put("result","连接异常");
		    	out.println(jsonobj4);
				}

}
}
