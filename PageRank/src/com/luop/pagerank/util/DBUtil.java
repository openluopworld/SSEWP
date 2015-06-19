package com.luop.pagerank.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Get connection to database
 * @author LuoPeng
 * @time 2015.6.18
 *
 */
class DBUtil {

	protected Connection conn = null;
	protected Statement state = null;
	protected PreparedStatement pState = null;
	protected ResultSet resultSet = null;
	protected String sql = null;
	
	/**
	 * Get connection to database 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	protected Connection getConnection () throws ClassNotFoundException, SQLException {
		
		String driverName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/pagerank";
		String user = "root";
		String password = "root";
		
		Class.forName(driverName);
		Connection conn = DriverManager.getConnection(url, user, password);
		
		return conn;
	}
	
	/**
	 * Close the resource of database
	 * @throws SQLException
	 */
	protected void dbClose () throws SQLException {
		
		if (resultSet != null) {
			resultSet.close();
			resultSet = null;
		}
		if (pState != null) {
			pState.close();
			pState = null;
		}
		if (state != null) {
			state.close();
			state = null;
		}
		if (conn != null) {
			conn.close();
			conn = null;
		}
		
	}
}
