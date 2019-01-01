package dao;
import java.util.Date;
public class Task {
	private int i_taskid;
	private Date d_publishtime;
	private Date d_endtime;
	private String v_place;
	private String v_information;
	private int i_personcount;
	private int i_current_personcount;
	//private int i_userid;
	private int i_tag;
	private String groupID;
	private String v_username;
	private String v_nikname;
	
	public String getV_nikname() {
		return v_nikname;
	}
	public void setV_nikname(String v_nikname) {
		this.v_nikname = v_nikname;
	}
	public String getV_username() {
		return v_username;
	}
	public void setV_username(String v_username) {
		this.v_username = v_username;
	}
	public String getGroupID() {
		return groupID;
	}
	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}
	public Date getD_publishtime() {
		return d_publishtime;
	}

	public int getI_taskid() {
		return i_taskid;
	}
	public void setI_taskid(int i_taskid) {
		this.i_taskid = i_taskid;
	}
	public Date getD_publistime() {
		return d_publishtime;
	}
	public void setD_publishtime(Date d_publishtime) {
		this.d_publishtime = d_publishtime;
	}
	public Date getD_endtime() {
		return d_endtime;
	}
	public void setD_endtime(Date d_endtime) {
		this.d_endtime = d_endtime;
	}
	public String getV_place() {
		return v_place;
	}
	public void setV_place(String v_place) {
		this.v_place = v_place;
	}
	public String getV_information() {
		return v_information;
	}
	public void setV_information(String v_information) {
		this.v_information = v_information;
	}
	public int getI_personcount() {
		return i_personcount;
	}
	public void setI_personcount(int i_personcount) {
		this.i_personcount = i_personcount;
	}
	public int getI_current_personcount() {
		return i_current_personcount;
	}
	public void setI_current_personcount(int i_current_personcount) {
		this.i_current_personcount = i_current_personcount;
	}

	public int getI_tag() {
		return i_tag;
	}
	public void setI_tag(int i_tag) {
		this.i_tag = i_tag;
	}


}
