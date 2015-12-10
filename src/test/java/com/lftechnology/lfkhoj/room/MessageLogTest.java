package com.lftechnology.lfkhoj.room;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;


public class MessageLogTest {

	@Test
	public void testMessageLog(){
		//arrange
		MessageLog messageLog = new MessageLog();
		String message = "Hello World";
		int roomId = 12345;
		
		//act
		messageLog.messageLog(message, roomId);
		
		//assert
		assertThat(message,is("Hello World"));
		assertThat(roomId, is(12345));
	}
	
	@Test
	public void testMessageLog2(){
		//arrange
		MessageLog messageLog = new MessageLog();
		String message = "";
		int roomId = 0;
		
		//act
		messageLog.messageLog(message, roomId);
		
		//assert
		assertThat(message, is(""));
		assertThat(roomId, is(0));
	}
}