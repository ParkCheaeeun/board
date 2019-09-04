package kr.or.ddit.reply.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReplyVO {
	private int reply_no;
	private String content;
	private Date write_date;
	private String write_date_str;
	private int post_no;
	private String user_id;
	private String usable;
	
	public String getWrite_date_str() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		write_date_str = sdf.format(write_date);
		return write_date_str;
	}
	
	public String getUsable() {
		return usable;
	}
	public void setUsable(String usable) {
		this.usable = usable;
	}
	public int getReply_no() {
		return reply_no;
	}
	public void setReply_no(int reply_no) {
		this.reply_no = reply_no;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getWrite_date() {
		return write_date;
	}
	public void setWrite_date(Date write_date) {
		this.write_date = write_date;
	}
	public int getPost_no() {
		return post_no;
	}
	public void setPost_no(int post_no) {
		this.post_no = post_no;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
}
