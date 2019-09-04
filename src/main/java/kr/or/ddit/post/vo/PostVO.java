package kr.or.ddit.post.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PostVO {
	private int post_no;
	private int board_no;
	private String post_title, post_content, user_id;
	private Date write_date;
	private String write_date_str;
	private int parent_post_no, group_no;
	private String usable;
	
	public PostVO(){
		
	}
	
	public PostVO(int board_no, String post_title, String post_content, String user_id){
		this.board_no = board_no;
		this.post_title = post_title;
		this.post_content = post_content;
		this.user_id = user_id;
	}
	
	public String getWrite_date_str() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		this.write_date_str = sdf.format(write_date);
		return write_date_str;
	}
	
	public String getUsable() {
		return usable;
	}

	public void setUsable(String usable) {
		this.usable = usable;
	}

	public void setWrite_date_str(String write_date_str) {
		this.write_date_str = write_date_str;
	}

	public int getPost_no() {
		return post_no;
	}
	public void setPost_no(int post_no) {
		this.post_no = post_no;
	}
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public String getPost_title() {
		post_title = post_title.replace(" ", "&nbsp");
		return post_title;
	}
	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}
	public String getPost_content() {
		return post_content;
	}
	public void setPost_content(String post_content) {
		this.post_content = post_content;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Date getWrite_date() {
		return write_date;
	}
	public void setWrite_date(Date write_date) {
		this.write_date = write_date;
	}
	public int getParent_post_no() {
		return parent_post_no;
	}
	public void setParent_post_no(int parent_post_no) {
		this.parent_post_no = parent_post_no;
	}
	public int getGroup_no() {
		return group_no;
	}
	public void setGroup_no(int group_no) {
		this.group_no = group_no;
	}
	
	
}
