package com.lftechnology.lfkhoj.room;

import com.lftechnology.lfkhoj.common.RoomOperation;

public class RoomServiceImpl implements RoomService {

	RoomOperation operation = new RoomOperation();

	public int create(RoomRequest room) {
		return operation.createRoom(room);
	}

	@Override
	public String delete(int roomId) {
		return operation.delete(roomId);
	}

	@Override
	public String getMessages(int roomId) {
		return operation.getMessages(roomId);
	}
}
