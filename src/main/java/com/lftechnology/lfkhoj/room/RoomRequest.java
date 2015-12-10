package com.lftechnology.lfkhoj.room;

import lombok.Data;
/**
 * 
 * This class includes the entity for creating a new room in Hipchat.
 *
 */
@Data
public class RoomRequest {
	private String topic;
	private String name;
}