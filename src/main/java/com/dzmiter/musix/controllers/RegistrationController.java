package com.dzmiter.musix.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dzmiter.musix.dao.CrudDAO;
import com.dzmiter.musix.entity.User;
import com.dzmiter.musix.utils.EmailSender;
import com.dzmiter.musix.utils.RegistrationValidator;

@Controller
public class RegistrationController {	
	
	@Autowired
	private RegistrationValidator validator;
	
	@Autowired
	private CrudDAO dao;
	
	@Autowired
	private EmailSender sender;
		
	@RequestMapping(value = "/registration") 
	public String registrationJsp() {
		return "registration";
	}
	
	@RequestMapping(value = "/registration_do", method = RequestMethod.POST)
	public String register(@ModelAttribute User user, BindingResult result,
			HttpServletRequest request, Model model) {
		
		String repeat = request.getParameter("repeat");
		validator.setRepeatedPassword(repeat);
		validator.validate(user, result);
		
		if (result.hasErrors()) {
			model.addAttribute("repeat", repeat);
			return "registration";
		} else {
			user.setCreationDate(new Date());
			user.setRole("USER");
			user.setIsActivated(Boolean.FALSE);
			User newUser = dao.merge(user);
			newUser = dao.find(User.class, newUser.getId());
			ShaPasswordEncoder encoder = new ShaPasswordEncoder();			
			
			String url = "http://localhost:8080/musix/activate?id=" + newUser.getId() +
					"&t=" + encoder.encodePassword(newUser.getCreationDate().toString(), null) +
					"&c=" + encoder.encodePassword(newUser.getEmail(), null);
			String message = "<b>" + newUser.getFirstName() + " " + newUser.getLastName() + "</b>,<br>" +
					"пожалуйста, подтвердите вашу учётную запись в Musix.<br>" +
					"После подтверждения регистрации вы получите полный доступ к Musix.<br>" +
					"<a href=\"" + url + "\">Подтвердить регистрацию</a>.<hr>" + 
					"Ваш пароль: <b>" + user.getPassword() + "<b><br>" +
					"C уважением, команда Musix.";	
			try {
				sender.sendEmail(newUser.getEmail(), message);
			} catch (EmailException e) {
				e.printStackTrace();
				return "registration";				
			}
			return "redirect:/";
		}
		
	} 
	
	@ModelAttribute("user")
	public User newUser() {
		return new User();
	}
}
