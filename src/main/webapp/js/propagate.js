      /**
		 * shareUrl后面添加articleId,originalUserid参数
		 * 其中originalUserid为cookie中的userid 
		 */
		var add4share = function(shareUrl){
			  if(!shareUrl){
				  shareUrl = o.getItem("originalUrl");
			  }

			  alert("替换前的分享链接为:"+shareUrl);
			  var articleId = o.getItem("articleId");
			  //originalUserid为cookie中的userid 
			  var originalUserid = o.getItem("userid");
			  var param = "originalUserid="+originalUserid;
			  if(articleId){
				  param+="&articleId="+articleId;
		  	  }
			  if(shareUrl.indexOf("?") > -1){
				  shareUrl = shareUrl.substring(0,shareUrl.indexOf("?"));
			  }
			  alert("后后的分享链接为:"+shareUrl);
			  shareUrl += "?" + param;
			  console.log(shareUrl);
			  alert("分享链接为:"+shareUrl);
		  }; 
	  
		  //add4share建立传播关系：originalUserid 传播给 userid
		  //测试add4share
	   	  // add4share(getRootPath()+"/userRecord/userappend.js");
	   	  
	   	  var user = [];//保存的微信用户信息
	   	  var sendUserInfo =  function(){
	   		  var userinfoJsUrl = getRootPath()+ "/userRecord/userinfo.js?openid=1&nickname=小明" +
	   		  	  "&language=zh_CN&unionid=1&province=广东&city=深圳" +
	   		  	   "&country=中国&headimgurl=xxxx&privilege=xxxx&articleId=1&Ticket=xxxx&tagidList=xxxx";
	   		  //之后上面写死的参数要改成如下的添加方式
	   		  if(user.remark){  userinfoJsUrl+="&remark="+user.remark; } 
	   		  if(user.subscribe){  userinfoJsUrl+="&subscribe="+user.subscribe; } 
	   		  if(user.sex){  userinfoJsUrl+="&sex="+user.sex;  }   
	   		  if(user.subscribeTime){  userinfoJsUrl+="&subscribeTime="+user.subscribeTime; }  
	   		  if(user.groupid){ userinfoJsUrl+="&groupid="+user.groupid; } 
	   		  if(user.qrExpireSeconds){ userinfoJsUrl+="&qrExpireSeconds="+user.qrExpireSeconds;  }
	   		  if(user.qrCreateTime){ userinfoJsUrl+="&qrCreateTime="+user.qrCreateTime; }
			 console.log("动态加载userinfo.js:"+userinfoJsUrl);
			 loadJS("userinfo",userinfoJsUrl);
			 alert("保存用户信息成功。")
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
		  	  
		   	  console.log("originalUrl:"+o.getItem("originalUrl")); 
		   	  console.log("userid:"+o.getItem("userid"));
		   	  //originalUserid为cookie中的userid 
		   	  console.log("originalUserid:"+o.getItem("userid"));
		  	  console.log("articleId:"+o.getItem("userid"));
		  	  console.log("getRootPath:"+getRootPath());
		  	  //加载userrecord.js
		  	 var jsUrl = getRootPath()+"/userRecord/userread.js?userid="+userid;
	  		     if(originalUserid){
		  			jsUrl+="&originalUserid="+originalUserid;
		  		 }
		  		 if(articleId){
		  			jsUrl+="&articleId="+articleId;
		  		 }
		  		 if(originalUrl){
			  			jsUrl+="&originalUrl="+originalUrl;
			  	 }
		  		 console.log("userread.js:"+jsUrl);
		  		 loadJS("userread",jsUrl);
		  	  
		  	  
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
	      
	     
	     
	     
	     
	    	 
  