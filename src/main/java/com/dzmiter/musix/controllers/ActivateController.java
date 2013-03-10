package com.dzmiter.musix.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dzmiter.musix.dao.CrudDAO;
import com.dzmiter.musix.entity.User;

@Controller
public class ActivateController {
	
	@Autowired
	private CrudDAO dao;
	
	@RequestMapping(value = "/activate")
	public String activate(@RequestParam("id") Long id, @RequestParam("t") String time,
			@RequestParam("c") String credentials, Model model, HttpServletRequest request) {
		if(id == null || time == null || credentials == null) {
			model.addAttribute("message", "Sorry, it's not valid link!");
		} else {			
			User user = dao.find(User.class, id);
			ShaPasswordEncoder encoder = new ShaPasswordEncoder();
			if(user == null) {
				model.addAttribute("message", "Sorry, it's not valid link!");
			} else if(user.getIsActivated()) {
				model.addAttribute("message", "You have already acivated your account!");
			} else if(time.equals(encoder.encodePassword(user.getCreationDate().toString(), null)) && 
					credentials.equals(encoder.encodePassword(user.getEmail(), null))) {
				model.addAttribute("message", "Congratulations! You've just activated your account!");
				user.setIsActivated(Boolean.TRUE);
				dao.merge(user);
				HttpSession sess = request.getSession();
				sess.setAttribute("myId", user.getId());
				sess.setAttribute("email", user.getEmail());			
			} else {
				model.addAttribute("message", "Sorry, it's not valid link!");
			}
		}
		return "activate";		
	}
	
}
