package com.ericardo.home.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericardo.home.models.Message;
import com.ericardo.home.repositories.MessageRepository;

@Service
public class MessageService {
	
	@Autowired
	private MessageRepository _mR;
	
	public void create(Message message) {
		_mR.save(message);
	}
	
	public Message find(Long id) {
		return (Message)_mR.findOne(id);
	}
	
	public void update(Message message) {
		_mR.save(message);
	}
	
	public void destroy(Long id) {
		_mR.delete(id);
	}
	
	public ArrayList<Message> all() {
		return (ArrayList<Message>) _mR.findAll();
	}
}