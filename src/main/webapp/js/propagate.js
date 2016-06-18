         /**
		 * shareUrl后面添加articleId,originalUserid参数
		 * 其中originalUserid为cookie中的userid 
		 */
		  var add4share = function(shareUrl){
			  var articleId = o.getItem("articleId");
			  //originalUserid为cookie中的userid 
			  var originalUserid = o.getItem("userid");
			  var param = "originalUserid="+originalUserid;
			  if(articleId){
				  param+="&articleId="+articleId;
		  	  }
			  if(shareUrl.indexOf("?") > -1){
				  shareUrl += "&" + param;
			  }else{
				  shareUrl += "?" + param;
			  }
		  }; 
	  
		  //add4share建立传播关系：originalUserid 传播给 userid
		  //测试add4share
	   	  add4share(getRootPath(window.location.href)+"/userRecord/userappend.js");
	   	  add4share("https://www.baidu.com/");
	   	  add4share("http://www.baidu.com/s?ie=utf-8");
	   	  
	   	  var userinfo = "微信用户信息";//之后这里要改成 真正的信息参数
	   	  var sendUserInfo =  function(userinfo){
	   		  var userinfoJsUrl = getRootPath()+ "/userinfo.js?openid=xxxx&nickname=xxxx"
	   			 + "&sex=xxxx&province=xxxx&city=xxxx&country=xxxx&headimgurl=xxxx&privilege=xxxx"
	   			 + "&unionid=xxxx&articleId=xxxx";
				 console.log("动态加载userinfo.js:"+userinfoJsUrl);
				 loadJS("userappend",userinfoJsUrl);
	   	  }
	   	  
	   	  
	        /**
			 * 初始化信息
			 */
		  var initUserInfo = function(){
			   //阅读时的链接
		        var originalUrl = window.location.href;
		        //阅读者id
		        var userid = getUrlParam('userid');
		        //分享者用户id
		        var originalUserid =  getUrlParam('originalUserid');
		        //分享内容表id
		        var articleId = getUrlParam('articleId');
		        // 分享类型
		        var shareType ; 
		        // 分享目标
		        var shareTarget ;      
		  	  //缓存参数：把链接url,文章id,阅读者id保存到cookie中
			   o.setItem('originalUrl', originalUrl); 
			   o.setItem('articleId', articleId); 
			   o.setItem('userid',userid); 
		  	  
		   	  console.log("originalUrl:"+o.getItem("userid")); 
		   	  console.log("userid:"+o.getItem("userid"));
		   	  //originalUserid为cookie中的userid 
		   	  console.log("originalUserid:"+o.getItem("userid"));
		  	  console.log("articleId:"+o.getItem("userid"));
		  	 
		  	  //加载userrecord.js
		  	 var jsUrl = getRootPath()+"/userRecord/userrecord.js?userid="+userid
		  		 +"&originalUrl="+originalUrl+"&originalUserid="+originalUserid;
		  		 if(articleId){
		  			jsUrl+="&articleId="+articleId;
		  		 }
		  		 console.log("userrecord.js:"+jsUrl);
		  		 loadJS("userrecord",jsUrl);
		  	  
		  	  
		   	  /**
		   	   * 从url中提取originalUserid参数，如果存在则建立连接，加载另一段js
		   	   */
		  	  if(originalUserid){
		  		 var userappendJSUrl = getRootPath()+"/userRecord/userappend.js?userid="+userid
		  		+"&originalUrl="+originalUrl+"&originalUserid="+originalUserid;
		  		 if(articleId){
		  			userappendJSUrl+="&articleId="+articleId;
		  		 }
		  		 console.log("动态加载userappend.js:"+userappendJSUrl);
		  		 loadJS("userappend",userappendJSUrl);
		  	  }
		  }//end of initUserInfo
		  
	   	
	   	  
	   	  //初始化用户信息
	     initUserInfo();
	     
	    	 
  