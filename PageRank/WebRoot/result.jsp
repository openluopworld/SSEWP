<%@ page language="java" 
	import="java.util.*, com.luop.pagerank.util.AllInterface, com.luop.pagerank.bean.UrlBean" 
	pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'result.jsp' starting page</title>
    
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
  <%
  	String keywords = request.getParameter("keywords");
  	List<Object> list = new AllInterface().getUrlByKeywords(keywords);
  	UrlBean urlBean = null;
   %>
   <center>
   <table>
   <%
	   	for ( int i = 0; i < list.size(); i++) {
	   		urlBean = (UrlBean)list.get(i);
	   		
   	%>
   			<tr>
   			<td>
   				<a href="<%=urlBean.getId()%>"><%=urlBean.getTitle() %></a>
   			</td>
   			</tr>
   	<% 
   		}
   	%>
   </table>
   </center>
  </body>
</html>
