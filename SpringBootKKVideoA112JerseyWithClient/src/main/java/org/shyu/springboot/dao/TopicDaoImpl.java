package org.shyu.springboot.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.shyu.springboot.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class TopicDaoImpl implements TopicDao {
	@Autowired
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Topic> getAllTopics() {
		List<Topic> topics = new ArrayList<>();
		
		//String HQL = "FROM Topic as t ORDER BY t.topicId";
		//topics = entityManager.createQuery(HQL, Topic.class).getResultList();
		
		String SQL = "select * from topics order by topics.topic_Id";
		topics = entityManager.createNativeQuery(SQL, Topic.class).getResultList();
		
		//topics = entityManager.createNamedQuery("Topic.findAllTopicRecords", Topic.class).setParameter("title1", "Spring%").getResultList();
		
		return topics;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean topicExists(String title, String category) {
		String SQL = "select * from topics where topics.title = :title1 and topics.category = :category1";
		List<Topic> topics = entityManager.createNativeQuery(SQL, Topic.class)
								.setParameter("title1", title)
								.setParameter("category1", category)
								.getResultList();
		if (topics.size() == 1) {
			return true;
		} else {
			return false;
		}	
	}

	@Override
	public boolean createTopic(Topic topic) {
		boolean isCreated = false;
		boolean exists = topicExists(topic.getTitle(), topic.getCategory());
		if (exists) {
			isCreated = false;
		} else {
			entityManager.persist(topic);
			isCreated = true;
		}	
		return isCreated;
	}

	@Override
	public Topic getTopicById(int id) {
		return entityManager.find(Topic.class, id);
	}

	@Override
	public Topic updateTopic(Topic topic) {
		Topic topicDB = getTopicById(topic.getTopicId());
		if (topicDB != null) {
			topicDB.setCategory(topic.getCategory());
			topicDB.setTitle(topic.getTitle());
			entityManager.flush();
		}
		return topicDB;
	}

	@Override
	public int deleteTopic(int id) {
		String SQL = "delete from topics where topics.topic_id = :topicId";
		int numberOfDeleted = entityManager.createNativeQuery(SQL)
						.setParameter("topicId", id)
						.executeUpdate();
		return numberOfDeleted;
	}
	
	
}
