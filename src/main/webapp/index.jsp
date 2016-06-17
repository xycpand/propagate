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
	<script src="<%=basePath%>/js/jquery-1.11.1.js"></script>
	 <!--     通过代码加载js内容 -->
	   <script type="text/javascript" src='<%=basePath%>/userRecord/userrecord.js?openId=op3EiwkPYRENALJj3gR3Q98ehW1Q&contentId=1'></script>
     <!--     直接通过路径访问js内容-->	
     <!--<script src="<%=path%>/js/jquery-1.11.1.js" type="text/javascript"></script>-->
	  <script src="<%=path%>/js/jquery.cookie.js" type="text/javascript"></script>
	 <!--  引入数说js -->
	  <script src="<%=path%>/js/datastory.js" type="text/javascript"></script>	
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
	       <a href="<%=basePath%>/userRecord/userrecord.js?x_reader=op3EiwkPYRENALJj3gR3Q98ehW1Q&x_content=1">js方式请求数据</a>
	    </td>
	</tr>
</table>
  </body>
  
    <script>
    
    $(document).ready(function(){
    	console.log("window.location.href:"+ window.location.href); 
        console.log("window.location.search:"+ window.location.search); 
  	  var originalUrl = window.location.href;
  	  var x_reader = getUrlParam('x_reader');
  	  //x_sharer为cookie中的x_reader 
  	  var x_sharer = $.cookie("x_reader");
  	  var x_content = getUrlParam('x_content');
   	  //把链接url,内容id,阅读者openid保存到cookie中
   	  $.cookie('url', originalUrl); 
   	  $.cookie('x_reader',x_reader ); 
   	  $.cookie('x_content', x_content); 
   	  //可以设置失效时间expires
   	 //$.cookie("x_content",x_content,{expires:365});
   	  console.log("url:"+ $.cookie('url')); 
   	  console.log("x_reader:"+x_reader);
   	  console.log("x_sharer:"+x_sharer);
  	  console.log("x_content:"+x_content);
  	 
   	  //从url中提取x_sharer参数，如果存在则建立连接，加载另一段js
      //var isExistX_sharer =  getUrlParam("x_sharer",originalUrl);
      //console.log("isExistX_sharer:"+isExistX_sharer);
  	  if(originalUrl.indexOf("x_sharer") > -1){
  		 var userappendJSUrl = getRootPath()+"/userappend.js?x_reader="+x_reader+"&x_sharer="+x_sharer+"&x_content="+x_content;
  		 console.log("动态加载userappend.js:"+userappendJSUrl);
  		 loadJS("userappend",userappendJSUrl);
  	  }
  	     
    	  add4share("https://www.baidu.com/");
    	  
    	  add4share("http://www.baidu.com/s?ie=utf-8");
    	  
    	  console.log("项目根目录："+getRootPath());
    	  
    	  var userinfo = "微信用户信息";//之后这里要改成 真正的信息参数
    	  var sendUserInfo =  function(userinfo){
    		  var userinfoJsUrl = getRootPath()+ "/userinfo.js?openid=xxxx&nickname=xxxx"
    			+ "&sex=xxxx&province=xxxx&city=xxxx&country=xxxx&headimgurl=xxxx&privilege=xxxx"
    			 + "&unionid=xxxx&x_content=xxxx";
 			 console.log("动态加载userinfo.js:"+userinfoJsUrl);
 			 loadJS("userappend",userinfoJsUrl);
    	  }
    	 
   });
    
    
	</script>
  
</html>

