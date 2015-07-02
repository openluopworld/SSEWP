package com.luop.pagerank.crawl;

import com.luop.pagerank.bean.UrlBean;
import com.luop.pagerank.compute.ComputeRank;
import com.luop.pagerank.util.AllInterface;

public class Test {

	public static void main ( String [] args ) {
		
//		new CrawlData().crawl();		
		
		new ComputeRank().compute();
//		System.out.println(Integer.MAX_VALUE);
		
//		String URL = "^(http://|https://)?((?:[A-Za-z0-9]+-[A-Za-z0-9]+|[A-Za-z0-9]+)\\.)+([A-Za-z]+)[/\\?\\:]?.*$";
//		
//		System.out.println("http://news.163.com/api/15/0701/12/ATEJACIR00011229.jpg"
//				.matches(URL));
		
//		UrlBean bean = new UrlBean();
//		bean.setUrl("http://baidu.com");
//		System.out.println(new AllInterface().addOneUrlInfo(bean, null));
		
	}
	
}