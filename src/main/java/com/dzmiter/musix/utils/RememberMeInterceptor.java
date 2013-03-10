package com.dzmiter.musix.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dzmiter.musix.dao.CrudDAO;
import com.dzmiter.musix.entity.User;

public class RememberMeInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private CrudDAO dao;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession sess = request.getSession();
		Cookie[] co = request.getCookies();
		for(Cookie c : co) {
			System.out.println(c.getName() + " <---> " + c.getValue());
		}
		if(sess.getAttribute("email") == null || sess.getAttribute("myId") == null) {
			Cookie[] cookies = request.getCookies();
			for(Cookie c : cookies) {
				if(c.getName().equals("musix-login")) {
					String[] val = c.getValue().split("-");
					if(c.getValue() != null && val.length == 2 && val[0].matches("[0-9]")) {
						Long id = Long.parseLong(val[0]);
						User user = null;
						if(id != null) {
							user = dao.find(User.class, id);
						}
						String email = null;
						if(user != null) {
							email = user.getEmail();
						}
						if(val[1].equals(user.getAuthToken()) && email != null) {
							sess.setAttribute("myId", id);
							sess.setAttribute("email", email);
						}						
					}	
					break;
				} 
			}
		}
		return super.preHandle(request, response, handler);
	}
	
}