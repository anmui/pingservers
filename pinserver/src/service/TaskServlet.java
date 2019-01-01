
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
@WebServlet("/TaskServlet")
public class TaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * �õ�����task
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO -generated method stub
		doPost(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * ����task
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
    
        // ���շ�ҳҳ�洫�ݹ�����ҳ����
        String strNum = request.getParameter("pageNum");
        
        
        if(strNum==null)
		{
			strNum = "1";
		}
        
        int pageNum = 0;// ��ʾ��ǰҪ��ʾ��ҳ����
        int maxPage = 0;// ���ҳ
        int taskCount = 0;// �õ���ѯ�������������ݵ���Ŀ
        int pageCount=0;//ÿҳ�ļ�¼��

		try {
			Connection con1=(Connection) DBconnection.getConnector();
			Statement stmt=con1.createStatement();
			rs=stmt.executeQuery("select * from t_task order by d_publishtime desc");
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
			
			//��ȡ��¼��
		    taskCount=list.size();
		    List<Task> list1=new ArrayList<Task>();
		    // ����ǵ�һ��ִ��,�ͻ���ղ�������
		    if (strNum == null) {
		            strNum = "0";
		            //System.out.println("�����쳣");
		          ;
		        } else {// ���յ����û�����ĵڼ�(pageNum)ҳ
		            pageNum = Integer.parseInt(strNum);
		            //System.out.println("pageNum: "+pageNum);
		        }
		        // �����Ҫ�ֶ���ҳ
		        pageCount=10;//�趨ÿҳ�ļ�¼��
		        if (taskCount % pageCount == 0) {//Ĭ��taskCount>pageCount
		            maxPage = taskCount/pageCount;
		        } else {
		            maxPage = taskCount/pageCount + 1;
		        }
		        
		   
	 if (taskCount % pageCount == 0)		   
		   {
		     //����10������
             for(int i=pageCount*(pageNum-1);i<pageCount*(pageNum-1)+pageCount;i++)
             {
            	 list1.add(list.get(i));
             }
		   }
	 else if(pageNum<maxPage)
	       {
		 for(int i=pageCount*(pageNum-1);i<pageCount*(pageNum-1)+pageCount;i++)
         {
        	 list1.add(list.get(i));
         }
	       }
	 else
		 for(int i=(taskCount-(taskCount%pageCount));i<taskCount;i++)
         {
        	 list1.add(list.get(i));
         }
		       Map<String, List<Task>> map = new HashMap<String, List<Task>>();
               map.put("result",list1);
			   res=JSONObject.fromObject(map,jsonConfig);
		     
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
         out.print(res.toString());
         out.flush();
		}
}
