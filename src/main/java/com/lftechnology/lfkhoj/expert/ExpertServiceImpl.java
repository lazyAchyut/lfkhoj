package com.lftechnology.lfkhoj.expert;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lftechnology.lfkhoj.common.RoomOperation;
import com.lftechnology.lfkhoj.domain.Domain;
import com.lftechnology.lfkhoj.domain.DomainServiceImpl;

public class ExpertServiceImpl implements ExpertService {

	private static final Logger logger = LoggerFactory.getLogger(DomainServiceImpl.class);

	@Inject
	ExpertDao expertDao;

	@Override
	public Expert create(Expert expert) {
		logger.debug("Create Expert with mentioned attributes");
		return expertDao.create(expert);
	}

	@Override
	public Expert find(int hipChatId) {
		logger.debug("finds expert with mentioned hipChatId " + hipChatId);
		return expertDao.find(hipChatId);
	}

	@Override
	public String inviteExpert(long roomId, Set<String> expertId) {
		logger.debug("Invites Expert in a room ");
		RoomOperation operation = new RoomOperation();

		return operation.inviteExpert(roomId, expertId);
	}

	@Override
	public int delete(int hipChatId) {
		logger.debug("deletes expert with mentioned hipChatId " + hipChatId);
		return expertDao.delete(hipChatId);
	}

	@Override
	public List<Expert> findByDomain(String topic) {
		logger.debug("finds expert with mentioned topic " + topic);
		return expertDao.findByDomain(topic);
	}

	@Override
	public Expert mapWithDomain(Expert expert, Domain domain) {
		return expertDao.mapWithDomain(expert, domain);
		
	}

}
