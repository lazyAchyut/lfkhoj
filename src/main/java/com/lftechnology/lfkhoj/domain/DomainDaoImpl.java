package com.lftechnology.lfkhoj.domain;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class DomainDaoImpl implements DomainDao{

	@Inject
	private EntityManager manager;
	
	@Override
	public Domain create(Domain domain) {
		return manager.merge(domain);
	}

	public int delete(String topic) {
		Query  query = manager.createQuery("delete Domain where topic = :topic");
	    return query.setParameter("topic", topic).executeUpdate();
	}

	public Domain findByTopic(String topic) {
 		TypedQuery<Domain> query = manager.createNamedQuery("domain.topic", Domain.class);
		List<Domain> domains = query.setParameter("topic", topic).getResultList();
		if(domains.size()==0)
			return null;
		else
			return domains.get(0);
				
 	}

}
