package com.lftechnology.lfkhoj.expert;

import java.util.List;
import java.util.Set;

import com.lftechnology.lfkhoj.domain.Domain;

/**
 * Service interface for Expert.
 * 
 *
 */
public interface ExpertService {
	/**
	 * Persists a new Expert to database.
	 * 
	 * @param expert
	 *            New expert to be created
	 * @return Expert object after creation
	 */
	Expert create(Expert expert);

	/**
	 * deletes a Expert from a database.
	 * 
	 * @param id
	 *            expert to be deleted form db
	 * 
	 */
	int delete(int hipChatId);

	/**
	 * find a Expert from a database.
	 * 
	 * @param id
	 *            find expert form db return expert with matched id.
	 */
	Expert find(int hipChatId);

	/**
	 * find a Expert from a database.
	 * 
	 * @param topic
	 *            find expert form db with specified topic. returns list of
	 *            experts.
	 * 
	 */
	List<Expert> findByDomain(String topic);

	/*
	 * invites expert to a chatroom created
	 * 
	 * @param roomId id of a room in which expert is to be added
	 * 
	 * @param expertId id of expert to be added in the room
	 * 
	 */

	String inviteExpert(long roomId, Set<String> expertId);

	/**
	 * Map a given domain to a specified expert.
	 * 
	 * @param expert
	 *            expert in which domain is to be mapped.
	 * @param domain
	 *            domain to be mapped with expert
	 */
	Expert mapWithDomain(Expert expert, Domain domain);
}
