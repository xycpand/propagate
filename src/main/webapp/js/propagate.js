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
			  var articleId = o.getItem("articleId");
			  //x_sharer为cookie中的x_reader 
			  var originalUserid = o.getItem("userid");
			  var param = "articleId="+articleId+"&originalUserid="+originalUserid;
			  if(shareUrl.indexOf("?") > -1){
				  shareUrl += "&" + param;
			  }else{
				  shareUrl += "?" + param;
			  }
			  console.log(shareUrl);
		  }; 
	  
		  //add4share建立传播关系：originalUserid 传播给 userid
		  
		  //测试add4share
	   	  add4share("https://www.baidu.com/");
	   	  
	   	  //测试add4share
	   	  add4share("http://www.baidu.com/s?ie=utf-8");
	   	  
	   	  
		  var userRecord = function(){
		  	  var userid = getUrlParam('userid');
		  	  
		  	  //缓存参数：把链接url,内容id,阅读者openid保存到cookie中
			   o.setItem('url', originalUrl); 
			   o.setItem('userid',userid); 
			   o.setItem('articleId', articleId); 

			   o.setItem('test', "test successed"); 
		  	  
		  	  
		  	  //x_sharer为cookie中的x_reader 
		      var originalUserid = o.getItem("userid");
		      var articleId = o.getItem("articleId");
		      var url = o.getItem("url");

		   	  console.log("test:"+o.getItem("test")); 
		   	  console.log("url:"+url); 
		   	  console.log("userid:"+userid);
		   	  console.log("originalUserid:"+originalUserid);
		  	  console.log("articleId:"+articleId);
		  	 
		   	  //从url中提取x_sharer参数，如果存在则建立连接，加载另一段js
		  	  if(originalUrl.indexOf("originalUserid") > -1){
		  		 var userappendJSUrl = getRootPath()+"/userappend.js?userid="+userid+"&originalUserid="+originalUserid+"&articleId="+articleId;
		  		 console.log("动态加载userappend.js:"+userappendJSUrl);
		  		 loadJS("userappend",userappendJSUrl);
		  	  }
		  }
		  
	    	  
	   	  var userinfo = "微信用户信息";//之后这里要改成 真正的信息参数
	   	  var sendUserInfo =  function(userinfo){
	   		  var userinfoJsUrl = getRootPath()+ "/userinfo.js?openid=xxxx&nickname=xxxx"
	   			+ "&sex=xxxx&province=xxxx&city=xxxx&country=xxxx&headimgurl=xxxx&privilege=xxxx"
	   			 + "&unionid=xxxx&articleId=xxxx";
				 console.log("动态加载userinfo.js:"+userinfoJsUrl);
				 loadJS("userappend",userinfoJsUrl);
	   	  }
	    	 
  