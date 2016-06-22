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
	<script src="<%=path%>/js/jquery-1.11.1.js"></script>
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
	<option value="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath() %>" selected>当前服务器地址</option>
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
	<tr><td colspan="2">测试js和接口</td></tr>
	<tr>
	    <td>
	       <button onclick="window.location.href='<%=basePath%>userRecord/article.html?userid=1&articleId=1'">进入阅读文章页面</button>
	       <button onclick="window.location.href='<%=basePath%>userRecord/article.html?userid=2&originalUserid=1&articleId=1'">通过分享链接进入阅读文章页面</button>
	    </td>
	    <td><input type="button"  value="保存用户分享记录"  onclick='setbinding("/userRecord/saveShareArticleRecord","{\"app\":{\"appId\":\"propagate\",\"timeStamp\":\"TIMESTAMP\",  \"nonce\":\"NONCE\", \"signature\":\"21aa0011472249b4292e81504f3917bd\"  },  \"body\":{\"token\":\"e11eba87d5e1fc3e74ebd242ee67b94f\",\"articleId\":1}}}}")'></td>
	      <!--测试 保存用户阅读记录 -->
	       <a href="<%=basePath%>userRecord/userread.js?userid=1&originalUrl=www.baidu.com&originalUserid=2&articleId=1">测试请求userrecord.js内容</a>
	      <!--测试 保存用户分享记录 -->
	       <a href="<%=basePath%>userRecord/userappend.js?userid=1&originalUrl=www.baidu.com&originalUserid=2&articleId=1">测试请求userappend.js内容</a>
	      <!--测试 上报微信用户信息  -->
	       <a href="<%=basePath%>userRecord/userinfo.js?openid=1&nickname=小明">测试请求userinfo.js内容</a>
	</tr>
</table>
  </body>
    <script>
    var type='payload'
    
		$("#bt").click(function (){
			$("#resp").html("");
			var hostval = $("#realhost").val()
			if(hostval==''){
				hostval=$("#host").val()
			}
			var url =hostval+$("#api").val();
			var data = $("#param").val();
			if(type=='payload')
			{
				var vcookie = $('#cookie').val();
				if(vcookie!=''){
					try{
						vcookie = eval('('+vcookie+')');
						for(item in vcookie){
							$.cookie(item, vcookie[item]);
						}
					}
					catch(e){console.print(e)}
					
				}
				
				$.ajax({type:'POST',contentType:'application/json',url:url,data:data,
				success:function(resp){ $("#resp").text(resp);},dataType:"html"}
				);
			}
			else if(type=='cookie'){
				data = eval('('+data+')');
				for(item in data){
					$.cookie(item, data[item]);
				}
				$.ajax({type:'POST',contentType:'application/json',url:url,
					success:function(resp){ $("#resp").text(resp);},dataType:"html"}
					);
			}
			else if(type=='formdata'){
				data = eval('('+data+')');
				$.ajax({type:'POST',url:url,data:data,
					success:function(resp){ $("#resp").text(resp);},dataType:"html"}
				);
			}
			else if(type=='string'){
				//以string 的形式提交，参数名为param
				$.ajax({type:'POST',url:url,data:{param:data},
					success:function(resp){ $("#resp").text(resp);},dataType:"html"}
					);
			}
			else if(type=='xml'){
				$.ajax({type:'POST',contentType:'application/xml',url:url,data:data,
					success:function(resp){ 
						var str = serializeXml(resp)
						$("#resp").text(str);
					},dataType:"xml"});
			}
			
		});
		
    function setbinding4string(api,json){
    	setbinding(api,json,null,'string');
    }
    
		function setbinding(api,json,cookiejson,submittype){
			$("#api").val(api)
			$("#param").val(json.replace("\$\{systime\}",new Date().getTime()))
			$("#cookie").val(cookiejson)
			if(!submittype){
				submittype= 'payload';
			}
			type=submittype;
		}
		
		jQuery.cookie = function(name, value, options) {
			if (typeof value != 'undefined') {
			   options = options || {};
			   if (value === null) {
			    value = '';
			    options = $.extend({}, options);
			    options.expires = -1;
			   }
			   var expires = '';
			   if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
			    var date;
			    if (typeof options.expires == 'number') {
			     date = new Date();
			     date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
			    } else {
			     date = options.expires;
			    }
			    expires = '; expires=' + date.toUTCString();
			   }
			   var path = options.path ? '; path=' + (options.path) : '';
			   var domain = options.domain ? '; domain=' + (options.domain) : '';
			   var secure = options.secure ? '; secure' : '';
			   document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
			} else {
			   var cookieValue = null;
			   if (document.cookie && document.cookie != '') {
			    var cookies = document.cookie.split(';');
			    for (var i = 0; i < cookies.length; i++) {
			     var cookie = jQuery.trim(cookies[i]);
			     if (cookie.substring(0, name.length + 1) == (name + '=')) {
			      cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
			      break;
			     }
			    }
			   }
			   return cookieValue;
			}
			};
			
			function setbinding_cookie(api,json){
				$("#api").val(api)
				$("#param").val(json)
				type = 'cookie';
				
			}
			
		    function setbinding4xml(api,json){
		    	setbinding(api,json,null,'xml');
		    }
		
	</script>
  
</html>

