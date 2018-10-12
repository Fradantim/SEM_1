package com.tmi.servlets;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletStartUp")
public class ServletStartUp extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("eeeeeeeeeeeeeeeeeeeeeee");
		
		try {
			response.getWriter().print("{\"text\": \"A tu vieja le gusta el raviol.\"}");;
			TimeUnit.SECONDS.sleep(3);
		} catch (Exception e) {
			response.getWriter().print("{\"errorMessage\": \"ERROR: No pudo conectarse al BusinessDelegate!\"}");;
			response.setStatus(400);
			return;
		}
	
	}
}
