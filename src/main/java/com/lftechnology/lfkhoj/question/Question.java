package com.lftechnology.lfkhoj.question;

import java.util.ArrayList;
import java.util.List;

import com.lftechnology.lfkhoj.expert.Expert;

public class Question {

	private int roomId;
	
	private String question;
	
	private List<Expert> list =new ArrayList<>();

	public List<Expert> getList() {
		return list;
	}

	public void setList(List<Expert> list) {
		this.list = list;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	
	
}
