package com.luop.pagerank.crawl;

/**
 * Crawl data from Internet
 * @author LuoPeng
 * @time 2015.6.22
 *
 */
public class CrawlData {

	/**
	 * 初始map只有一个网址；(使用map可以自动消除重复的url)
	 * while map is not null, get the first url from map
	 * Get the infomation of url, find all the links that end with shtml、html or action (or not end with css、js、png、jpg)
	 * 	and put them into a temporary map 'tempMap'
	 * 一个网页爬取结束后，根据keywords和description的内容解析出关键字，计算outlinks(the size of tempMap)；
	 * 然后将这个网址（url、标题、关键字、outlinks）加入到url列表；将所有的关系加入到links表；clear 'tempMap'
	 * repeat step2, until the map is null or we have got enough pages.
	 */
	public void crawl () {
		
	}
	
}
