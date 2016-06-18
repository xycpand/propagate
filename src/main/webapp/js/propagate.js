         //阅读时的链接
        var originalUrl = window.location.href;
        //阅读者id
        var userid;
        //分享者用户id
        var originalUserid ;  
        //分享内容表id
        var articleId ;
        // 分享类型
        var shareType ; 
        // 分享目标
        var shareTarget ;          
        
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
	  
		  //add4share建立传播关系：x_sharer 传播给 x_reader
		  
		  //测试add4share
	   	  add4share("https://www.baidu.com/");
	   	  
	   	  //测试add4share
	   	  add4share("http://www.baidu.com/s?ie=utf-8");
	   	  
	   	  
		  var userRecord = function(){
		  	  var x_reader = getUrlParam('x_reader');
		  	  
		  	  //缓存参数：把链接url,内容id,阅读者openid保存到cookie中
			   o.setItem('url', originalUrl); 
			   o.setItem('x_reader',x_reader); 
			   o.setItem('x_content', x_content); 

			   o.setItem('test', "test successed"); 
		  	  
		  	  
		  	  //x_sharer为cookie中的x_reader 
		      var x_sharer = o.getItem("x_reader");
		      var x_content = o.getItem("x_content");
		      var url = o.getItem("url");

		   	  console.log("test:"+o.getItem("test")); 
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
		  }
		  
	    	  
	   	  var userinfo = "微信用户信息";//之后这里要改成 真正的信息参数
	   	  var sendUserInfo =  function(userinfo){
	   		  var userinfoJsUrl = getRootPath()+ "/userinfo.js?openid=xxxx&nickname=xxxx"
	   			+ "&sex=xxxx&province=xxxx&city=xxxx&country=xxxx&headimgurl=xxxx&privilege=xxxx"
	   			 + "&unionid=xxxx&x_content=xxxx";
				 console.log("动态加载userinfo.js:"+userinfoJsUrl);
				 loadJS("userappend",userinfoJsUrl);
	   	  }
	    	 
  