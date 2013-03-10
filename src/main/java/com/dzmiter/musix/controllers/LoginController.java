package com.dzmiter.musix.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import com.dzmiter.musix.dao.CrudDAO;
import com.dzmiter.musix.entity.User;
import com.dzmiter.musix.utils.LoginValidator;

@Controller
public class LoginController {
	
	@Autowired
	private CrudDAO dao;	
	
	@Autowired
	private LoginValidator validator;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute User user, BindingResult result,
			HttpServletRequest request, Model model, HttpServletResponse response) {
		
		String rememberme = request.getParameter("remember-me");
		
		validator.validate(user, result);
		
		if (result.hasErrors()) {
			model.addAttribute("rememberme", (rememberme == null) ? null : "remember-me");
			return "login";
		} else {
			if(rememberme != null) {
				User checkedUser = validator.getCheckedUser();
				ShaPasswordEncoder encoder = new ShaPasswordEncoder();
				String authToken = encoder.encodePassword(Math.random() + "", null);
				checkedUser.setAuthToken(authToken);
				dao.merge(checkedUser);
				
				String id = checkedUser.getId().toString();
				
				Cookie cookie = new Cookie("musix-login", id + "-" + authToken);
				cookie.setMaxAge(-1);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
			HttpSession sess = request.getSession();
			sess.setAttribute("myId", validator.getCheckedUser().getId());
			sess.setAttribute("email", validator.getCheckedUser().getEmail());			
			return "redirect:/";
		}
	} 
	
	@RequestMapping(value = "/logout")
	public String logOut(HttpServletRequest request, HttpServletResponse response) {		
		Cookie[] cookies = request.getCookies();
		for(Cookie c : cookies) {
			if(c.getName().equals("musix-login")) {
				c.setValue(null);
				c.setMaxAge(1);
				c.setPath("/");
				response.addCookie(c);
				break;
			}
		}
		HttpSession sess = request.getSession();
		Object myId = sess.getAttribute("myId");
		if(myId != null) {			
			User user = dao.find(User.class, Long.parseLong(myId.toString()));
			if(user != null) {
				user.setAuthToken(null);
				dao.merge(user);
			}
		}
		sess.invalidate();
		return "redirect:/";		
	} 
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginJsp() {
		return "login";		
	}
	
	@ModelAttribute("user")
	public User newUser() {
		return new User();
	}

}