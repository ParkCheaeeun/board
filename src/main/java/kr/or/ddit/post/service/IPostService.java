package kr.or.ddit.post.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.file.vo.FileVo;
import kr.or.ddit.post.vo.PostVO;

public interface IPostService {
	List<PostVO> getPostList(int board_no);
	
	Map<String, Object> getPost(int post_no);
	
	int insertPost(PostVO postVo, List<FileVo> list);
	
	int updatePost(PostVO postVo, List<FileVo> list);
	
	int disabledPost(int post_no);
	
	Map<String, Object> getPostPagingList(Page page);
}
