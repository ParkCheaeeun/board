package kr.or.ddit.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileuploadUtil {
	
	/**
	* Method : getFilenameTest
	* 작성자 : PC-09
	* 변경이력 :
	* Method 설명 : Content-disposition 헤더 문자열로부터 파일이름 추출
	*/
	public static String getFilename(String contentDisposision) {
//		메소드 인자: String contentDisposision = "form-data; name=\"file\"; filename=\"냅.png\"";
		String[] fileNames = contentDisposision.split("; ");
		
		for(String fileName : fileNames) {
			if(fileName.split("=")[0].equals("filename")){
				return fileName.split("=")[1].replace("\"", "");
			}
		}
		
		return null;
	}

	public static String getFileExtension(String contentDisposision) {
		String filename = getFilename(contentDisposision);
		
		if(filename.lastIndexOf(".") > 0) {
			return filename.substring(filename.lastIndexOf("."), filename.length());
		}else {
			return "";
		}
	}

	public static String getpath() {
		String basicPath ="e:\\upload";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String yyyyMM = sdf.format(new Date()); //201908
		String yyyy = yyyyMM.substring(0,4);
		String mm = yyyyMM.substring(4,6);
		
		File yyyyDirectory = new File(basicPath + "\\" + yyyy + "\\" + mm);
		if(!yyyyDirectory.exists()) {
			yyyyDirectory.mkdirs();
			
			return basicPath +  "\\" + yyyy + "\\" + mm + "\\";
		}
		
		return basicPath +  "\\" + yyyy + "\\" + mm + "\\";
	}
	
}
