package com.lftechnology.lfkhoj.question;

import java.util.Set;
/**
 * QuestionService handles all question related operation.
 * 
 * 
 *
 */
public interface QuestionService {
	
    Set<String> parseHashTag(String question);
}
