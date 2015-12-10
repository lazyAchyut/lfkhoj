package com.lftechnology.lfkhoj.room;

import java.io.IOException;

/**
 * RoomService interface includes all the methods related to the HipChat room.
 */
public interface RoomService {
	/**
	 * 
	 * @param room:
	 *            Passes the room object for creating a new Hipchat room.
	 * @return a JSON related to the newely created room.
	 * @throws IOException
	 */
	int create(RoomRequest room);

	/**
	 * 
	 * @return a JSON related with the deleted room.
	 * @throws IOException
	 */
	String delete(int roomId);

	/**
	 * 
	 * @return a JSON string with all the messages from the room.
	 * @throws IOException
	 */
	String getMessages(int roomId);
}
