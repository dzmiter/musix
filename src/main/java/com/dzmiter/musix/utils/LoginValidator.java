package com.dzmiter.musix.utils;

import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.dzmiter.musix.dao.CrudDAO;
import com.dzmiter.musix.entity.User;

@Service
public class LoginValidator implements Validator {
	
	@Autowired
	private CrudDAO dao;
	
	private User checkedUser;
	
	//min: 1 digit, 1 upper case letter, 1 lower case letter, no spaces, 5 characters
	private String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{5,}$";
		
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
	
		User user = (User) target;
		boolean isEmailValid = isValidEmailAddress(user.getEmail());
		boolean isPasswordValid = user.getPassword().matches(passwordPattern);
		
		if(!isEmailValid || !isPasswordValid) {
			if(!isEmailValid) {
				errors.rejectValue("email", "email.incorrect", "The field email is not valid!");
			}
			if(!isPasswordValid) {
				errors.rejectValue("password", "password.incorrect", "The field password is incorrect!");
			}
		} else if((checkedUser = checkUser(user)) == null) {
			errors.rejectValue("email", "email.mismatch", "There is no users with such email & password!");
		} else if(!checkedUser.getIsActivated()){
			errors.rejectValue("email", "email.inactivated", "User with such email & password is not activated!");
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
	
	public User checkUser(User user) {
		List<User> users = dao.list(User.class);
		String email = user.getEmail();
		String password = shaEncode(user.getPassword());
		for(User u : users) {
			if(u.getEmail().equals(email) && u.getPassword().equals(password)) {
				return u;
			}
		}
		return null;
	}
	
	public User getCheckedUser() {
		return checkedUser;
	}
	
	public static String shaEncode(String password) {
		ShaPasswordEncoder encoder = new ShaPasswordEncoder();
		String encodedPassword = encoder.encodePassword(password, null);
		return encodedPassword;
	}

}
