package com.lftechnology.lfkhoj.domain;

/**
 * Interface declaring methods for Domain service
 * 
 *
 */
public interface DomainService {

	/**
	 * Method to create new domain object
	 * 
	 * @param topic
	 *            to create new domain
	 * @return Domain object
	 */
	Domain create(String topic);

	/**
	 * deletes a domain from a database.
	 * 
	 * @param id
	 *            expert to be deleted form db
	 * 
	 */

	int delete(String topic);
	
	/**
	 *  Method to extract topic from message
	 *  
	 *  @param Message string from hipchat
	 *  
	 *  @return Single topic or null
	 */
	
	String parseTopic(String message);
	/**
	 * find a Domain from a database.
	 * 
	 * @param topic
	 *            find domain form db with specified topic. returns list of
	 *            domains.
	 * 
	 */
	Domain find(String topic);
}
