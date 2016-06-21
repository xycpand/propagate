<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="http://localhost:6060/propagate/">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	  <script src="${basePath}/js/helper.js" type="text/javascript"></script>	
	  <script src="${basePath}/js/propagate.js" type="text/javascript"></script>
	<style type="text/css">
	.menu li{
		display: inline-block;
	}
	</style>
	${head!""}
  </head>
  <body>
		<center>处理逻辑：</center>
		<center>1、加载userread.js</center>
		<center>2、把链接url,内容id,阅读者openid保存到cookie中</center>
		<center>3、判断url中是否有originalUserid参数，如果存在则需要通过该方法加载userappend.js</center>
	<div>
	    <ul>
		  <li><input type="button"  onclick="add4share('www.baidu.com')" value="分享" id="add4share"></li>
		  <li><input type="button" onclick='sendUserInfo()'  value="上报微信用户信息" id="sendUserInfo"></li>
		</ul>
	</div>
   ${foot!""}
  </body>
  <script>
	var errorInfo = '${errorInfo}';
	if(errorInfo){
		alert(errorInfo);
	}
  </script>
</html>

