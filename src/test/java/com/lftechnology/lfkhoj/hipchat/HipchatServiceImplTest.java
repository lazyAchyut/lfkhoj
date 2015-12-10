package com.lftechnology.lfkhoj.hipchat;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.lftechnology.lfkhoj.domain.Domain;
import com.lftechnology.lfkhoj.domain.DomainService;
import com.lftechnology.lfkhoj.expert.Expert;
import com.lftechnology.lfkhoj.expert.ExpertService;

public class HipchatServiceImplTest {

	HipchatServiceImpl service;
	DomainService dService;
	ExpertService eService;
	RequestMessage rm;
	Domain domain;
	Expert expert;

	@Before
	public void setup() {
		service = new HipchatServiceImpl();
		expert = new Expert();
		domain = new Domain();
		
		//initializing ResponseMessage
		rm = new RequestMessage();
		rm.setItem(new RequestMessage.Item());
		rm.getItem().setMessage(new RequestMessage.Message());
		rm.getItem().getMessage().setFrom(new RequestMessage.From());
		
		//dService
		dService = mock(DomainService.class);
		when(dService.parseTopic("\\expert")).thenReturn(null);
		when(dService.parseTopic("\\expert #java")).thenReturn("java");
		when(dService.parseTopic("\\expert #css")).thenReturn("css");
		when(dService.create("topic")).thenReturn(domain);
		when(dService.find("java")).thenReturn(domain);
		when(dService.find("css")).thenReturn(null);
		service.domainService = dService;

		//eService
		eService = mock(ExpertService.class);
		when(eService.find(1)).thenReturn(expert);
		when(eService.find(2)).thenReturn(null);
		service.expertService = eService;
	}

	@Test
	public void testExpert() {
		// arrange
		rm.getItem().getMessage().getFrom().setName("Achyut Pokhrel");
		rm.getItem().getMessage().setMessage("\\expert #java");
		rm.getItem().getMessage().getFrom().setId(1);

		// act
		ResponseMessage response = service.expert(rm);

		// assert
		verify(dService, times(1)).find(anyString());
		verify(dService, times(0)).create(anyString());
		verify(eService, times(1)).find(anyInt());
		verify(eService, times(0)).create(expert);
		verify(eService, times(1)).mapWithDomain(expert, domain);
		assertThat(response.getMessage(), is("Domain mapped successfully"));
	}

	@Test
	public void testExpertEmptyMessage() {
		// arrange
		rm.getItem().getMessage().getFrom().setName("Achyut Pokhrel");
		rm.getItem().getMessage().setMessage("\\expert");
		rm.getItem().getMessage().getFrom().setId(1);

		// act
		ResponseMessage response = service.expert(rm);

		// assert
		verify(dService, times(0)).find(anyString());
		verify(dService, times(0)).create(anyString());
		verify(eService, times(0)).find(anyInt());
		verify(eService, times(0)).create(anyObject());
		verify(eService, times(0)).mapWithDomain(anyObject(), anyObject());
		assertThat(response.getMessage(), is("Topic cannot be empty."));
	}

	@Test
	public void testExpertNoDomain() {
		// arrange
		rm.getItem().getMessage().getFrom().setName("Achyut Pokhrel");
		rm.getItem().getMessage().setMessage("\\expert #css");
		rm.getItem().getMessage().getFrom().setId(1);

		// act
		ResponseMessage response = service.expert(rm);

		// assert
		verify(dService, times(1)).find(anyString());
		verify(dService, times(1)).create(anyString());
		verify(eService, times(1)).find(anyInt());
		verify(eService, times(0)).create(anyObject());
		verify(eService, times(1)).mapWithDomain(anyObject(), anyObject());
		assertThat(response.getMessage(), is("Domain mapped successfully"));
	}

	@Test
	public void testExpertNoExpert() {
		// arrange
		rm.getItem().getMessage().getFrom().setName("Achyut Pokhrel");
		rm.getItem().getMessage().setMessage("\\expert #java");
		rm.getItem().getMessage().getFrom().setId(2);

		// act
		ResponseMessage response = service.expert(rm);

		// assert
		verify(dService, times(1)).find(anyString());
		verify(dService, times(0)).create(anyString());
		verify(eService, times(1)).find(anyInt());
		verify(eService, times(1)).create(anyObject());
		verify(eService, times(1)).mapWithDomain(anyObject(), anyObject());
		assertThat(response.getMessage(), is("Domain mapped successfully"));
	}

}