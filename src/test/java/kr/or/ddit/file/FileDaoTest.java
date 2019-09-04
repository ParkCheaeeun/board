package kr.or.ddit.file;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.file.dao.FileDaoImpl;

public class FileDaoTest {
	
	private FileDaoImpl dao;
	
	@Before
	public void setUp() throws Exception {
		dao = FileDaoImpl.getInstance();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void fileInsertTest() {
		fail("Not yet implemented");
	}

}
