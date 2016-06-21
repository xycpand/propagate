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
	  <script src="<%=path%>/js/helper.js" type="text/javascript"></script>	
	  <script src="<%=path%>/js/propagate.js" type="text/javascript"></script>
	<style type="text/css">
	.menu li{
		display: inline-block;
	}
	</style>
  </head>
  <body>
  	<div>
	<ul>
	<li><label>服务器</label>
	<select name="host" id="host">
	<option value="<%=basePath%>" selected>当前服务器地址</option>
	</select><input type='txt' id='realhost' >
	<li><label>接口名</label>
	<textarea id='api' cols="60"></textarea>

	</li>
	<li><label>参数</label><textarea id="param" cols="100" rows="15"></textarea></li>
	<li><label>cookie</label><textarea id="cookie" cols="100" rows="2"></textarea></li>
	<li><input type="button" value="提交" id="bt"></li>
	<li><label>响应</label><div id="resp"></div></li>
	</ul>
	</div>
	<table>
	<tr><td colspan="6"><b>用户浏览记录管理</b></td></tr>
	<tr>
	    <td>
	    <button onclick="window.location.href='<%=basePath%>userRecord/article.html?userid=1&articleId=1'">测试流程</button>
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

