package com.luop.pagerank.bean;

/**
 * Url Bean
 * @author LuoPeng
 * @time 2015.6.18
 *
 */
public class UrlBean {

	private String url = null;
	private int id;
	private String title    = null;
	private String keywords = null;
	private int outlinks;
	private double rank;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public int getOutlinks() {
		return outlinks;
	}
	public void setOutlinks(int outlinks) {
		this.outlinks = outlinks;
	}
	public double getRank() {
		return rank;
	}
	public void setRank(double rank) {
		this.rank = rank;
	}
	
}
