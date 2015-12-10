package com.lftechnology.lfkhoj.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import lombok.Data;

@Data
@RunWith(Parameterized.class)
public class DomainServiceImplTest {

	DomainServiceImpl service;
	DomainDao dao;
	Domain domain;
	
	//properties for parsing hashtags
	private String expected;
	private String command;
	
	//constructor
	public DomainServiceImplTest(String expected, String command) {
		this.expected = expected;
		this.command = command;
	}
	
	//Data providing method
	@Parameters
	public static Collection<String[]> parseTextParameter(){
		return Arrays.asList(new String[][]{
			{"java", "\\expert #java"},
			{"C#", "\\expert #C#"},
			{"C#", "\\expert #C##"},
			{"css", "\\expert #css #java"},
			{"java", "\\expert css #java c#"},
			{"ror", "\\expert #ror #"},
			{"abc123", "\\expert #abc123"},	
			{null, "\\expert css"},
			{null, "\\expert #$%$%^"},
			{null, "\\expert #123"},
			{null, "\\expert #1css"},
			{null, "\\expert"}
					
		});
	}

	@Before
	public void setup() {
		service = new DomainServiceImpl();

		domain = new Domain();
		domain.setDomainId(0);
		domain.setTopic("topic");

		dao = mock(DomainDao.class);

		// when
		when(dao.findByTopic("topic")).thenReturn(domain);
		when(dao.create(anyObject())).thenReturn(domain);
		when(dao.delete(anyString())).thenReturn(1);
		service.domainDao = dao;
	}

	@Test
	public void testCreate() throws Exception {
		// act
		Domain domain1 = service.create("topic");

		// assert
		verify(dao, times(1)).create(anyObject());
		assertThat(domain1.getTopic(), is("topic"));
		assertThat(domain1.getDomainId(), is(0));
	}

	@Test
	public void testDelete() throws Exception {
		// act
		int check = service.delete(anyObject());

		// assert
		assertThat(check, is(1));
	}

	@Test
	public void testFind() throws Exception {
		// act
		Domain domain = service.find("topic");

		// assert
		assertThat(domain.getDomainId(), is(0));

	}

	@Test
	public void testParseTopic() throws Exception {
		assertThat(service.parseTopic(command), is(expected));
	}

}
