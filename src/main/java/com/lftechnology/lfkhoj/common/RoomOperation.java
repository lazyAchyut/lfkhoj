package com.lftechnology.lfkhoj.common;

import java.util.Set;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lftechnology.lfkhoj.Config;
import com.lftechnology.lfkhoj.room.RoomRequest;
import com.lftechnology.lfkhoj.room.RoomResponse;
import com.tngtech.configbuilder.ConfigBuilder;

public class RoomOperation {

	Config config = new ConfigBuilder<Config>(Config.class).build();
	Client client = ClientBuilder.newClient();
	WebTarget target = client.target(config.getHipchatUri());
	private String authToken = "auth_token";

	private static final Logger logger = LoggerFactory.getLogger(RoomOperation.class);

	public int createRoom(RoomRequest room) {
		Response resp = target.path("/v2/room").queryParam(authToken, config.getAuthToken())
				.request(MediaType.APPLICATION_JSON).post(Entity.entity(room, MediaType.APPLICATION_JSON));

		logger.debug("Response message from hipchat after room creation : " + resp.toString());
		
		RoomResponse output = resp.readEntity(RoomResponse.class);
		return output.getId();
	}

	public String delete(int roomId) {

		Response response = (Response) target.path("/v2/room/"+roomId).queryParam("auth_token", config.getAuthToken()).request()
				.accept(MediaType.APPLICATION_JSON).delete();

		logger.debug("Response message from hipchat after room deletion : " + response);

		return String.valueOf(response.getStatus());
	}

	public String getMessages(int roomId) {

		String response = target.path("/v2/room/" + roomId + "/history/latest")
				.queryParam("auth_token", config.getAuthToken()).request().accept(MediaType.APPLICATION_JSON)
				.get(String.class).toString();

		logger.debug("Response message from hipchat room : " + response);

		return response.toString();
	}

	public String inviteExpert(long roomId, Set<String> expertId) {

		logger.debug("Room Id : " + roomId + " , Expert Id : " + expertId);
		Response resp = null;
		for (String s : expertId) {

			resp = target.path("/v2/room/" + roomId + "/invite/" + s).queryParam(authToken, config.getAuthToken())
					.request("application/json").post(Entity.entity("", "application/json"));
		}
		logger.debug("Response message from hipchat after expert invitation : " + resp.toString());

		return resp.toString();
	}

}
