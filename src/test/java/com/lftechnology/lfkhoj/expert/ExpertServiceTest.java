package com.lftechnology.lfkhoj.expert;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.lftechnology.lfkhoj.domain.Domain;

public class ExpertServiceTest {

	ExpertServiceImpl service;
	ExpertDao dao;
	Expert expert;
	Domain domain;
	Expert expert1;

	@Before
	public void setup() {
		service = new ExpertServiceImpl();

		expert = new Expert();
		expert.setExpertId(1);

		expert.setHipChatId(12345);
		List<Expert> expertlist = new ArrayList<>();
		expert1 = new Expert();
		dao = mock(ExpertDao.class);
		when(dao.find(12345)).thenReturn(expert);
		when(dao.delete(12345)).thenReturn(1);
		when(dao.findByDomain("domain")).thenReturn(expertlist);
		when(dao.mapWithDomain(expert1, domain)).thenReturn(expert);
		service.expertDao = dao;
	}

	@Test
	public void testCreate() throws Exception {
		// arrange

		// act
		service.create(expert);

		// assert
		verify(dao, times(1)).create(expert);
	}

	@Test
	public void testFind() throws Exception {
		// arrange

		// act
		Expert expert1 = service.find(12345);

		// assert
		assertThat(expert1.getExpertId(), is(1));
		assertThat(expert1.getHipChatId(), is(12345));

	}

	@Test
	public void testDelete() throws Exception {
		;

		// act
		int check = service.delete(12345);
		// assert
		assertThat(check, is(1));
	}

	@Test
	public void findByDomainTest() {
		// act
		List<Expert> eList = service.findByDomain("domain");

		// assert
		assertThat(eList.size(), is(0));
	}

	@Test
	public void mapWithDomain() {

		// act
		Expert expertTest = service.mapWithDomain(expert1, domain);

		// assert
		assertThat(expertTest.getExpertId(), is(1));

	}

	

}
