package kr.or.ddit.file.vo;

public class FileVo {
	private int file_no;
	private String upload_file_name;
	private String real_file_route;
	private int post_no;
	
	public int getFile_no() {
		return file_no;
	}
	public void setFile_no(int file_no) {
		this.file_no = file_no;
	}
	public String getUpload_file_name() {
		return upload_file_name;
	}
	public void setUpload_file_name(String upload_file_name) {
		this.upload_file_name = upload_file_name;
	}
	public String getReal_file_route() {
		return real_file_route;
	}
	public void setReal_file_route(String real_file_route) {
		this.real_file_route = real_file_route;
	}
	public int getPost_no() {
		return post_no;
	}
	public void setPost_no(int post_no) {
		this.post_no = post_no;
	}
	
}
