package com.ericardo.home.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ericardo.home.models.Message;
import com.ericardo.home.services.MessageService;

@Controller
@RequestMapping("/message")
public class MessageController {
	
	@Autowired
	private MessageService _mS;
	
	@PostMapping("/new")
	public String newMessage(@ModelAttribute("message") Message message) {
		System.out.print("hitting message serv");
		
		
		_mS.create(message);	
		return "thankyou";
	}
}