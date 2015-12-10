package com.lftechnology.lfkhoj.room;

import javax.ws.rs.core.Response;

import lombok.Getter;
import lombok.Setter;
/**
 * This class maps the json messages received from HipChat after the room is created.
 *
 *
 */
@Getter
@Setter
public class RoomResponse {

	public Entity entity;
	private int id;
	public Links links;
	
	@Getter
	@Setter
	public static class Entity{
		private int id;
		public Links links;
		private String name;
		private String version;

		public Entity() {}
		
		public Entity(int id, Links links, String name, String version) {
			this.id = id;
			this.links = links;
			this.name = name;
			this.version = version;
		}

		@Getter
		@Setter
		public static class Links{
			private String participants;
			private String self;
			private String webhooks;
			
			public Links() {}
			
			public Links(String participants, String self, String webhooks) {
				this.participants = participants;
				this.self = self;
				this.webhooks = webhooks;
			}
		}
	}
	
	@Getter
	@Setter
	public static class Links{
		private String self;
		
		public Links() {}

		public Links(String self) {
			this.self = self;
		}
	}

	public RoomResponse() {}

	public RoomResponse(Response resp) {}
	
	public RoomResponse(Entity entity, int id, Links links) {
		this.entity = entity;
		this.id = id;
		this.links = links;
	}
}
