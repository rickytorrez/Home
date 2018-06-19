package com.ericardo.home.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericardo.home.models.Listing;
import com.ericardo.home.repositories.ListingRepository;

@Service
public class ListingService {

	@Autowired
	private ListingRepository _lR;
	
	public void create(Listing listing) {
		_lR.save(listing);
	}
	
	public Listing find(Long id) {
		return (Listing)_lR.findOne(id);
	}
	
	public void update(Listing listing) {
		_lR.save(listing);
	}
	
	public void destroy(Long id) {
		_lR.delete(id);
	}
	
	public ArrayList<Listing> all(){
		return (ArrayList<Listing>) _lR.findAll();
	}
	
	public ArrayList<Listing> findByAddress(String address){
		return (ArrayList<Listing>) _lR.findByAddress(address);
	}
}