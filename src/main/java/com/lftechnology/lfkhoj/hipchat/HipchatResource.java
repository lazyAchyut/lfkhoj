package com.lftechnology.lfkhoj.hipchat;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lftechnology.lfkhoj.expert.ExpertService;

/**
 * REST end point for Hipchat
 *
 * 
 */

@Path("hipchat")
public class HipchatResource {
	private static final Logger logger = LoggerFactory.getLogger(HipchatResource.class);
	
	@Inject
	HipchatService hipchatService;
	
	@Inject
	ExpertService expertService;

	/**
	 * Forwards /expert request to expert service
	 * 
	 * param: Hipchat JSON body return: ResponseMessage
	 */
	@POST
	@Path("expert")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage expert(RequestMessage requestMessage) {
		logger.debug("expert method invoked with question: '%s' by '%s'.",requestMessage.getItem().getMessage().getMessage(), requestMessage.getItem().getMessage().getFrom().getName());
		
		return hipchatService.expert(requestMessage);
	}

	/**
	 * Forwards /ask request to question service
	 * 
	 * param: Hipchat JSON body return: ResponseMessage
	 */
	@POST
	@Path("ask")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage ask(RequestMessage requestMessage) {
		logger.debug("ask method invoked with question: '%s' by '%s'.",requestMessage.getItem().getMessage().getMessage(), requestMessage.getItem().getMessage().getFrom().getName());

		return hipchatService.question(requestMessage);
	}

	
	
	/**
	 * Forwards /close request to room service
	 * 
	 * @param requestMessage
	 * @return
	 */
	@Path("close")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage closeRoom(RequestMessage requestMessage){
		
		return hipchatService.deleteRoom(requestMessage);
	}
}
