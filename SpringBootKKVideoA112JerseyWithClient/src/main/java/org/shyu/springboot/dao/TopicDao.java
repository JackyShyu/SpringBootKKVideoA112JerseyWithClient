package org.shyu.springboot.dao;

import java.util.List;

import org.shyu.springboot.model.Topic;

public interface TopicDao {
	public abstract List<Topic> getAllTopics();
	public abstract boolean topicExists(String title, String category);
	public abstract boolean createTopic(Topic topic);
	public abstract Topic getTopicById(int id);
	public abstract Topic updateTopic(Topic topic);
	public abstract int deleteTopic(int id);
}
