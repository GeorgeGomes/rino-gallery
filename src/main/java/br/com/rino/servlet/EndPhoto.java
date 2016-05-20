package br.com.rino.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.rino.dao.PhotoDAO;
import br.com.rino.entity.Photo;
import br.com.rino.util.FileUtil;

public class EndPhoto extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.sendRedirect("http://localhost:8090/rino/app/photo/endPhoto.xhtml");
		
		for (Cookie cookie : req.getCookies()) {
		    cookie.setValue("");
		    cookie.setMaxAge(0);
		    cookie.setPath("/");

		    res.addCookie(cookie);
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.sendRedirect("http://localhost:8090/rino/app/photo/endPhoto.xhtml");
		
		for (Cookie cookie : req.getCookies()) {
		    cookie.setValue("");
		    cookie.setMaxAge(0);
		    cookie.setPath("/");

		    res.addCookie(cookie);
		}	
	}
}