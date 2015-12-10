package com.lftechnology.lfkhoj.expert;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.lftechnology.lfkhoj.domain.Domain;

public class ExpertDaoImpl implements ExpertDao {

	@Inject
	private EntityManager manager;

	@Override
	public Expert create(Expert expert) {
		return manager.merge(expert);
		
	}

	@Override
	public int delete(int hipChatId) {
		Query query = manager.createQuery("delete Expert where hipChatId = :hipChatId");
		return query.setParameter("hipChatId", hipChatId).executeUpdate();
	}

	@Override
	public Expert find(int hipChatId) {
		TypedQuery<Expert> query = manager.createQuery("from Expert where hipChatId = :hipChatId",Expert.class);
		List<Expert> experts= query.setParameter("hipChatId", hipChatId).getResultList();
		if(experts.size()==0)
			return null;
		else
			return experts.get(0);
	}

	@Override
	public List<Expert> findByDomain(String topic) {
		TypedQuery<Expert> query=manager.createQuery("select e from Expert e JOIN e.domains d where d.topic = :topic",Expert.class);
		List<Expert> expertList=query.setParameter("topic", topic).getResultList();
		return expertList;
	}

	@Override
	public Expert mapWithDomain(Expert expert, Domain domain) {
		expert.getDomains().add(domain);
		return manager.merge(expert);
		
	}

}
