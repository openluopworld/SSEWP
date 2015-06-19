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
	 * ��ʼ�����нڵ�rankΪ1
	 * ���������жϷ���
	 * �õ�����url��in links������ÿһ��in link��rank��out links number���㵱ǰurl��rank��
	 * �������ʣ������url��rank�����ĳһ��url��rank�仯����0.0001�����ʾû������
	 * ѭ������ֱ������
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
