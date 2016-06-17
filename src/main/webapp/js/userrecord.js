 //;$(function(){
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
	 * 获取当前URL参数值
	 */
	var getUrlParam =  function(name,url) {
		   var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");
		   var r =  window.location.search.substr(1).match(reg);
		   if (r!=null) 
			   return unescape(r[2]); 
		   return null;
	}
	
	/**
	 * 获取项目根路径
	 */
	var getRootPath =  function(){
   	    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
   	    var curWwwPath=window.document.location.href;
   	    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
   	    var pathName=window.document.location.pathname;
   	    var pos=curWwwPath.indexOf(pathName);
   	    //获取主机地址，如： http://localhost:8083
   	    var localhostPaht=curWwwPath.substring(0,pos);
   	    //获取带"/"的项目名，如：/uimcardprj
   	    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
   	    return(localhostPaht+projectName);
   	}
   
    
    
   	
    /**
	 * shareUrl后面添加x_content,x_sharer参数
	 * 其中x_sharer为cookie中的x_reader 
	 */
	  var add4share = function(shareUrl){
		  var x_content = $.cookie("x_content");
		  //x_sharer为cookie中的x_reader 
		  var x_sharer = $.cookie("x_reader");
		  var param = "x_content="+x_content+"&x_sharer="+x_sharer;
		  if(shareUrl.indexOf("?") > -1){
			  shareUrl += "&" + param;
		  }else{
			  shareUrl += "?" + param;
		  }
		  console.log(shareUrl);
	  }; 
	 
		//获取http协议中的url
		var getUrlFromHttp = function(e) {
			var t = /http:\/\/([^\/]+)\//i;
			var n = e.match(t);
			return n[1]
		};
		  console.log("url from http: "+getUrlFromHttp("http://localhost:6060/propagate/index.jsp"));
    // });
  