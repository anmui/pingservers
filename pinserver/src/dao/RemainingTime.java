package dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RemainingTime {

	public static int remainingTime(Date endTime)
	{
	
		long time=0;
		 Date currentTime=new Date();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String formatTime=sdf.format(currentTime);
		 Date nowTime = null;
		try {
			nowTime = sdf.parse(formatTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 time =  (endTime.getTime()-nowTime.getTime())/86400000;
		 time=Math.abs(time);
		 return (int) time;
	}
	
	
}
