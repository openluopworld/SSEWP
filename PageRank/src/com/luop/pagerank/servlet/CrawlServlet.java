package com.luop.pagerank.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Crawl data from intenet-
 * @author LuoPeng
 *
 */
public class CrawlServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1677455353710039608L;

	@Override
	public void doGet (HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}
	
	@Override
	public void doPost (HttpServletRequest request, HttpServletResponse response) {
		
	}

}
