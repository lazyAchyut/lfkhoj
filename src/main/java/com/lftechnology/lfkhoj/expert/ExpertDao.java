package com.lftechnology.lfkhoj.expert;

import java.util.List;

import com.lftechnology.lfkhoj.domain.Domain;

public interface ExpertDao {
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
	 * @param hipChatId
	 *            expert to be deleted form db
	 * 
	 */

	int delete(int hipChatId);

	/**
	 * find a Expert from a database.
	 * 
	 * @param hipChatId
	 *            find expert form db with specified id. returns list of
	 *            experts.
	 * 
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
