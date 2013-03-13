package com.dzmiter.musix.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dzmiter.musix.dao.CrudDAO;
import com.dzmiter.musix.entity.Track;

@Controller
public class SearchController {
	
	@Autowired
	private CrudDAO dao;
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(Model model, HttpServletRequest request) throws InterruptedException {
		dao.doIndex();
		String search_query = request.getParameter("search_query");
		List<Track> searchResult = null;
		if(search_query.length() > 0) {
			searchResult = dao.search(Track.class, 
					search_query, "name");
			searchResult.addAll(dao.search(Track.class, 
					search_query, "description"));
		}		
		model.addAttribute("searchResult", searchResult);
		model.addAttribute("search_query", search_query);
		return "search";
	}

}
