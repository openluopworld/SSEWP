package com.luop.pagerank.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.luop.pagerank.compute.ComputeRank;

/**
 * compute the rank of each url
 * @author LuoPeng
 * @time 2015.6.18
 *
 */
public class ComputeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4876393170412177796L;
	
	@Override
	public void doGet (HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}
	
	/**
	 * 初始化所有节点rank为1
	 * 给出收殓判断方法
	 * 得到所有url的in links，根据每一个in link的rank和out links number计算当前url的rank，
	 * 逐个计算剩余所有url的rank，如果某一个url的rank变化大于0.0001，则表示没有收殓
	 * 循环计算直至收殓
	 */
	@Override
	public void doPost (HttpServletRequest request, HttpServletResponse response) {
		
		new ComputeRank().compute();
		
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.print("compute successfully");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
