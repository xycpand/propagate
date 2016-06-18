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
			  console.log(shareUrl);
		  }; 
	  
		  //add4share建立传播关系：originalUserid 传播给 userid
		  //测试add4share
	   	  add4share(getRootPath()+"/userRecord/userappend.js");
	   	  
	   	  var userinfo = "微信用户信息";//之后这里要改成 真正的信息参数
	   	  var sendUserInfo =  function(userinfo){
	   		  var sex = "M";
	   		  var subscribe;
	   		  var subscribeTime;
	   		  var groupid;
	   		  var qrExpireSeconds;
	   		  var qrCreateTime;
	   		  var userinfoJsUrl = getRootPath()+ "/userRecord/userinfo.js?openid=1&nickname=小明" +
	   		  	  "&language=中文&unionid=1&remark=备注&sex=M&province=广东省&city=深圳市" +
	   		  	   "&country=南山区&headimgurl=xxxx&privilege=xxxx&articleId=1&Ticket=xxxx&tagidList=xxxx";
	   		  if(subscribe){
	   			  userinfoJsUrl+="&subscribe="+subscribe;
		  	  }  
	   		  if(subscribeTime){
	   			  userinfoJsUrl+="&subscribeTime="+subscribeTime;
		  	  }  
	   		  if(groupid){
	   			userinfoJsUrl+="&groupid="+groupid;
		  	  } 
	   		  if(qrExpireSeconds){
	   			userinfoJsUrl+="&qrExpireSeconds="+qrExpireSeconds;
		  	  }
	   		  if(qrCreateTime){
	   			userinfoJsUrl+="&qrCreateTime="+qrCreateTime;
		  	  }
			 console.log("动态加载userinfo.js:"+userinfoJsUrl);
			 loadJS("userinfo",userinfoJsUrl);
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
	      
		   //测试
		   sendUserInfo("");
	     
	     
	     
	     
	    	 
  