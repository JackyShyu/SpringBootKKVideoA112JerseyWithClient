package org.shyu.springboot.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

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
	
	public void getTopic(int topicId) {
		Client client = null;
		
		client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/topics/topic");
		WebTarget path = target.path("{topicId}").resolveTemplate("topicId", topicId);
		Topic topic = path.request(MediaType.APPLICATION_JSON_VALUE).get(Topic.class);
		System.out.println("Id: " + topic.getTopicId() + ", Title: " + topic.getTitle() + ", Category: " + topic.getCategory());
	}
	
	public void createTopic(Topic topic) {
		Client client = null;
		
		client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/topics/create");
		Response response = target.request(MediaType.APPLICATION_JSON_VALUE).post(Entity.json(topic));
		System.out.println(response.getEntity().toString());
		//Topic topicAfter = ((Topic) response.getEntity());
		//System.out.println("Id: " + topicAfter.getTopicId() + ", Title: " + topicAfter.getTitle() + ", Category: " + topicAfter.getCategory());
		System.out.println("Status: " + response.getStatus());
		System.out.println("Location: " + response.getLocation());
	}
	
	public void updateTopic(Topic topic) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/topics/update");
		Response response = target.request(MediaType.APPLICATION_JSON_VALUE).put(Entity.json(topic));
		System.out.println("Status: " + response.getStatus());
		System.out.println("Location: " + response.getLocation());
	}
	
	public void deleteTopic(int topicId) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/topics/delete").path("/{topicId}").resolveTemplate("topicId", topicId);
		Response response = target.request().delete();
		System.out.println("Status: " + response.getStatus());
		System.out.println("Location: " + response.getLocation());
	}

	public static void main(String[] args) {
		TopicClient jersey = new TopicClient();
		//jersey.getAllTopic();
		//jersey.getTopic(1);
		//jersey.createTopic(newTopic());
		//jersey.updateTopic(updateTopic());
		jersey.deleteTopic(4);
	}

	private static Topic newTopic() {
		Topic topic = new Topic();
		topic.setTopicId(4);
		topic.setCategory("Java");
		topic.setTitle("Lumbda Expression");
		return topic;
	}
	
	private static Topic updateTopic() {
		Topic topic = new Topic();
		topic.setTopicId(4);
		topic.setCategory("Java updated");
		topic.setTitle("Lumbda Expression updated");
		return topic;
	}
}
