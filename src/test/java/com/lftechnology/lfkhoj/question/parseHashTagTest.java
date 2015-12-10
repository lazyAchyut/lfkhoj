package com.lftechnology.lfkhoj.question;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Set;

import org.junit.Test;

public class parseHashTagTest {
	@Test
	public void testParseQuestion() {
		// arrange
		QuestionServiceImpl qsImpl = new QuestionServiceImpl();

		// act
		Set<String> hashTag = qsImpl.parseHashTag("What is #java");
		String[] hash = hashTag.toArray(new String[hashTag.size()]);
		String topic = hash[0];
		
		//assert
		assertThat(topic, is("java"));
	}
}
