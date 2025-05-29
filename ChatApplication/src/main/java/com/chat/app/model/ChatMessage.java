             package com.chat.app.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@NoArgsConstructor

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatMessage {
	private Long id;
	private String sender;
	private String content;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public ChatMessage(Long id, String sender, String content) {
		super();
		this.id = id;
		this.sender = sender;
		this.content = content;
	}
	public ChatMessage() {
		super();
	}
	

}
