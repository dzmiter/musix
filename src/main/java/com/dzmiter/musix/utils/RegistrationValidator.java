package com.dzmiter.musix.utils;

import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.dzmiter.musix.dao.CrudDAO;
import com.dzmiter.musix.entity.User;

@Service
public class RegistrationValidator implements Validator {
	
	@Autowired
	private CrudDAO dao;
	
	//min: 1 digit, 1 upper case letter, 1 lower case letter, no spaces, 5 characters
	private String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{5,}$";
	
	//rus & eng & digits, no spaces, at least 1 ch
	private String namePattern = "^[a-zA-Z0-9а-яА-Я](?=\\S+$).{1,}$";
	
	private String repeatedPassword = "";
	
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
	
		User user = (User) target;
		if(!isValidEmailAddress(user.getEmail())) {
			errors.rejectValue("email", "email.incorrect", "The field email is not valid!");
		} else if(!isEmailUnique(user.getEmail())){
			errors.rejectValue("email", "email.duplicate", "There is some user with the same email!");
		}
		if(!user.getPassword().matches(passwordPattern)) {
			errors.rejectValue("password", "password.incorrect", "The field password is incorrect!");
		}
		if(!user.getFirstName().matches(namePattern)) {
			errors.rejectValue("firstName", "firstName.incorrect", "The field first name is incorrect!");
		}
		if(!user.getLastName().matches(namePattern)) {
			errors.rejectValue("lastName", "lastName.incorrect", "The field last name is incorrect!");
		}
		if(!user.getPassword().equals(repeatedPassword)) {		
			errors.rejectValue("role", "role.incorrect", "2 passwords must be identical!");
		}
		
	}
	
	public void setRepeatedPassword(String repeatedPassword) {
		if(repeatedPassword != null) {
			this.repeatedPassword = repeatedPassword;
		}
	}

	public boolean isValidEmailAddress(String email) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
		    emailAddr.validate();
		} catch (AddressException ex) {
			result = false;
		} 
		return result;
	}
	
	public boolean isEmailUnique(String email) {
		List<User> users = null;
		users = dao.list(User.class);
		for(User u : users) {
			if(u.getEmail().equals(email)) {
				return false;
			}
		}
		return true;
	}

}
