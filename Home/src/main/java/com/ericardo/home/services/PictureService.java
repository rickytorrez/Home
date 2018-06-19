package com.ericardo.home.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericardo.home.models.Picture;
import com.ericardo.home.repositories.PictureRepository;

@Service
public class PictureService {

	@Autowired
	private PictureRepository _pR;
	
	public void create(Picture picture) {
		_pR.save(picture);
	}
	
	public Picture find(Long id) {
		return (Picture) _pR.findOne(id);
	}
	
	public void update(Picture picture) {
		_pR.save(picture);
	}
	
	public void destroy(Long id) {
		_pR.delete(id);
	}
	
	public ArrayList<Picture> all(){
		return (ArrayList<Picture>) _pR.findAll();
	}
}