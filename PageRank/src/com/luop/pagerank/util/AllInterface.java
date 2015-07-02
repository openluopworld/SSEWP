package com.luop.pagerank.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.luop.pagerank.bean.UrlBean;

/**
 * the interfaces
 * @author LuoPeng
 * @time 2015.6.17
 *
 */
public class AllInterface extends DBUtil {

	private Map<String, Object> urlsInfoMap = null;
	
	/**
	 * Get all the url info
	 * @return
	 */
	public Map<String, Object> getUrlInfo () {
		try {
			conn = getConnection();
			if ( conn != null ) {
				state = conn.createStatement();
				sql = "select * from urls";
				resultSet = state.executeQuery(sql);
				
				urlsInfoMap = new HashMap<String, Object>();
				UrlBean urlBean = null;
				while (resultSet.next()) {
					urlBean = new UrlBean();
					urlBean.setUrl(resultSet.getString(1));
					urlBean.setId(resultSet.getInt(2));
					urlBean.setTitle(resultSet.getString(3));
					urlBean.setKeywords(resultSet.getString(4));
					urlBean.setOutlinks(resultSet.getInt(5));
					urlBean.setRank(resultSet.getDouble(6));
					urlsInfoMap.put(urlBean.getUrl(), urlBean);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				dbClose();
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
		return urlsInfoMap;
	}
	
	/**
	 * Get all the source urls of a destination url 
	 * @return
	 */
	public Map<String, List<String>> getInLinks () {
		if ( urlsInfoMap == null ) {
			return null;
		}
		
		Map<String, List<String>> inlinks = new HashMap<String, List<String>>();
		List<String> onelinks = null;
		Iterator<String> urls = urlsInfoMap.keySet().iterator();
		String urlStr = null;
		
		try {
			conn = getConnection();
			if ( conn != null) {
				sql = "select distinct srcurl from links where desturl = ?";
				pState = conn.prepareStatement(sql);
				while (urls.hasNext()) {
					urlStr = urls.next();
					pState.setString(1, urlStr);
					resultSet = pState.executeQuery();
					onelinks = new ArrayList<String>();
					while (resultSet.next()) {
						onelinks.add(resultSet.getString(1));
					}
					inlinks.put(urlStr, onelinks);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				dbClose();
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
		
		return inlinks;
	}
	
	/**
	 * Set the final rank of the urls
	 */
	public void setRanks (Map<String, Object> map) {
		try {
			conn = getConnection();
			if ( conn != null) {
				
				Iterator<String> urls = map.keySet().iterator();
				String urlStr = null;
				
				sql = "update urls set rank = ? where url = ?";
				pState = conn.prepareStatement(sql);
				while (urls.hasNext()) {
					urlStr = urls.next();
					pState.setDouble(1, ((UrlBean)map.get(urlStr)).getRank());
					pState.setString(1, urlStr);
					pState.executeUpdate();
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				dbClose();
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
	}
	
	/**
	 * Get to top 10 urls which is related to the key words
	 * @param keywords
	 * @return
	 */
	public List<Object> getUrlByKeywords ( String keywords) {
		List<Object> urls = null;
		try {
			conn = getConnection();
			if ( conn != null ) {
				sql = "select url, title from urls where keywords like '%?%' order by rank desc limit 0, 10";
				pState = conn.prepareStatement(sql);
				pState.setString(1, keywords);
				resultSet = pState.executeQuery();
				
				urls = new ArrayList<Object>();
				UrlBean urlBean = null;
				while (resultSet.next()) {
					urlBean = new UrlBean();
					urlBean.setUrl(resultSet.getString(1));
					urlBean.setTitle(resultSet.getString(3));
					urls.add(urlBean);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				dbClose();
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
		return urls;
	}
	
	/**
	 * Add the info of url to database
	 * @param bean the basic info of url
	 * @param outlinks the outlinks of url
	 * @return true if add successfully, else return false 
	 */
	public boolean addOneUrlInfo ( UrlBean bean, List<String> outlinks ) {
		boolean result = false;
		
		try {
			conn = getConnection();
			if ( conn != null ) {
				state = conn.createStatement();
				sql = "select url from urls where url = '" + bean.getUrl() + "'";
				resultSet = state.executeQuery(sql);
				if ( !resultSet.next() ) { // if the url dose not exist,
					conn.setAutoCommit(false);
					state.executeUpdate("insert into urls values('" + bean.getUrl()
							+ "', " + bean.getId()
							+ ", '" + bean.getTitle()
							+ "', '" + bean.getKeywords()
							+ "', " + bean.getOutlinks()
							+ ", " + bean.getRank()
							+ ")");
					System.out.println("insert into urls values('" + bean.getUrl()
							+ "', " + bean.getId()
							+ ", '" + bean.getTitle()
							+ "', '" + bean.getKeywords()
							+ "', " + bean.getOutlinks()
							+ ", " + bean.getRank()
							+ ")");
					sql = "insert into links values('" + bean.getUrl() + "', '?')";
					for ( int i = 0; i < outlinks.size(); i++ ) {
						state.executeUpdate(sql.replace("?", outlinks.get(i)));
					}
					conn.commit();
					result = true;
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("Rollback, url=" + bean.getUrl());
				e1.printStackTrace();
			}
		} finally {
			try {
				dbClose();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

}
