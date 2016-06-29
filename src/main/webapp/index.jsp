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
	<%--  <script src="<%=path%>/js/propagate.js" type="text/javascript"></script> --%>
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
	<tr><td colspan="2"><b>接口测试</b></td></tr>
	     <tr> 
	         <td><input type="button"  value="保存文章分享记录"  onclick='setbinding("/userRecord/saveShareRecord","{\"app\":{\"appId\":\"propagate\",\"timeStamp\":\"TIMESTAMP\",  \"nonce\":\"NONCE\", \"signature\":\"21aa0011472249b4292e81504f3917bd\"  },  \"body\":{\"openId\":\"op3EiwqSh-WmyWvP1776y6DBW5bc\",\"articleId\":1,\"originalUrl\":\"http://xp.fengniao.info/article/show.html?x_articleId=1\",\"remark\":\"分享时填写的分享内容\",\"shareType\":null,\"shareTarget\":null}}}}")'></td>
	         <td><input type="button"  value="保存文章信息"  onclick='setbinding("/userRecord/saveArticle","{\"app\":{\"appId\":\"propagate\",\"timeStamp\":\"TIMESTAMP\",  \"nonce\":\"NONCE\", \"signature\":\"21aa0011472249b4292e81504f3917bd\"  },  \"body\":{\"openId\":\"op3EiwqSh-WmyWvP1776y6DBW5bc\",\"articleId\":1,\"title\":\"我就是文章标题\",\"content\":null,\"tagIds\":\"1&2\",\"tagNames\":\"1号标签&2号标签\"}}}}")'></td>
	     </tr>
		<tr><td colspan="2"><b>测试js</b></td></tr>
		<tr>
		    <td>
		       <button onclick="window.location.href='<%=basePath%>userRecord/testJS.html?x_reader=oCmwKvwib0Ahz8ugW0lFk8HTpljI&x_articleId=1&x_sharer=op3EiwqwcqMFpRq04dQL1k1neb5M'">进入测试js页面</button>
		    </td>
		    <td>
		       <button onclick="window.location.href='<%=basePath%>userRecord/article.html?x_reader=oCmwKvwib0Ahz8ugW0lFk8HTpljI&x_articleId=1'">进入阅读文章页面</button>
		    </td>
		     <td>
		       <button onclick="window.location.href='<%=basePath%>userRecord/article.html?x_reader=999&x_sharer=op3EiwqwcqMFpRq04dQL1k1neb5M&x_articleId=1'">通过分享链接进入阅读文章页面</button>
		    </td>
		   <td>
		<%--   <!--测试 保存用户阅读记录 -->
	       <a href="<%=basePath%>userRecord/userread.js?openId=oCmwKv9ErXuGDmJYWGV2KSxEYj6A&originalUrl=www.baidu.com&originalOpenId=FnVp5baN9aSIUcwo&articleId=1">测试请求userrecord.js内容</a>
	      <!--测试 保存用户分享记录 -->
	       <a href="<%=basePath%>userRecord/userappend.js?openId=oCmwKvxo1PnytDlJEuXeQVFC5xwo&originalUrl=www.baidu.com&originalOpenId=oCmwKv9ErXuGDmJYWGV2KSxEYj6A&articleId=1">测试请求userappend.js内容</a>
	      <!--测试 上报微信用户信息  -->
	       <a href="<%=basePath%>userRecord/userinfo.js?openid=FnVp5baN9aSIUcwo&nickname=小明">测试请求userinfo.js内容</a></td>
        --%>
         </tr>
	<tr><td colspan="6">标签相关接口</td></tr>
	<tr>
		<td><input type="button"  value="查询用户标签"  onclick='setbinding("/userTag/queryUserTag","{\"app\":{\"appId\":\"propagate\",\"timeStamp\":\"TIMESTAMP\",  \"nonce\":\"NONCE\", \"signature\":\"21aa0011472249b4292e81504f3917bd\"  },  \"body\":{\"openid\":\"op3EiwqSh-WmyWvP1776y6DBW5bc\"}}")'></td>
		<td><input type="button"  value="查询某文章的标签"  onclick='setbinding("/userTag/queryArticleTag","{\"app\":{\"appId\":\"propagate\",\"timeStamp\":\"TIMESTAMP\",  \"nonce\":\"NONCE\", \"signature\":\"21aa0011472249b4292e81504f3917bd\"  },  \"body\":{\"openid\":\"op3EiwqSh-WmyWvP1776y6DBW5bc\",\"articleId\":1}}")'></td>
		<td><input type="button"  value="文章添加标签"  onclick='setbinding("/userTag/addArticleTag","{\"app\":{\"appId\":\"propagate\",\"timeStamp\":\"TIMESTAMP\",  \"nonce\":\"NONCE\", \"signature\":\"21aa0011472249b4292e81504f3917bd\"  },  \"body\":{\"openid\":\"op3EiwqSh-WmyWvP1776y6DBW5bc\",\"articleId\":1,\"tagName\":[\"宝贝\",\"游戏\",\"活动\"]}}")'></td>
		<td><input type="button"  value="文章删除标签"  onclick='setbinding("/userTag/delArticleTag","{\"app\":{\"appId\":\"propagate\",\"timeStamp\":\"TIMESTAMP\",  \"nonce\":\"NONCE\", \"signature\":\"21aa0011472249b4292e81504f3917bd\"  },  \"body\":{\"openid\":\"op3EiwqSh-WmyWvP1776y6DBW5bc\",\"articleId\":1,\"tagName\":[\"宝贝\",\"游戏\",\"活动\"]}}")'></td>
		<td><input type="button"  value="查询热门标签"  onclick='setbinding("/userTag/queryHotTag","{\"app\":{\"appId\":\"propagate\",\"timeStamp\":\"TIMESTAMP\",  \"nonce\":\"NONCE\", \"signature\":\"21aa0011472249b4292e81504f3917bd\"  },  \"body\":{\"openid\":\"op3EiwqSh-WmyWvP1776y6DBW5bc\"}}")'></td>
	
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

