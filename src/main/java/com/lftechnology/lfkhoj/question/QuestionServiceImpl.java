package com.lftechnology.lfkhoj.question;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lftechnology.lfkhoj.expert.ExpertService;
/**
 * The default class implementing the QuestionService.
 * 
 *
 *
 */

public class QuestionServiceImpl implements QuestionService{

	@Inject
	ExpertService exService;
	
	private static final Logger logger = LoggerFactory.getLogger(QuestionServiceImpl.class);
	
	@Override
	public Set<String> parseHashTag(String question) {
		
		logger.debug("Received question '%s'.", question);
		
		//match the pattern
		Pattern pattern = Pattern.compile("#(\\w+|\\W)");
		Matcher matcher = pattern.matcher(question);
		
		//storing the hashTag
		Set<String> hashTag = new HashSet<>(); 
		while(matcher.find())
		{
			hashTag.add(matcher.group(1));
		}
		
		logger.debug("Stored hashtag '%s'. ", hashTag);
		
		return hashTag;
	}
}
