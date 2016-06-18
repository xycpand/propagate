        /**
		 * shareUrl后面添加x_content,x_sharer参数
		 * 其中originalUserid为cookie中的userid 
		 */
		  var add4share = function(shareUrl){
			  var articleId = o.getItem("articleId");
			  //originalUserid为cookie中的userid 
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
	   	  add4share(getRootPath(window.location.href)+"/userRecord/userappend.js");
	   	  add4share("https://www.baidu.com/");
	   	  add4share("http://www.baidu.com/s?ie=utf-8");
	   	  
	   	  
		  var userRecord = function(){
			  //阅读时的链接
		        var originalUrl = window.location.href;
		        //阅读者id
		        var userid = 1; //之后要换成真正的阅读用户id getUrlParam('userid');
		        //分享者用户id
		        var originalUserid = 2;  //之后要换成真正的分享用户id getUrlParam('originalUserid');
		        //分享内容表id
		        var articleId = 3; //getUrlParam('articleId');
		        // 分享类型
		        var shareType ; 
		        // 分享目标
		        var shareTarget ;      
		  	  //缓存参数：把链接url,文章id,阅读者id保存到cookie中
			   o.setItem('originalUrl', originalUrl); 
			   o.setItem('articleId', articleId); 
			   o.setItem('userid',userid); 
		  	  
		  	  //originalUserid为cookie中的userid 
		      var originalUserid = o.getItem("userid");
		      var articleId = o.getItem("articleId");
		      var originalUrl = o.getItem("originalUrl");

		   	  console.log("originalUrl:"+originalUrl); 
		   	  console.log("userid:"+userid);
		   	  console.log("originalUserid:"+originalUserid);
		  	  console.log("articleId:"+articleId);
		  	 
		   	  /**
		   	   * 从url中提取originalUserid参数，如果存在则建立连接，加载另一段js
		   	   *
		   	   */
		  	  if(originalUrl.indexOf("originalUserid") > -1){
		  		 var userappendJSUrl = getRootPath()+"/userRecord/userappend.js?userid="+userid+"&originalUserid="+originalUserid+"&articleId="+articleId;
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
	    	 
  