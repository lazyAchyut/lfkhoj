package com.lftechnology.lfkhoj.domain;

public interface DomainDao {
	/**
	 * 
	 * @param domain
	 *            to create new topic
	 * @return Domain
	 */
	Domain create(Domain domain);

	/**
	 * deletes a domain from a database.
	 * 
	 * @param topic
	 *            domain to be deleted form db
	 * 
	 */

	int delete(String topic);
	/**
	 * find a domain from a database.
	 * 
	 * @param topic
	 *            find domain form db with specified topic. returns list of
	 *            domains.
	 * 
	 */
	Domain findByTopic(String topic);
}
