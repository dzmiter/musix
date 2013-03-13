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
	
	private static int tracksOnPage = 100;
		
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		List<Track> mostPopular = dao.topList(Track.class, tracksOnPage, "rating");
		List<Track> mostListened = dao.topList(Track.class, tracksOnPage, "playsnumber");
		model.addAttribute("mostPopular", mostPopular);
		model.addAttribute("mostListened", mostListened);
		return "home";
	}
	
	@ModelAttribute("user")
	public User newUser() {
		return new User();
	}
	
}
