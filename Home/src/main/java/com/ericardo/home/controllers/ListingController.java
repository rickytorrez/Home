package com.ericardo.home.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ericardo.home.models.Listing;
import com.ericardo.home.models.Picture;
import com.ericardo.home.models.User;
import com.ericardo.home.services.ListingService;
import com.ericardo.home.services.PictureService;
import com.ericardo.home.services.UserService;

@Controller
@RequestMapping("/listings")
public class ListingController {
	
	@Autowired
	private ListingService _lS;
	
	@Autowired
	private UserService _uS;
	
	@Autowired
	private PictureService _pS;
	
	/********************************** CLIENT SIDE ******************************************/
	/********************************* VIEW LISTINGS  ****************************************/
	
	@RequestMapping("")
	public String listing(HttpSession _session, Model _model) {
		User user;
		
		if(_session.getAttribute("id") != null) {
			user = _uS.find((Long) _session.getAttribute("id"));
			_model.addAttribute("user", user);
		}	
		
		_model.addAttribute("listings", _lS.all());
		return "listings";
	}
	
	/******************************** VIEW SINGLE LISTING  ***********************************/
	
	@RequestMapping("/{id}")
	public String find(@PathVariable("id") Long id, Model _model, HttpSession _session) {
		if(_session.getAttribute("id") != null) {
			User user = _uS.find( (Long) _session.getAttribute("id"));
			_model.addAttribute("user", user);
		}
		_model.addAttribute("listing", _lS.find(id));
		return "singleListing";
	}
	
	/********************************* VIEW FAVORITE PAGE  ***********************************/
	
	@RequestMapping("/favorites")
	public String favorite(Model _model, HttpSession _session) {
		if(_session.getAttribute("id") == null) {
			return "redirect:/listings";
		}
		User user = _uS.find( (Long) _session.getAttribute("id"));
		_model.addAttribute("user", user);
		
		return "favorites";
	}
	
	/************************************* ADD FAVORITE  *************************************/

	@PostMapping("/{id}/add")
	public String addToFave(@PathVariable("id") Long id, HttpSession _session) {
		User user = _uS.find( (Long) _session.getAttribute("id"));
		Listing listing = _lS.find(id);
		
		List<Listing> listings = user.getFaveListings();
		listings.add(listing);
		user.setFaveListings(listings);
		_uS.update(user);
		
		return "redirect:/listings";
	}
	
	/************************************ REALTOR SIDE **************************************/
	/********************************** AGENT DASHBOARD *************************************/

	@RequestMapping("/realtor")
	public String realtor(HttpSession _session, Model _model, @ModelAttribute("listing") Listing listing) {
		if(_session.getAttribute("id") == null) {
			return "redirect:/users/new";
		}
		
		User user = _uS.find( (Long) _session.getAttribute("id"));
		if(!user.isRealtor() && !user.isAdmin()) {
			return "redirect:/listings";
		}
		_model.addAttribute("user", user);
		_model.addAttribute("listing", new Listing());
		return "realtor";		
	}
	
	/********************************** CREATE LISTING ***************************************/
	
	@PostMapping("/createListing")
	public String create(@Valid @ModelAttribute("listing") Listing listing, BindingResult _result, HttpSession _session, Model _model) {
		if(_session.getAttribute("id") == null ) {
			return "redirect:/users/new";
		}
		
		User user = _uS.find( (Long) _session.getAttribute("id") );
		
			listing.setUser(user);
			_lS.create(listing);
			return "redirect:/listings/realtor";		
	}
	
	/************************* DISPLAY SINGLE LISTING FOR REALTOR EDIT ***********************/
	
	@RequestMapping("/{id}/edit")
	public String displaySingleListing(@PathVariable("id") Long id, Model _model, HttpSession _session, @ModelAttribute("listing") Listing listing) {
		if(_session.getAttribute("id") != null) {
			User user = _uS.find( (Long) _session.getAttribute("id"));
			_model.addAttribute("user", user);
		}
		_model.addAttribute("listing", _lS.find(id));
		return "editListing";
	}
	
	/************************************** EDIT LISTING  *************************************/
	
	@PostMapping("/{id}/editListing")
	public String updateListing(@PathVariable("id") Long id, Model _model, HttpSession _session,
			@RequestParam("address") String address,
			@RequestParam("description") String description,
			@RequestParam("type") String type,
			@RequestParam("yearbuilt") Integer yearbuilt,
			@RequestParam("numberofrooms") Integer numberofrooms,
			@RequestParam("numberofbathrooms") Integer numberofbathrooms,
			@RequestParam("size") Integer size,
			@RequestParam("cost") double cost) {
		
		if(_session.getAttribute("id") == null) {
			return "redirect:/users/new";
		}
		
		System.out.print("Hitting it?");
		
		User user = _uS.find( (Long) _session.getAttribute("id"));
		
		Listing listing = _lS.find(id);
		listing.setAddress(address);
		listing.setDescription(description);
		listing.setType(type);
		listing.setYearbuilt(yearbuilt);
		listing.setNumberofrooms(numberofrooms);
		listing.setNumberofbathrooms(numberofbathrooms);
		listing.setSize(size);
		listing.setCost(cost);
		listing.setUser(user);
		_lS.update(listing);
		
		return "redirect:/listings/realtor";
	}
	
	/********************************* ADD IMAGE TO LISTING **********************************/
	
	@PostMapping("/{id}/addImage")
	public String addNewImage(@PathVariable("id") Long id, Model _model, HttpSession _session, @RequestParam("file") MultipartFile file) {
		if(_session.getAttribute("id") == null ) {
			return "redirect:/users/new";
		}
		
		User user = _uS.find( (Long) _session.getAttribute("id") );
		Listing listing = _lS.find(id);
		Picture picture = new Picture();
		
		if(!user.isRealtor() && !user.isAdmin()) {
			return "redirect:/listings";
		}
			// File upload
			if(!file.isEmpty()) {
				try {
					byte[] bytes = file.getBytes();
					// Creates the directory to store the file
					File dir = new File("src/main/webapp/images/");
					if(!dir.exists())
						dir.mkdir();
					
					// Creates the file on the server
					File serverFile = new File(dir.getAbsolutePath()
							+ File.separator + file.getOriginalFilename());
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
					
					// Adding it to the database
					picture.setImage(file.getOriginalFilename());
					picture.setUser(user);
					picture.setListing(listing);
					_pS.create(picture);
					return "redirect:/listings/realtor";
				} catch (Exception e){
					return "redirect:/listings";
				}
			}
			return "redirect:/listings";
		}
	/************************************** JOHN ROUTE  *************************************/
	
	@RequestMapping("/john")
	public String johnHelped() {
		return "jonathan";
	}
}