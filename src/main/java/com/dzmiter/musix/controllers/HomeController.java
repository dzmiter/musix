package com.dzmiter.musix.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dzmiter.musix.dao.CrudDAO;
import com.dzmiter.musix.entity.*;

@Controller
public class HomeController {
	
	@Autowired
	private CrudDAO dao;
	
	private static final int tracksOnPage = 50;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		List<Track> mostListened = dao.topList(Track.class, tracksOnPage, "playsnumber");
		List<Track> mostPopular;// = dao.topList(Track.class, tracksOnPage, "rating");
		model.addAttribute("mostListened", mostListened);
//		model.addAttribute("mostPopular", mostPopular);
		
		try {
			dao.doIndex();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		mostPopular = dao.search(Track.class, "yu olol zsdf", "name");
		model.addAttribute("mostPopular", mostPopular);
		return "home";
	}
	
	@ModelAttribute("user")
	public User newUser() {
		return new User();
	}
	
}
