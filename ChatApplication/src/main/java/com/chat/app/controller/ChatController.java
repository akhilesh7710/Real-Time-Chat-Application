package com.chat.app.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chat.app.entity.ChatMessage;
import com.chat.app.repository.ChatMessageRepository;

import org.springframework.ui.Model;


@Controller
public class ChatController {
	
	@Autowired
    private ChatMessageRepository chatMessageRepository;

	
//	@MessageMapping("/sendMessage")	@SendTo("/topic/messages")
//	public ChatMessage sendMessage(ChatMessage message) {
//		System.out.println("Message received: " + message.getContent()); 
//	    return new ChatMessage(message.getId(), message.getSender(), message.getContent());
//	    }
//	
	
	@MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(ChatMessage message, Principal principal) { //new()
       // String senderUsername = principal.getName(); //new
       // message.setSender(senderUsername); //new

        message.setTimestamp(java.time.LocalDateTime.now());
        chatMessageRepository.save(message);  // Save to DB
        System.out.println("Message received: " + message.getContent()); 
        return message;
    }
	
	@GetMapping("chat")
	public String chat(Principal principal ,Model model) {
		 // Get logged-in username
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username", principal != null ? principal.getName() : "Guest");

		return "chat";
	}
	
	 @ResponseBody
	    @GetMapping("/chat/history")
	    public List<ChatMessage> getChatHistory(Principal principal ) { //new ()
	       // String username = principal.getName();//new

	        return chatMessageRepository.findAll();
	    }
	}


