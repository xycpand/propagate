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
	<style type="text/css">
	.menu li{
		display: inline-block;
	}
	</style>
  </head>
  <body>
	<table>
	<tr>
	     <td>
	       <button onclick="window.location.href='<%=basePath%>userRecord/article.html?userid=1&articleId=1'">进入阅读文章页面</button>
	       <button onclick="window.location.href='<%=basePath%>userRecord/article.html?userid=2&originalUserid=1&articleId=1'">通过分享链接进入阅读文章页面</button>
	    </td>
	    <td>
	      <!--测试 保存用户阅读记录 -->
	       <a href="<%=basePath%>userRecord/userread.js?userid=1&originalUrl=www.baidu.com&originalUserid=2&articleId=1">测试请求userrecord.js内容</a>
	      <!--测试 保存用户分享记录 -->
	       <a href="<%=basePath%>userRecord/userappend.js?userid=1&originalUrl=www.baidu.com&originalUserid=2&articleId=1">测试请求userappend.js内容</a>
	      <!--测试 上报微信用户信息  -->
	       <a href="<%=basePath%>userRecord/userinfo.js?openid=1&nickname=小明">测试请求userinfo.js内容</a>
	    </td>
	</tr>
</table>
  </body>
</html>

