package org.shyu.springboot.service;

import java.util.List;

import org.shyu.springboot.model.Topic;

public interface TopicService {
	public abstract List<Topic> getAllTopics();
	public abstract boolean createTopic(Topic topic);
	public abstract Topic getTopicById(int id);
	public abstract Topic updateTopic(Topic topic);
	public abstract int deleteTopic(int id);
}
