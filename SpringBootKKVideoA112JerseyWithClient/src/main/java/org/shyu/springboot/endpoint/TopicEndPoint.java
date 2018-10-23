package org.shyu.springboot.endpoint;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.shyu.springboot.model.Topic;
import org.shyu.springboot.service.TopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

@Component
@Path("/topics")
public class TopicEndPoint {
	private static final Logger logger = LoggerFactory.getLogger(TopicEndPoint.class); 
	
	@Autowired
	private TopicService topicService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getAllTopics() {		
		List<Topic> topics = new ArrayList<>(topicService.getAllTopics());
		if (topics.size() > 0) {
			logger.info("Find " + topics.size() + " records");
		} else {
			logger.info("No record found!");
		}
		return Response.ok(topics).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create")
	public Response createTopic(Topic topic) {
		boolean isCreated = topicService.createTopic(topic);
		if (!isCreated) {
			logger.info("Record already exist");
		} else {
			logger.info("Record created!");
		}
		return Response.created(URI.create("/topics/create")).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/topic/{topicId}")
	public Response getTopicById(@PathParam("topicId") int id) {
		Topic topic = topicService.getTopicById(id);
		if (topic != null) {
			logger.info("Topic retrieved!");
		} else {
			logger.info("Topic not found!");
		}
		return Response.ok(topic).build();
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/update")
	public Response updateTopic(Topic topic) {
		Topic topicAfterUpdated = topicService.updateTopic(topic);
		if (topicAfterUpdated != null) {
			logger.info("Record updated");
		} else {
			logger.info("Record not found!");
		}
		return Response.ok(topicAfterUpdated).build();
	}
	
	@DELETE
	@Path("/delete/{topicId}")
	public Response deleteTopic(@PathParam("topicId") int id) {
		int numberOfDeleted = topicService.deleteTopic(id);
		if (numberOfDeleted > 0) {
			logger.info("Record deleted!");
		} else {
			logger.info("Not record found to delte!");
		}
		return Response.noContent().build();
	}
}
