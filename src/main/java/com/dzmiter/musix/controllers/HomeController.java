package com.dzmiter.musix.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dzmiter.musix.dao.CrudDAO;
import com.dzmiter.musix.entity.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class HomeController {
	
	@Autowired
	private CrudDAO dao;
	
	private static int tracksOnPage = 100;
		
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request) {
		List<Track> mostPopular = dao.topList(Track.class, tracksOnPage, "rating");
		List<Track> mostListened = dao.topList(Track.class, tracksOnPage, "playsnumber");
		model.addAttribute("mostPopular", mostPopular);
		model.addAttribute("mostListened", mostListened);
		String path = request.getSession().getServletContext().getRealPath("/resources/audiofiles") + "/";
		Gson gson = new Gson();
		List<TrackInPlaylist> trtr = new ArrayList<TrackInPlaylist>();
		for(Track t : mostListened) {
			TrackInPlaylist tr = new TrackInPlaylist();
			tr.setTitle(t.getPath());
			tr.setMp3("./resources/audiofiles/" + t.getPath() + "." + t.getFormat());
			trtr.add(tr);
		}
		String json = gson.toJson(trtr);
		model.addAttribute("json", json);
		return "home";
	}
	
	@ModelAttribute("user")
	public User newUser() {
		return new User();
	}
	
}

class TrackInPlaylist {
	private String title;
	private String mp3;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMp3() {
		return mp3;
	}
	public void setMp3(String mp3) {
		this.mp3 = mp3;
	}
}