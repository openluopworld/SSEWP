package com.luop.pagerank.crawl;

/**
 * Crawl data from Internet
 * @author LuoPeng
 * @time 2015.6.22
 *
 */
public class CrawlData {

	/**
	 * ��ʼmapֻ��һ����ַ��(ʹ��map�����Զ������ظ���url)
	 * while map is not null, get the first url from map
	 * Get the infomation of url, find all the links that end with shtml��html or action (or not end with css��js��png��jpg)
	 * 	and put them into a temporary map 'tempMap'
	 * һ����ҳ��ȡ�����󣬸���keywords��description�����ݽ������ؼ��֣�����outlinks(the size of tempMap)��
	 * Ȼ�������ַ��url�����⡢�ؼ��֡�outlinks�����뵽url�б������еĹ�ϵ���뵽links��clear 'tempMap'
	 * repeat step2, until the map is null or we have got enough pages.
	 */
	public void crawl () {
		
	}
	
}
