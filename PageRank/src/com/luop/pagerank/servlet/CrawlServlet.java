package com.luop.pagerank.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.luop.pagerank.crawl.CrawlData;

/**
 * Crawl data from Internet
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
		
		new CrawlData().crawl();
		
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.print("Crawl successfully");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
