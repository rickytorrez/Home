package com.ericardo.home.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ericardo.home.models.User;
import com.ericardo.home.services.ListingService;
import com.ericardo.home.services.UserService;

@Controller
public class RouteController {
	
	@Autowired
	private UserService _uS;
	
	@Autowired
	private ListingService _lS;
	
	/*************************************** HOME DASHBOARD **********************************/

	@RequestMapping("/*")
	public String index(HttpServletRequest _request, HttpSession _session, Model _model) {
		if(!_uS.isValid(_session)) {
			_model.addAttribute("allListings", _lS.all());
			return "home";
		} else {
			User user = _uS.find((Long) _session.getAttribute("id"));
			
			if(user.isRealtor()) {
				return "redirect:/listings/realtor";
			} else {
				return "redirect:/listings";
			}
		}
	}
}