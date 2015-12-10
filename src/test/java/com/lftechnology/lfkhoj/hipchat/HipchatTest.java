package com.lftechnology.lfkhoj.hipchat;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.lftechnology.lfkhoj.domain.Domain;
import com.lftechnology.lfkhoj.domain.DomainService;
import com.lftechnology.lfkhoj.expert.Expert;
import com.lftechnology.lfkhoj.expert.ExpertService;
import com.lftechnology.lfkhoj.question.QuestionService;
import com.lftechnology.lfkhoj.room.RoomRequest;
import com.lftechnology.lfkhoj.room.RoomService;

public class HipchatTest {

	HipchatServiceImpl service;
	DomainService dService;
	ExpertService eService;
	ResponseMessage rMessage;
	Domain domain;
	Expert expert;
	QuestionService qService;
	RoomService rService;

	@Before
	public void setup() {
		service = new HipchatServiceImpl();
		eService = mock(ExpertService.class);
		qService = mock(QuestionService.class);
		rService = mock(RoomService.class);
		

		// when
		Set<String> hashTags = new HashSet<>();
		hashTags.add("Java");
		when(qService.parseHashTag("What is #Java?")).thenReturn(hashTags);
		service.questionService = qService;

		RoomRequest room = new RoomRequest();
		room.setName("hipchat");
		room.setTopic("Java");

		when(rService.create(room)).thenReturn(anyInt());
		service.roomService = rService;
		Set<String> slist = new HashSet<String>();

		when(eService.inviteExpert(anyInt(), slist)).thenReturn(anyString());
		service.expertService = eService;
	}

	@Test
	public void questionTest() {
		// arrange
		RequestMessage rm1 = new RequestMessage();
		rm1.setItem(new RequestMessage.Item());
		rm1.getItem().setMessage(new RequestMessage.Message());
		rm1.getItem().getMessage().setFrom(new RequestMessage.From());
		rm1.getItem().getMessage().getFrom().setName("Ram Tharu");
		rm1.getItem().getMessage().setMessage("\\expert What is #java?");
		rm1.getItem().getMessage().getFrom().setId(1);

		// act
		ResponseMessage command = service.question(rm1);

		// assert
		assertThat(command.getMessage(), is("Expert Succesfully Invited"));

	}

	@Test
	public void removeCommandTest() {
		// arrange
		HipchatService service = new HipchatServiceImpl();
		String question = "/ask What is #Java?";

		// act
		String command = service.removeCommand(question);

		// assert
		assertThat(command, is(" What is Java?"));

	}

}
