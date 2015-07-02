package com.luop.pagerank.crawl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.luop.pagerank.bean.UrlBean;
import com.luop.pagerank.util.AllInterface;

/**
 * Crawl data from Internet
 * @author LuoPeng
 * @time 2015.6.22
 *
 */
public class CrawlData {

	private Set<String> urlsSet = null;   // the  urls waiting to be crawled
	private int urlId = 1;                // the id of an url
	
	private Document document = null;
	private Elements elements = null;
	private Element element = null;
	private Connection conn = null;
	
	/**
	 * 初始set只有一个网址；(使用map可以自动消除重复的url)
	 * while map is not null, get the first url from map
	 * Get the infomation of url, find all the links that end with shtml、html or action (or not end with css、js、png、jpg)
	 * 	and put them into a temporary map 'tempMap'
	 * 一个网页爬取结束后，根据keywords和description的内容解析出关键字，计算outlinks(the size of tempMap)；
	 * 然后将这个网址（url、标题、关键字、outlinks）加入到url列表；将所有的关系加入到links表；clear 'tempMap'
	 * repeat step2, until the map is null or we have got enough pages.
	 */
	public void crawl () {
		
		
		Object[] urlsArray = null;
		int arraySize = 0;
		
		urlsSet = new HashSet<String>();
		urlsSet.add("http://news.baidu.com/");
		while ( !urlsSet.isEmpty() && urlId < Integer.MAX_VALUE) {
			urlsArray = urlsSet.toArray();
			urlsSet.clear();
			
			arraySize = urlsArray.length;
			for ( int i = 0; i < arraySize; i++) {
				pageContent((String)urlsArray[i]);
			}
		}
		
	}
	
	/**
	 * Get the content of the url
	 * @param url
	 */
	private void pageContent( String url) {
		
		List<String> outlinks = null; // out link urls
		UrlBean bean = null;          // basic info of the url
		
		bean = new UrlBean();
		try {
			
			conn = Jsoup.connect(url).timeout(5000);
			if ( conn == null) {
				return ;
			}
			
			document = conn.get(); // 得到网页源代码，超时时间是5秒
			
			/*
			 * 文章基本信息
			 */
			element = document.getElementsByTag("title").get(0);
			String title = element.text();
			bean.setTitle(title);
			bean.setKeywords(title);
			
			elements = document.getElementsByTag("a");
			if ( elements != null) {
				outlinks = new ArrayList<String>();
				for ( int i = 0; i < elements.size(); i++) {
					outlinks.add(elements.get(i).attr("href"));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} 
		
		bean.setUrl(url);
		bean.setId(urlId++);
		bean.setRank(1);
		if ( outlinks != null) {
			int count = outlinks.size();
			bean.setOutlinks(count);
			for ( int i = 0; i < count; i++) {
				urlsSet.add(outlinks.get(i));
			}
			if ( new AllInterface().addOneUrlInfo(bean, outlinks)) {
				System.out.println("Add to database successfully, url is '" + url + "'.");
			} else {
				System.out.println("Fail to add to database, maybe url exists. Url is '" + url + "'.");
			}
			System.out.println("[" + count + " urls remain...]");
			System.out.println();
		} else {
			System.out.println("Error occurs in crawl, url is '" + url + "'.");
			System.out.println("[[FINISHED]]");
			System.out.println();
		}
		
	}
	
}
