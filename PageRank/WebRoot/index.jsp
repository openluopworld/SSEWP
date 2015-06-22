<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body bgcolor="#f6f5ec">
	  <form action="result.jsp" method="get">
		  <input type="text" height="20px" width="200px" name="keywords">
		  <input type="submit" value="搜索">
	  </form>
	  <hr color="red">
	  <p>"/pagerank/crawl" to get urls from the Internet</p>
	  <p>"/pagerank/compute" to compute the rank of each url.</p>
	  <p>"/pagerank/index.jsp" input the key word, and search for result.</p>
  </body>
</html>
