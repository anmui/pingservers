package service;



import java.io.IOException;
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
import com.mysql.jdbc.Statement;

import dao.JsonDateValueProcessor;
import dao.RemainingTime;
import dao.Searchk;
import dao.Task;
import dao.User;
import db.DBconnection;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * Servlet implementation class selectStringServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Connection con1=null;
		
		
		int i_time=1;
		int i_sex=0;
		int i_flag=0;
		String key=null;
		String strNum = "1";
		int pageNum = 0;
		int pageCount=0;
		int maxPage=0;
		
		String time=null;
		String sex=null;
		String flag=null;
		
		//�ӿͻ��˻�ȡ��������
		strNum = request.getParameter("pageNum");
		if(strNum==null)
		{
			strNum = "1";
		}
		time=request.getParameter("time");
		sex=request.getParameter("sex");
		flag=request.getParameter("flag");
		//�õ�key
        key=request.getParameter("keyword");
        //key="��Ƭ";
        if(time==null)
		{
			i_time=30;
		}
		else
			i_time=Integer.parseInt(time);
		
		if(sex==null)
		{
			i_sex=0;
		}
		else
		    i_sex=Integer.parseInt(sex);
		
		if(flag==null)
		{
			i_flag=0;
		}
		else
			i_flag=Integer.parseInt(flag);
		
		
		
		JSONObject res=null;
		JSONObject jsonObject = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		try {
			con1=(Connection) DBconnection.getConnector();
			Statement stmt=(Statement) con1.createStatement();
			ResultSet rs=(ResultSet) stmt.executeQuery("select * from t_task order by d_publishtime desc");
			List<Task> list=new ArrayList<Task>();
			
			java.sql.Date sd;
			java.util.Date ud;
			    
			while(rs.next())
			{
				Task task=new Task();
				task.setD_endtime((Date)rs.getObject("d_endtime"));
				
				sd=rs.getDate("d_endtime");
				//����d_endtime
				ud=new java.util.Date(sd.getTime());
				
				//��ʱ���
				System.out.println("ʱ���: "+RemainingTime.remainingTime(ud));
				
				
				task.setD_publishtime((Date)rs.getObject("d_publishtime"));
				task.setI_personcount(rs.getInt("i_personcount"));
				task.setI_tag(rs.getInt("i_tag"));
				//System.out.println("��ǩ: "+rs.getInt("i_tag"));
				
				task.setI_taskid(rs.getInt("i_taskid"));
				
				//System.out.println(rs.getInt("i_taskid"));
				
				task.setV_information(rs.getString("v_information"));
				task.setV_place(rs.getString("v_place"));
				task.setV_username(User.getV_username(rs.getInt("i_userid")));
				task.setI_current_personcount(rs.getInt("i_current_personcount"));
				task.setGroupID(rs.getString("groupID"));
				task.setV_nikname(User.getV_nickname(rs.getInt("i_userid")));
				
				
				if(Searchk.keysearch(RemainingTime.remainingTime(ud), i_time, User.getV_sex(rs.getInt("i_userid")), i_sex, rs.getInt("i_tag"), i_flag, rs.getString("v_information"), key))
				{
					list.add(task);
					System.out.println("�������� "+rs.getInt("i_taskid"));
					
				}
				else 
					System.out.println("����������");
				
			}
		
			
			//��ȡ��¼��
		   
		    if(list.isEmpty())
		    {
		    	//System.out.println("����ʧ��");
		    	response.getWriter().println("����ʧ��");
		    }
		    
		    else
		    {
		    int taskCount = list.size();
		    List<Task> list1=new ArrayList<Task>();
		    // ����ǵ�һ��ִ��,�ͻ���ղ�������
		    if (strNum == null) {
		            strNum = "0";
		        } else {// ���յ����û�����ĵڼ�(pageNum)ҳ
		            pageNum = Integer.parseInt(strNum);
		            System.out.println("pageNum: "+pageNum);
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
		     //����20������
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
	 {
		 for(int i=(taskCount-(taskCount%pageCount));i<taskCount;i++)
         {
        	 list1.add(list.get(i));
         }
	 }
		       Map<String, List<Task>> map = new HashMap<String, List<Task>>();
               map.put("result",list1);
			   res=JSONObject.fromObject(map,jsonConfig);
			   response.getWriter().print(res);
		    }
		     
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
	
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
