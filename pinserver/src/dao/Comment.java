package dao;

public class Comment {

	
	private String v_username;
	private String v_comment;
	private String v_commenttime;
	private String v_nikname;
	
	public String getV_nikname() {
		return v_nikname;
	}
	public void setV_nikname(String v_nikname) {
		this.v_nikname = v_nikname;
	}
	
	public String getV_commenttime() {
		return v_commenttime;
	}


	public void setV_commenttime(String v_commenttime) {
		this.v_commenttime = v_commenttime;
	}


	public Comment()
	{
		
	}


	public String getV_username() {
		return v_username;
	}


	public void setV_username(String v_username) {
		this.v_username = v_username;
	}


	public String getV_comment() {
		return v_comment;
	}


	public void setV_comment(String v_comment) {
		this.v_comment = v_comment;
	}
	
	
}
