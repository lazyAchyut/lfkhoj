package com.lftechnology.lfkhoj.hipchat;

import java.util.List;

import lombok.Data;
/**
 *  DTO class for Request Message, message format send by Hipchat.
 * 	
 * 


{

   "event": "room_message",

   "item": {

      "message": {

         "date": "2015-10-14T06:15:26.778258+00:00",

         "from": {

            "id": 2716065,

            "links": {

               "self": "https://api.hipchat.com/v2/user/2716065"

            },

            "mention_name": "AchyutPokhrel",

            "name": "Achyut Pokhrel",

            "version": "00000000"

         },

         "id": "53e205e4-44c3-4eba-97c9-5032cc5b5f88",

         "mentions": [],

         "message": "/ask what is java?",

         "type": "message"

      },

      "room": {

         "id": 2023022,

         "links": {

            "participants": "https://api.hipchat.com/v2/room/2023022/participant",

            "self": "https://api.hipchat.com/v2/room/2023022",

            "webhooks": "https://api.hipchat.com/v2/room/2023022/webhook"

         },

         "name": "StudentRoom",

         "version": "O7WN272M"

      }

   },

   "oauth_client_id": "0417a902-0eb8-4271-9246-4b979c00e3f4",

   "webhook_id": 2838389

}


 *
 *
 */

@Data
public class RequestMessage {
	private String event;
	private Item item;
	private int webhook_id;
	private String oauth_client_id;
	
	@Data
	public static class Item {
		private Message message;
		private Room room;
	}

	@Data
	public static class Message {
		private String date;
		private From from;
		private String id;
		private List<String> mentions;
		private String message;
		private String type;
	}

	@Data
	public static class From {
		private int id;
		private String mention_name;
		private Links links;
		private String name;
		private String version;
	}
	
	@Data
	public static class Links{
		private String self;
		private String participants;
		private String webhooks;
	}
	
	@Data
	public static class Room {
		private String name;
		private int id;
		private String version;
		private Links links;
	}

}
