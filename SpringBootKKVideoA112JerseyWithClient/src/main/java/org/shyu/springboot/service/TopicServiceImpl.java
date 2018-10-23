package org.shyu.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.shyu.springboot.dao.TopicDao;
import org.shyu.springboot.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TopicServiceImpl implements TopicService {
	@Autowired
	private TopicDao topicDao;
	
	@Override
	public List<Topic> getAllTopics() {		
		List<Topic> topics = new ArrayList<>(topicDao.getAllTopics());
		return topics;
	}

	@Override
	public boolean createTopic(Topic topic) {
		return topicDao.createTopic(topic);
	}

	@Override
	public Topic getTopicById(int id) {
		return topicDao.getTopicById(id);
	}

	@Override
	public Topic updateTopic(Topic topic) {
		return topicDao.updateTopic(topic);
	}

	@Override
	public int deleteTopic(int id) {
		return topicDao.deleteTopic(id);
	}
	
	
}
