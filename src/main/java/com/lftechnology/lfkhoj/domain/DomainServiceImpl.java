package com.lftechnology.lfkhoj.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementing class for DomainService interface
 * 
 */
public class DomainServiceImpl implements DomainService {

	private static final Logger logger = LoggerFactory.getLogger(DomainServiceImpl.class);

	@Inject
	DomainDao domainDao;

	@Override
	public Domain create(String topic) {
		logger.debug("Create Domain of topic " + topic);

		Domain domain = new Domain();
		domain.setTopic(topic);
		return domainDao.create(domain);
	}

	@Override
	public String parseTopic(String message) {
		logger.debug("Parsing '%s' message in parseTopic method.");

		Pattern pattern = Pattern.compile("#([A-Za-z]+\\w*#?)");
		Matcher matcher = pattern.matcher(message);
		if (matcher.find())
			return matcher.group(1);
		else
			return null;
	}

	@Override
	public int delete(String topic) {
		logger.debug("Deletes Domain of topic " + topic);
		return domainDao.delete(topic);
	}

	@Override
	public Domain find(String topic) {
		logger.debug("Find Domain of topic " + topic);
		return domainDao.findByTopic(topic);
	}

}
