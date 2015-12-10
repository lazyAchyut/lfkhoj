package com.lftechnology.lfkhoj.hipchat;

/**
 * HipchatService interface to bind all hipchat services
 * 
 * 
 */

public interface HipchatService {

	/**
	 * Method to call ExpertService
	 *
	 * @param List
	 *            of RequestMessage from Hipchat
	 * @return ResponseMessage
	 */
	ResponseMessage expert(RequestMessage requestMessage);

	/**
	 * Method to call QuestionService
	 *
	 * @param List
	 *            of RequestMessage from Hipchat
	 * @return ResponseMessage
	 */
	ResponseMessage question(RequestMessage requestMessage);

	
	ResponseMessage deleteRoom(RequestMessage requestMessage);
	
	
	
	
	/**
	 * Method to remove slash command i.e /ask
	 * 
	 * @param String question 
	 *                      question with slash command
	 *
	 * @return String question 
	 * 						question without slash command
	 */
	String removeCommand(String question);

	
}
