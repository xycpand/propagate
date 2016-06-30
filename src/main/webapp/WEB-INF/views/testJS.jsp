<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${basePath}">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	  <script src="${basePath}/js/test-propagate.js" type="text/javascript"></script>
	<style type="text/css">
	.menu li{
		display: inline-block;
	}
	</style>
	${head!""}
	<link rel="shortcut icon" href="/favicon.ico">
  </head>
  <body>
  <center>查看控制台</center>
   ${foot!""}
  </body>
  <script>
  console.log("getRootPath:"+getRootPath());
  console.log("getContextPath:"+getContextPath());
  
  
  o.setItem('x_articleId',666); 
  console.log(o.getItem("x_articleId"));
  
  console.log("document.referrer):"+document.referrer);
  console.log("getUrlFromHttp(document.referrer):"+getUrlFromHttp(document.referrer));
  console.log("getDomain(getUrlFromHttp(document.referrer)):"+getDomain(getUrlFromHttp(document.referrer)));
  
  
  console.log(getDomain("http://localhost:6060/if/index.jsp"));
   
  console.log(getDomain(location.hostname));
  console.log("document.referrer:"+document.referrer);
  
  console.log("location.hostname："+location.hostname);
  
  var testURL = "http://xp.fengniao.info/article/show.html?x_articleId=3&x_sharer=op3EiwqwcqMFpRq04dQL1k1neb5M&x_sharer=op3EiwjwtPolgdCP-c-wlxgJ579A"
	
		  console.log(getUrlParam("x_articleId"));
 	
	
	var shareParam = "openId=1&articleId=1&originalUrl=baidu.com&amp;remark=分享时填写的分享内容&shareType=1&shareTarget=2";
	   
	console.log(getQueryString(shareParam,"remark"));
	
	console.log(replaceParamVal(testURL,"without","addSuccess"));
	console.log(replaceParamVal(testURL,"without","replaceSuccess"));
	
	
	  
	  console.log(add4share());
	  
	  
	  var originalUrl = window.location.href;
	  console.log(originalUrl);
	   originalUrl =encodeURIComponent(originalUrl);
	  console.log(originalUrl);
	  console.log(decodeURIComponent(originalUrl));
	  
	  
  </script>
</html>

