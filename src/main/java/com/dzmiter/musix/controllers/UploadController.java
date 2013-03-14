package com.dzmiter.musix.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dzmiter.musix.dao.CrudDAO;
import com.dzmiter.musix.entity.Track;
import com.dzmiter.musix.entity.User;
import com.sun.mail.util.BASE64DecoderStream;

@Controller
public class UploadController {
	
	@Autowired
	private CrudDAO dao;
	
	private String fileName = "";
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String upload() {		
		return "upload";
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public void uploadDo(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, NoSuchAlgorithmException  {

		String up = request.getParameter("up");
		if(!up.equals("true")) {
			return;
		}
		byte[] file = getFileInBytes(request);		
		String path = calculateFilePath(file, request);
		boolean isWritten = writeFileFromBytes(file, path);	
		if(!isWritten) {
			return;
		}
		dao.merge(setStandartFields(request, "cool track"));
	}
	
	private Track setStandartFields(HttpServletRequest request, String description) {
		Track loadedTrack = new Track();
		loadedTrack.setCreationDate(new Date());
		loadedTrack.setSize(getFileSize(request));
		loadedTrack.setFormat(getFileExtension(request));
		loadedTrack.setPlaysnumber(0);
		loadedTrack.setRating(0);
		loadedTrack.setPath(fileName);
		loadedTrack.setDescription(description);
		Long myId = (Long) request.getSession().getAttribute("myId");
		loadedTrack.setUser(dao.find(User.class, myId));
		return loadedTrack;
	}
	
	private byte[] getFileInBytes(HttpServletRequest request) throws IOException {
		String base = request.getParameter("base64");
		ServletInputStream reader = request.getInputStream();
		Integer size = getFileSize(request);
		byte[] b = new byte[size];
		if(base.equals("true")) {
			BASE64DecoderStream stream = new BASE64DecoderStream(reader);
			stream.read(b);
			stream.close();
		} else {
			reader.read(b);
		}
		reader.close();
		return b;		
	}
	
	private Integer getFileSize(HttpServletRequest request) {
		Integer size = Integer.parseInt(request.getHeader("UP-SIZE"));
		return size;
	}
	
	private String calculateFilePath(byte[] file, HttpServletRequest request) throws NoSuchAlgorithmException {
		String path = request.getSession().getServletContext().getRealPath("/resources/audiofiles") + "/";		
		getFileName(file);
		String extension = "." + getFileExtension(request);
		System.out.println(path + fileName + extension);
		return path + fileName + extension;
	}
	
	private void getFileName(byte[] file) throws NoSuchAlgorithmException {
		fileName = md5hash(file);		
	}
	
	private String getFileExtension(HttpServletRequest request) {
		String extension = request.getHeader("UP-TYPE");
		int i = extension.lastIndexOf('/');
		if (i > 0) {
		    extension = extension.substring(i+1);
		}
		return extension;
	}
	
	private boolean writeFileFromBytes(byte[] bytes, String path) throws IOException {		 
	    File file = new File(path);	 
	    if(file.exists()) {
	    	return false;
	    }
	    FileOutputStream os = new FileOutputStream(file);	 
	    os.write(bytes);	 
	    os.close();
	    return true;
	}
	
	private String md5hash(byte[] ar) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(ar);
		BigInteger bigInt = new BigInteger(1, md.digest());
		return bigInt.toString(16);
	}
		
}
