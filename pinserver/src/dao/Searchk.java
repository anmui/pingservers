package dao;



public class Searchk {

	public static boolean keysearch(int d_endtime,int i_time,String v_sex,int i_sex,int i_tag,int i_flag,String v_information,String key) throws Exception {// ��������id��ʣ�����ʱ�䣬�������Ա���Ϣ���ؼ���key	
		boolean r = false;
		boolean b,c,d;	
		int v_sex1;
		if(v_sex=="��") {
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
		
		
		if(d_endtime<=i_time) {//i_time�ж�ʱ��1����7һ��30��ʮ��
			b = true;
		}
		else {
			b = false;
		}
		
		//�����Ա�1:�� 2:Ů
		if(i_sex==0)
		{
			c=true;
		}
		else if(i_sex==v_sex1) {//�ж��Ա�i_sex0���֣�1�У�2Ů
			c = true;
		}
		else {
			c = false;
		}
		
		
		
		if(i_flag==i_tag+1 || i_flag==0) {//���ݿ�:i_tag0����1ѧϰ2����3����
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
//		boolean result = s.keysearch(1,1,"��",0,4,5, t.getV_information(), k);
//		System.out.println("key:" + result);
//	}
}


