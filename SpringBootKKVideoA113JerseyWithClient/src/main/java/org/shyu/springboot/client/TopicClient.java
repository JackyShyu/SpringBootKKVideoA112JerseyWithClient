package org.shyu.springboot.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

import org.shyu.springboot.model.Topic;
import org.springframework.http.MediaType;

public class TopicClient {
	
	public void getAllTopic() {
		Client client = null;
		
		client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/topics");
		List<Topic> topics = target.request(MediaType.APPLICATION_JSON_VALUE).get(new GenericType<List<Topic>>() {});
		topics.stream().forEach(topic -> System.out.println("Id: " + topic.getTopicId() + ", Title: " + topic.getTitle() + ", Category: " + topic.getCategory()));
	}

	public static void main(String[] args) {
		TopicClient client = new TopicClient();
		client.getAllTopic();
	}

}
