package dao;



public class Searchk {

	public static boolean keysearch(int d_endtime,int i_time,String v_sex,int i_sex,int i_tag,int i_flag,String v_information,String key) throws Exception {// 输入任务id，剩余结束时间，发布人性别，信息，关键词key	
		boolean r = false;
		boolean b,c,d;	
		int v_sex1;
		if(v_sex=="男") {
			v_sex1=1;
		}
		else {
			v_sex1=2;
		}
		
		
		
		if(key!=null)
		{
		if (v_information.indexOf(key)!=-1) {
			r = true;
		}
		else{
			char [] stringArr = key.toCharArray();
			int a=0;String s;
			if(stringArr.length>1) {				
				for(int i=0;i<stringArr.length;i++) {
					s=Character.toString(stringArr[i]);
					if(v_information.contains(s)) {
						a++;
					}
				}
			}
			if(a>=2)
			{r = true;}
		}
		}
		else r=true;
		
		
		if(d_endtime<=i_time) {//i_time判断时间1今天7一周30三十天
			b = true;
		}
		else {
			b = false;
		}
		
		//检索性别1:男 2:女
		if(i_sex==0)
		{
			c=true;
		}
		else if(i_sex==v_sex1) {//判断性别i_sex0不分，1男，2女
			c = true;
		}
		else {
			c = false;
		}
		
		
		
		if(i_flag==i_tag+1 || i_flag==0) {//数据库:i_tag0外卖1学习2游玩3其他
			d = true;
		}
		else {
			d = false;
		}
		
		
		
		r=r&&b&&c&&d;
		return r;
	}

//	public static void main(String[] args) throws Exception {
//		Searchk s = new Searchk();
//		
//		boolean result = s.keysearch(1,1,"男",0,4,5, t.getV_information(), k);
//		System.out.println("key:" + result);
//	}
}


