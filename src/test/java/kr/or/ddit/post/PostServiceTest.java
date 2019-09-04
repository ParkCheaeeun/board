package kr.or.ddit.post;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.file.vo.FileVo;
import kr.or.ddit.post.service.PostServiceImpl;
import kr.or.ddit.post.vo.PostVO;

public class PostServiceTest {
	
	private PostServiceImpl service;

	@Before
	public void setUp() throws Exception {
		service = PostServiceImpl.getInstance();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void insertPostTest() {
		/***Given***/
		PostVO vo = new PostVO(2, "궁금한 게 있습니다.", "생각이 안나네요. 다음에 올게요", "moon");
		List<FileVo> fileList = new ArrayList<FileVo>();
		
		/***When***/
		int cnt = service.insertPost(vo,fileList);
		
		/***Then***/
		assertEquals(cnt, 1);
	}
	
	@Test
	public void getPostTest() {
		/***Given***/
		PostVO vo;

		/***When***/
		Map<String, Object> map = service.getPost(2);
		vo = (PostVO) map.get("postVo");
		
		/***Then***/
		assertEquals("brown", vo.getUser_id());
	}
	
	@Test
	public void getPostListTest() {
		/***Given***/
		List<PostVO> list;

		/***When***/
		list = service.getPostList(1);

		/***Then***/
		assertEquals(3, list.size());
	}
	
	@Test
	public void updatePostTest() {
		/***Given***/
		PostVO vo = new PostVO(1, "안녕하세요", "반갑습니다.", "moon");
		List<FileVo> fileList = new ArrayList<FileVo>();
		vo.setPost_no(3);
		
		/***When***/
		int cnt = service.updatePost(vo, fileList);
		
		/***Then***/
		assertEquals(cnt, 1);
	}

}
