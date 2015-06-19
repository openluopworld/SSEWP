package com.luop.pagerank.compute;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.luop.pagerank.bean.UrlBean;
import com.luop.pagerank.util.AllInterface;

/**
 * Compute the rank of each url
 * @author LuoPeng
 * @time 2015.6.18
 *
 */
public class ComputeRank {

	private Map<String, Object> urlsInfoMap = null; // the url info
	private Map<String, List<String>> inlinksMap = null; // the inlink urls of each url
	private boolean finished = false; // judge whether the result is convergent
	private static final double THRESHOLD = 0.001; 
	
	public void compute () {
		
		AllInterface obj = new AllInterface();
		urlsInfoMap = obj.getUrlInfo();
		inlinksMap = obj.getInLinks();
		
		init();
		
		Iterator<String> urlsIte = urlsInfoMap.keySet().iterator();
		List<String> inlinksList = null;
		String urlStr = null;
		UrlBean urlBean = null;
		double rank = 0;
		String inlinkUrl = null;
		
		int roundCount = 1;
		
		while ( !finished ) {
			
			finished = true; // suppose it will finish after this round
			
			// compute again: rank of each url will be computed again
			while ( urlsIte.hasNext() ) {
				urlStr = urlsIte.next();
				inlinksList = inlinksMap.get(urlStr); // get the inlink urls of url 'urlStr'
				
				// compute the new rank
				rank = 0;
				for ( int i = 0; i < inlinksList.size(); i++) {
					inlinkUrl = inlinksList.get(i);
					urlBean = (UrlBean)urlsInfoMap.get(inlinkUrl);
					rank += urlBean.getRank()/urlBean.getOutlinks();
				}
				rank = 0.15 + 0.85 * rank;
				
				urlBean = (UrlBean)urlsInfoMap.get(urlStr);
				if ( Math.abs(urlBean.getRank()-rank) > THRESHOLD) {
					finished = false;
				}
				// update the rank of url
				urlBean.setRank(rank);
				urlsInfoMap.put(urlStr, urlBean); 
				
				System.out.println("round:" + (roundCount++));
			}
			
			
		}
		
		updateRank();
		
	}
	
	/**
	 * Update Ranks
	 */
	private void updateRank() {
		new AllInterface().setRanks(urlsInfoMap);
	}
	
	/**
	 * set the rank of each url to 1
	 */
	private void init () {
		Iterator<String> urlsIte = urlsInfoMap.keySet().iterator();
		String urlStr = null;
		UrlBean urlBean = null;
		while ( urlsIte.hasNext() ) {
			urlStr = urlsIte.next();
			urlBean = (UrlBean)urlsInfoMap.get(urlStr);
			urlBean.setRank(1);
			urlsInfoMap.put(urlStr, urlBean); 
		}
	}
	
}
