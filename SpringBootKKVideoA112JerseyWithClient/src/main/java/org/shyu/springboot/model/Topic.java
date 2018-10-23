package org.shyu.springboot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//@NamedQueries({@NamedQuery(name="Topic.findAllTopicRecords" , query="SELECT t FROM Topic t WHERE t.title like :title1")})
@Entity
@Table(name="topics")
public class Topic implements Serializable {
	
	private static final long serialVersionUID = 3466527321140663648L;
	
	@Id
	@Column(name="topic_id")
	private int topicId;
	private String title;
	private String category;
	
	public Topic() {
	}
	
	public Topic(String title, String category) {
		this.title = title;
		this.category = category;
	}
	
	public int getTopicId() {
		return topicId;
	}
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
}
