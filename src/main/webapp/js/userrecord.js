    /**
	 * 动态加载js
	 */
	var loadJS = function( id, fileUrl ) 
	{ 
		console.log("fileUrl:"+fileUrl);
    	var scriptTag = document.getElementById( id ); 
    	var oHead = document.getElementsByTagName('head').item(0); 
    	var oScript= document.createElement("script"); 
    	if (scriptTag) oHead.removeChild( scriptTag ); 
    	oScript.id = id; 
    	oScript.type = "text/javascript";
    	oScript.onload = oScript.onreadystatechange = function() { 
    		if (!this.readyState || this.readyState === "loaded" || this.readyState === "complete" ) { 
    		    //help(); 
    		    // Handle memory leak in IE 
    		    oScript.onload = oScript.onreadystatechange = null; 
    		} 
		}; 
    	oScript.src=fileUrl ; 
    	oHead.appendChild(oScript); 
	} 
	
	
	
	/**
	 * 获取项目根路径
	 */
	var getRootPath =  function(){
   	    var curWwwPath=window.document.location.href;
   	    var pathName=window.document.location.pathname;
   	    var pos=curWwwPath.indexOf(pathName);
   	    var localhostPaht=curWwwPath.substring(0,pos);
   	    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
   	    return(localhostPaht+projectName);
   	}
    
   	
    /**
	 * shareUrl后面添加x_content,x_sharer参数
	 * 其中x_sharer为cookie中的x_reader 
	 */
	  var add4share = function(shareUrl){
		  var x_content = o.getItem("x_content");
		  //x_sharer为cookie中的x_reader 
		  var x_sharer = o.getItem("x_reader");
		  var param = "x_content="+x_content+"&x_sharer="+x_sharer;
		  if(shareUrl.indexOf("?") > -1){
			  shareUrl += "&" + param;
		  }else{
			  shareUrl += "?" + param;
		  }
		  console.log(shareUrl);
	  }; 
	  
	 
		
		 
		  var originalUrl = window.location.href;
	  	  var x_reader = getUrlParam('x_reader');
	  	  
	  	  //缓存参数：把链接url,内容id,阅读者openid保存到cookie中
		   o.setItem('url', originalUrl); 
		   o.setItem('x_reader',x_reader ); 
		   o.setItem('x_content', x_content); 
	  	  
	  	  
	  	  //x_sharer为cookie中的x_reader 
	      var x_sharer = o.getItem("x_reader");
	      var x_content = o.getItem("x_content");
	      var url = o.getItem("url");
	   	
	   	  console.log("url:"+url); 
	   	  console.log("x_reader:"+x_reader);
	   	  console.log("x_sharer:"+x_sharer);
	  	  console.log("x_content:"+x_content);
	  	 
	   	  //从url中提取x_sharer参数，如果存在则建立连接，加载另一段js
	  	  if(originalUrl.indexOf("x_sharer") > -1){
	  		 var userappendJSUrl = getRootPath()+"/userappend.js?x_reader="+x_reader+"&x_sharer="+x_sharer+"&x_content="+x_content;
	  		 console.log("动态加载userappend.js:"+userappendJSUrl);
	  		 loadJS("userappend",userappendJSUrl);
	  	  }
	   	  
	   	  //add4share建立传播关系：x_sharer 传播给 x_reader
	   	  
	 	  //测试add4share
	   	  add4share("https://www.baidu.com/");
	   	  //测试add4share
	   	  add4share("http://www.baidu.com/s?ie=utf-8");
	    	  
	   	  var userinfo = "微信用户信息";//之后这里要改成 真正的信息参数
	   	  var sendUserInfo =  function(userinfo){
	   		  var userinfoJsUrl = getRootPath()+ "/userinfo.js?openid=xxxx&nickname=xxxx"
	   			+ "&sex=xxxx&province=xxxx&city=xxxx&country=xxxx&headimgurl=xxxx&privilege=xxxx"
	   			 + "&unionid=xxxx&x_content=xxxx";
				 console.log("动态加载userinfo.js:"+userinfoJsUrl);
				 loadJS("userappend",userinfoJsUrl);
	   	  }
	    	 
  