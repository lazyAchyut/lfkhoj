package com.lftechnology.lfkhoj.room;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class MessageLog {

	public void messageLog(String message, int roomId){
		try {
			PrintWriter writer = new PrintWriter(String.valueOf(roomId));
			writer.print(message);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
