package kr.or.ddit.board.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardVO {
	private int board_no;
	private String board_name;
	private String usable;
	private Date enrollment_date;
	private String enrol_date_str;
	private String user_id;
	
	public BoardVO(){
		
	}
	public String getEncrol_date_str() {
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		this.enrol_date_str = sdf.format(enrollment_date);
		return enrol_date_str;
	}
	
	public BoardVO(String board_name, String usable) {
		this.board_name = board_name;
		this.usable = usable;
	}

	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public String getBoard_name() {
		return board_name;
	}
	public void setBoard_name(String board_name) {
		this.board_name = board_name;
	}
	public String getUsable() {
		return usable;
	}
	public void setUsable(String usable) {
		this.usable = usable;
	}
	public String getEnrollment_date() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(enrollment_date);
	}
	public void setEnrollment_date(Date enrollment_date) {
		this.enrollment_date = enrollment_date;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	
}
