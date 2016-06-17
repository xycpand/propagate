(function() {
		var e = 60 * 30;
		//获取指定的参数的值
		var t = function(e) {
			var t = new RegExp("(^|&)" + e + "=([^&]*)(&|$)");
			var n = window.location.search.substr(1).match(t);
			if (n != null) return unescape(n[2]);
			return null
		};
		
		//获取http协议中的url
		var n = function(e) {
			var t = /http:\/\/([^\/]+)\//i;
			var n = e.match(t);
			return n[1]
		};

		var r = function(e) {
			if (!e) return "";
			if (e.indexOf("://") != -1) e = e.substr(e.indexOf("://") + 3);
			var t = ["com", "net", "org", "gov", "edu", "mil", "biz", "name", "info", "mobi", "pro", "travel", "museum", "int", "areo", "post", "rec", "im", "cn", "me"];
			var n = e.split(".");
			if (n.length <= 1) return e;
			if (!isNaN(n[n.length - 1])) return e;
			var r = ".com.cn";
			if (e.indexOf(r) != -1) {
				return n[n.length - 3] + "." + n[n.length - 2] + "." + n[n.length - 1]
			}
			var i = 0;
			while (i < t.length && t[i] != n[n.length - 1]) i++;
			if (i != t.length) return n[n.length - 2] + "." + n[n.length - 1];
			else {
				i = 0;
				while (i < t.length && t[i] != n[n.length - 2]) i++;
				if (i == t.length) return n[n.length - 2] + "." + n[n.length - 1];
				else return n[n.length - 3] + "." + n[n.length - 2] + "." + n[n.length - 1]
			}
		};

		//cookie 内容管理
		var i = {
			get: function(e) {
				var t = document.cookie.match(new RegExp("(^| )" + e + "=([^;] * )(; | $)"));
				if (t != null) return unescape(t[2]);
				return null
			},
			add: function(e, t, n) {
				var i = e + "=" + t;
				if (n != 0) {
					var o = new Date;
					var a = n * 1e3;
					o.setTime(o.getTime() + a);
					i +=  ";expires = "+o.toGMTString()
				}
				var s=r(location.hostname);
					if (s != "") {
						i += ";domain = "+s
					}
					i+=";path = /";
					document.cookie = i
			}
		};



				var o = function() {

					var t = {
						setItem: function(t, n) {
							i.add(t, n, e)
						},
						getItem: function(e) {
							return i.get(e)
						},
						removeItem: function(e) {
							i.add(e, "", -1)
						}
					};


					if (sessionStorage) {
						try {
							var o = ["REFER_DSCKID", "REFER_DSTIMESTAMP", "DS_FROM_TYPE"];
							var a = function() {
								if (document.referrer != "") {
									var e = r(n(document.referrer));
									var t = r(location.hostname);
									return e == t
								} else {
									return false
								}
							}();
							if (!a) {
								for (var s = 0; s < o.length; s++) {
									t.removeItem(o[s])
								}
							} else {
								for (var s = 0; s < o.length; s++) {
									var c = sessionStorage.getItem(o[s]);
									var u = t.getItem(o[s]);
									if ((c == null || c == "") && (u != null && u != "")) {
										sessionStorage.setItem(o[s], u)
									}
								}
							}
						} catch (f) {}
					}
					return {
						setItem: function(e, n) {
							sessionStorage && sessionStorage.setItem(e, n);
							t.setItem(e, n)
						},
						getItem: function(e) {
							if (sessionStorage) {
								return sessionStorage.getItem(e)
							} else {
								return t.getItem(e)
							}
						}
					}
				}();

				//从地址中获取必要的参数,并保存起来
				var a = function() {
					var e = location.href;
					if (e.indexOf("DSCKID") != -1) {
						var n = t("DSCKID");
						var r = t("DSTIMESTAMP");
						o.setItem("REFER_DSCKID", n);
						o.setItem("REFER_DSTIMESTAMP", r)
					}
					if (e.indexOf("from") != -1) {
						var i = t("from");
						var a = {
							singlemessage: 1,
							groupmessage: 2,
							timeline: 3
						};
						if (a[i] == undefined) {
							o.setItem("DS_FROM_TYPE", 0)
						} else {
							o.setItem("DS_FROM_TYPE", a[i])
						}
					}
					var s = document.referrer; //上一页的地址
					if (s.indexOf("mp.weixin.qq.com") != -1 || s.indexOf("mp.weixinbridge.com") != -1) {
						o.setItem("DS_FROM_TYPE", 4)
					}
				};
				var s = function() {
					var e = "";
					try {
						e = document.getElementById("DS_PRE_JS").src.split("?")[1].split("=")[1]
					} catch (t) {
						console.log("please read DataStory api doc")
					}
					var n = document.createElement("script");
					n.src = document.location.protocol + "//tongji.datastory.com.cn/ds.js?dsTid=" + e;
					var r = document.getElementsByTagName("script")[0];
					r.parentNode.insertBefore(n, r)
				};
				var initer = function() {
					try {
						if (window.DS == undefined) window.DS = {};
						DS.ready = function(customerInit) {
							var t = function() {
								if (DS.linkChange == undefined || DS.sendRepost == undefined) {
									setTimeout(t, 500)
								} else {
									try {
										customerInit()
									} catch (n) {}
								}
							};
							t()
						};
						a();
						s()
					} catch (e) {}
				};
				initer()
			})();