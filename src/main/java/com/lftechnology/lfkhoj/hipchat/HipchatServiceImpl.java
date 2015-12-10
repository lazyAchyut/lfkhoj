package com.lftechnology.lfkhoj.hipchat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lftechnology.lfkhoj.domain.Domain;
import com.lftechnology.lfkhoj.domain.DomainService;
import com.lftechnology.lfkhoj.expert.Expert;
import com.lftechnology.lfkhoj.expert.ExpertService;
import com.lftechnology.lfkhoj.question.QuestionService;
import com.lftechnology.lfkhoj.room.MessageLog;
import com.lftechnology.lfkhoj.room.RoomRequest;
import com.lftechnology.lfkhoj.room.RoomService;

/**
 * Implementing class for HipchatService interface
 * 
 * 
 */
public class HipchatServiceImpl implements HipchatService {
	private static final Logger logger = LoggerFactory.getLogger(HipchatServiceImpl.class);

	@Inject
	QuestionService questionService;
	@Inject
	 RoomService roomService;
	@Inject
	ExpertService expertService;
	@Inject
	DomainService domainService;
	@Inject
	private MessageLog messageLog;

	@Override
	@Transactional
	public ResponseMessage expert(RequestMessage requestMessage) {
		logger.debug("expert method invoked with question: '%s' by '%s'.",
				requestMessage.getItem().getMessage().getMessage(),
				requestMessage.getItem().getMessage().getFrom().getName());

		ResponseMessage responseMessage = new ResponseMessage();

		// parse the topic
		String topic = domainService.parseTopic(requestMessage.getItem().getMessage().getMessage());
		if (topic == null) {
			responseMessage.setMessage("Topic cannot be empty.");
			return responseMessage;
		}

		// check if domain already exists, if not create a new one
		Domain domain = domainService.find(topic);
		if (domain == null) {
			domain = new Domain();
			domain = domainService.create(topic);
		}

		// check if this expert already exists, if not create a new one
		Expert expert = expertService.find(requestMessage.getItem().getMessage().getFrom().getId());
		if (expert == null) {
			expert = new Expert();
			expert.setHipChatId(requestMessage.getItem().getMessage().getFrom().getId());
			expert.getDomains().add(domain);
			expert = expertService.create(expert);
			responseMessage.setMessage("New Expert Registered with id:" + expert.getExpertId());
		}
		// map the new domain with the expert
		expertService.mapWithDomain(expert, domain);
		responseMessage.setMessage("Domain mapped successfully");
		return responseMessage;
	}

	@Override
	public ResponseMessage question(RequestMessage requestMessage) {
		logger.debug("question method invoked with question: '%s' by '%s'.",
				requestMessage.getItem().getMessage().getMessage(),
				requestMessage.getItem().getMessage().getFrom().getName());

		ResponseMessage responseMessage = new ResponseMessage();

		// remove /keyword from question
		String question = this.removeCommand(requestMessage.getItem().getMessage().getMessage());

		if (question.trim().isEmpty()) {
			responseMessage.setMessage("Command cannot be empty.");
			return responseMessage;
		}
		// parse hashTag
		Set<String> hashTags = new HashSet<>();
		hashTags = questionService.parseHashTag(requestMessage.getItem().getMessage().getMessage());
		if (hashTags == null) {
			responseMessage.setMessage("Topic must be specified.");
			return responseMessage;
		}

		// create room
		RoomRequest room = new RoomRequest();
		room.setName(question);
		room.setTopic(question);
		int roomId=roomService.create(room);
		
		

		List<Expert> expertList = new ArrayList<Expert>();
		for (String s : hashTags) {
			 expertList=expertService.findByDomain(s);
			
		}

		Set<String> slist = new HashSet<String>();
		for (Expert e : expertList) {
			slist.add(String.valueOf(e.getHipChatId()));
		}

		expertService.inviteExpert(roomId, slist);

		responseMessage.setMessage("Expert Succesfully Invited");
		return responseMessage;
	}

	@Override
	public String removeCommand(String question) {
		logger.debug("Removing command from question '%s'. ", question);
		question = question.replaceFirst("/[A-Za-z]+", "");
		return question.replaceAll("#", "");
	}

	@Override
	public ResponseMessage deleteRoom(RequestMessage requestMessage) {
		int roomId = Integer.parseInt(this.removeCommand(requestMessage.getItem().getMessage().getMessage()).trim());
		messageLog.messageLog(roomService.getMessages(roomId), roomId);
		int statusCode = Integer.parseInt(roomService.delete(roomId));
		if (statusCode == 204) {
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setMessage("Room sucessfully deleted.");
			return responseMessage;
		} else {
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setMessage("Error occurred or room not exists.");
			return responseMessage;
		}
	}
}
