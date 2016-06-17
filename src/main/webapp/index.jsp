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
	 <!--     通过代码加载js内容 -->
	 <%--  <script type="text/javascript" src='<%=basePath%>userRecord/userrecord.js?openId=op3EiwkPYRENALJj3gR3Q98ehW1Q&contentId=1'></script>
      --%><!--     直接通过路径访问js内容-->	
	  <script src="<%=path%>/js/userrecord.js" type="text/javascript"></script>		
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
	       <a href="<%=basePath%>userRecord/userrecord.js?x_reader=op3EiwkPYRENALJj3gR3Q98ehW1Q&x_content=1">请求userrecord.js内容</a>
	       <a href="<%=basePath%>userRecord/userappend.js?x_reader=op3Eiwm0MUHS3BfHiip58JF5lb-&x_sharer=op3EiwkPYRENALJj3gR3Q98ehW1Q&x_content=abc">请求userappend.js内容</a>
	       <a href="<%=basePath%>userRecord/userinfo.js?x_reader=op3EiwkPYRENALJj3gR3Q98ehW1Q&x_content=1">请求userinfo.js内容</a>
	    </td>
	</tr>
</table>
  </body>
  
  <script>
  
     userRecord();
    
  </script>
  
</html>

