package dao;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtils {
	public static List<Task> getvips(String js) throws ParseException 
	{
		JSONObject jsonObject = JSONObject.fromObject(js);
        JSONArray ja = jsonObject.getJSONArray("result");
		List<Task> list=new ArrayList<Task>();
		for(int i=0;i<ja.size();i++)
		{
			JSONObject jo1 = ja.getJSONObject(i);
			Task task=new Task();
			//System.out.println(jo1.get("d_endtime"));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			Date et=sdf.parse((String) jo1.get("d_endtime"));
			
			//Date pm=sdf.parse((String) jo1.get("d_publishtime"));
			//获取当前时间作为d_publishtime
			Date date = new Date();
			SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :HH:mm:ss");
			
			String parse=dateFormat.format(date);
			Date pm=null;
			
			try {
				pm=dateFormat.parse(parse);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//System.out.println(pm);
			
			
			task.setD_endtime(et);
			task.setD_publishtime(pm);
			task.setI_personcount(jo1.getInt("i_personcount"));
			task.setI_tag(jo1.getInt("i_tag"));
			//task.setI_taskid(jo1.getInt("i_taskid"));
			task.setV_information(jo1.getString("v_information"));
			task.setV_place(jo1.getString("v_place"));
			//System.out.println(jo1.getString("v_username"));
			task.setV_username(jo1.getString("v_username"));
		    task.setGroupID(jo1.getString("groupID"));
			list.add(task);
			
		}
		return list;
	}

}
