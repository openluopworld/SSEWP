package com.luop.pagerank.bean;

/**
 * Link Bean
 * @author LuoPeng
 * @time 2015.6.18
 *
 */
public class LinkBean {

	private String srcurl  = null; // the source url of a link
	private String desturl = null; // the destination url of a link
	
	public String getSrcurl() {
		return srcurl;
	}
	public void setSrcurl(String srcurl) {
		this.srcurl = srcurl;
	}
	public String getDesturl() {
		return desturl;
	}
	public void setDesturl(String desturl) {
		this.desturl = desturl;
	}
	
}
