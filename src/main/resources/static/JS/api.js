/*
 * APICloud JavaScript Library
 * Copyright (c) 2014 apicloud.com
 */
(function(window) {
	// document.write("<script language=javascript src='../script/md.js'></script>");
	// document.write("<script language=javascript src='../script/xyb.js'></script>");
	var u = {};
	var fileTypeArray=[
		'doc','ppt','xls','pdf',
		'txt','rar',
		'mp3','wma','wav','aac',
		'mp4','avi','wmv','rm',
		'jpg','png','gif'
	];
	var isAndroid = (/android/gi).test(navigator.appVersion);
	var uzStorage = function() {
		var ls = window.localStorage;
		if (isAndroid) {
			ls = os.localStorage();
		}
		return ls;
	};
	function parseArguments(url, data, fnSuc, dataType) {
		if ( typeof (data) == 'function') {
			dataType = fnSuc;
			fnSuc = data;
			data = undefined;
		}
		if ( typeof (fnSuc) != 'function') {
			dataType = fnSuc;
			fnSuc = undefined;
		}
		return {
			url : url,
			data : data,
			fnSuc : fnSuc,
			dataType : dataType
		};
	}
	/*自定义功能*/
	u.longPress = function(el, fn) {
		var timeout = undefined;
			el.addEventListener('touchstart', function(event) {
				timeout = setTimeout(fn, 700);
			}, false);
			el.addEventListener('touchend', function(event) {
				clearTimeout(timeout);
			}, false);
			el.addEventListener('touchmove', function(event) {
				clearTimeout(timeout);
			}, false);
	}
	u.each = function(el, fn) {
		for (var i = 0; i < el.length; i++) {
			el[i].index=i;
			fn(el[i]);
		}
	}
	u.initItem = function (el,data,type,muti) {//list填充 <=2层
		var finalCon = '';
		if(type=='init'||type==undefined){
			type='init';
			var initCon = el.innerHTML;
			initCon = initCon.replace('<!--','');
			initCon = initCon.replace('-->','');
			el.precon=initCon;
		}
		for(var i=0,ilen=data.length;i<ilen;i++){//总条目数
			var tempCon=el.precon;
			for(var key in data[i]){//循环替换
				if(typeof(data[i][key])=='string'){
					tempCon=tempCon.replace('{'+key+'}',data[i][key]);
				}
			}
			finalCon+=tempCon;//拼接
		}
		if(type=='init'||type=='inner'){
			el.innerHTML=finalCon;
			el.num = data.length;
		}else if(type=='append'){
			u.append(el,finalCon);
			el.num += data.length;
		}
		if(!(muti==undefined||muti==null)){//二层
			for(var j=0,jlen=muti.length;j<jlen;j++){
				var key=muti[j];
				var allel=u.domAll('.'+key+'Sec');
				for(var k=0,klen=data.length;k<klen;k++){
					// if(!u.hasCls(allel[k],'done')){
						u.initItem(allel[k],data[k][key]);
						u.removeCls(allel[k],key+'Sec');
					// }
				}
			}
		}
	}
	u.myUrl = function(type,url){
		var furl='';
		// if(type=='smaImg') return "http://www.dxsbang.cn/source/DataQsdcxz/small/"+url;
		// else if(type=='midImg') return "http://www.dxsbang.cn/Japp/DataQsdcxz/middle/"+url;
		// else if(type=='orgImg') return "http://www.dxsbang.cn/Japp/DataQsdcxz/original/"+url;
		// else
		if(type=='api') furl= "http://www.dxsbang.cn/api/"+url;//新 后台
		else if(type=='pub') furl= "http://www.dxsbang.cn/pub/"+url;//原 后台
		else if(type=='res') furl= "http://www.dxsbang.cn/res/"+url;//公开资源
		else if(type=='src') furl= "http://www.dxsbang.cn/source/"+u.getStorage('school')+'/'+url;//私有资源
		return furl;
	}
	u.winUrl = function(type,url) {
		if(type=='app') return "http://app.dxsbang.cn/Japp/"+url;//win 后台
		else if(type=='data') return "http://app.dxsbang.cn/Japp/data"+url;//学习资料
		else return "http://app.dxsbang.cn/Japp/files/"+type+"/"+url;//files文件
	}
	u.contain = function(str,a){//是否包含其中字符(串)
		if(Array.isArray(a)){//a是数组
			var sym=false;
			for(var i in a){
				if(str.indexOf(a[i])!=-1){
					sym=true;
					break;
				}
			}
			return sym;
		}else
			return str.indexOf(a)!=-1;
	}
	u.contained = function(a,str){
		return u.contain(str,a);
	}
	u.containnum = function(str,a){//包含字符(串)个数
		var regex = new RegExp(a, 'g');
		var result = str.match(regex);
		return !result ? 0 : result.length;
	}

	u.htmlConvert = function(str) {//html上传文字转换
		str = str.replace(/&gt;>/g, '>');
		str = str.replace(/&lt;/g, '<');
		str = str.replace(/&nbsp;/g, ' ');
		str = str.replace(/&bmp;/g, '&');
		str = str.replace(/<div>/g, '\n');
		str = str.replace(/<\/?[^>]*>/g, '');
		return str;
	}
	u.emojiConvert = function (str){
		var tempStr='';
		var retStr='';
		var i=0;
		var s='';
		while(i<str.length){
			try{
				tempStr=str[i];//str.substr(i,1);
				s=encodeURI(tempStr);
				retStr+=tempStr;
				i++;
			}catch(e){
				tempStr=str.substr(i,2);
				s=encodeURI(tempStr);
				retStr+=s;
				i+=2;
			}
			// if(s.match(/%/g).length>=3){
			// 	retStr+=s;
			// }
		}
		return retStr;
		// return str.match(/(\ud83c[\udf00-\udfff])|(\ud83d[\udc00-\ude4f])|(\ud83d[\ude80-\udeff])/);
	}

	u.fname = function(path){//获取名字
		if(u.contain(path,'/'))
			return path.substring(path.lastIndexOf('/')+1,path.length);
		else
			return path.substring(path.lastIndexOf('\\')+1,path.length);
	}
	u.fextend = function(path) {//获取扩展名
		path = u.fname(path);
		if(path.indexOf('.')!=-1)
			return path.substring(path.lastIndexOf('.'),path.length);
		else
			return '';
	}
	u.ftype = function(path) {//获取文件类型
		var str = u.fextend(path).toLowerCase();
		for(var i=0;i<fileTypeArray.length;i++){
			if(str.indexOf(fileTypeArray[i])!=-1){
				return fileTypeArray[i];
			}
		}
		return 'file';
	}
	u.logout = function() {
		//注销推送
		// var push=api.require('push');
		// push.leaveGroup({
		// 	groupName : 'login'
		// }, function(ret, err) {
		// });
		// push.unbind({
		// 	userName: 'xyb'+u.getStorage('stuid'),
		// 	userId: ''+u.getStorage('stuid')
		// }, function(ret, err){
		// });
		u.setStorage('tkd','');
		api.closeToWin({
				name: 'root',
				animation:{
					type:'flip',
					subType:"from_right",
					duration:300
				}
		});
	}
	u.relogin = function(){
		// api.toast({msg:'您的登录信息已失效，请重新登录'});
		// alert('您的登录信息已失效，请重新登录');
		api.removeLaunchView({
		    animation: {
		        type: 'fade',
		        duration: 300
		    }
		});
		api.confirm({
		    title: '',
		    msg: '您的登录信息已失效，请重新登录',
		    buttons: ['确定', '退出']
		}, function(ret, err){
		    if( ret.buttonIndex==1 ){
		      u.logout();
		    }else{
					api.closeWidget({
						id : api.appId,
						animation : {
							type : 'flip',
							subType : 'from_bottom',
							duration : 500
						},
						silent : true
					});
				}
		});
	}
	u.checkInputType = function(str,type){
		// switch(type){
		// 	case 'mail':
		// 		return
		// 	default:
		// 		'';
		// }
	}
	// u.checkTime = function (){
	// 	var xhr = new XMLHttpRequest();
	// 	xhr.open("GET", u.myUrl('api','getStamp'), false); // 同步请求
	// 	xhr.send(null);
	// 	u.setStorage('timediff',xhr.responseText-new Date().getTime());
	// }
	u.checkTimeAsyc = function (fn) {
		var str=Math.random(1000000);
		var xhr = new XMLHttpRequest();
		xhr.open("GET", u.myUrl('api','getSafeStamp')+'?s='+str+'&m='+md5(api.loadSecureValue({sync: true,key: 'mk'})+str), false); // 同步请求
		xhr.send(null);
		u.setStorage('timediff',xhr.responseText-new Date().getTime());
		// var str=Math.random(1000000);
		// api.ajax({
		//     url: u.myUrl('api','getSafeStamp'),
		//     method: 'post',
		// 		data:{
		// 			values:{
		// 				s:str,
		// 				m:md5(api.loadSecureValue({sync: true,key: 'mk'})+str)
		// 			}
		// 		},
		// 		// timeout:2000,
		// 		dataType:'text'
		// },function(ret, err){
		//     if (ret) {
		// 			u.setStorage('timediff',ret-new Date().getTime());
		// 			fn(true);
		//     }else{
		// 			fn(false);
		// 		}
		// });
	}
	u.serverTime = function () {
		return new Date().getTime()+parseInt(u.getStorage('timediff'));
	}
	u.upFile = function (files,fn,time){
		if(time==null) time=200;
		api.showProgress({});
		api.ajax({
		    url: u.myUrl('api','upFile'),
		    method: 'post',
		    data: {
		        values: {
		            tkd: u.getStorage('tkd'),
								md : md5('1357'+u.getStorage('tkd')),
								stamp : u.serverTime()
		        },
						files:{
							file:files
						}
		    },
				timeout:time,
				dataType:'json'
		},fn);
	}
	u.myPage = function (value) {
		var str="";
		var vstr=u.getStorage('md5k');
		for(var i in value){
			if(i!='pass') vstr+=value[i];
		}
		value.md=md5(vstr);
		value.stamp=u.serverTime();
		value.str=md5(md5(value.tkd+'1234')+value.stamp);

		for(var j in value){
				str+="&"+j+"="+value[j];
		}
		return '?'+str.substring(1);
	}
	u.myAjax = function(url,value,type,fn,time){
		// api.showProgress({});
		// var value={};
		// console.log(url);
		if(type=='') type='json';
		var vstr=u.getStorage('md5k');
		for(var i in value){
			if(i!='pass') vstr+=value[i];//typeof(value[i])=='string'?value[i]:JSON.stringify(value[i]);
		}
		value.md=md5(vstr);
		value.stamp=u.serverTime();
		if(value.tkd==null&&value.pass==null){//未登录数据请求
			value.str=md5('1234'+value.stamp+"1234");
		}else if(value.pass!=null&&value.tkd==null){//登录
			value.pass=md5(value.pass+'1234');
			value.str=md5(md5(value.account+value.pass+'1234')+value.stamp);
		}else if(value.pass==null&&value.tkd!=null){//已登录数据请求
			// value.tkd=u.getStorage('tokenid');
			value.str=md5(md5(value.tkd+'1234')+value.stamp);
		}else{
			alert('请求格式错误');
		}
		api.ajax({
		    url: u.myUrl('api',url),
		    method: 'post',
		    data: {
		        values: value
		    },
				dataType:type,
				cache:false,
				timeout:time==null?20:time
		},fn);
	}
	u.recharge = function (enSym) {
		// var enSym=false;
		api.confirm({
			title : enSym ? 'Warm Tip' : '温馨提示',
			msg : enSym ? 'Insufficient balance, recharge?' : '您的余额已不足，是否充值？',
			buttons : [ enSym ? 'Confirm' : '确定', enSym ? 'Cancel' : '取消']
		}, function(ret, err) {
			var index = ret.buttonIndex;
			if (index == 1) {
				api.openWin({
					name : 'recharge',
					url : 'recharge.html',
				});
			}
		});
	}
	/*自定义结束*/

	u.trim = function(str) {
		if (String.prototype.trim) {
			return str == null ? "" : String.prototype.trim.call(str);
		} else {
			return str.replace(/(^\s*)|(\s*$)/g, "");
		}
	};
	u.trimAll = function(str) {
		return str.replace(/\s*/g, '');
	};
	u.isElement = function(obj) {
		return !!(obj && obj.nodeType == 1);
	};
	u.isArray = function(obj) {
		if (Array.isArray) {
			return Array.isArray(obj);
		} else {
			return obj instanceof Array;
		}
	};
	u.isEmptyObject = function(obj) {
		if (JSON.stringify(obj) === '{}') {
			return true;
		}
		return false;
	};
	u.addEvt = function(el, name, fn, useCapture) {
		if (!u.isElement(el)) {
			console.warn('$api.addEvt Function need el param, el param must be DOM Element');
			return;
		}
		useCapture = useCapture || false;
		if (el.addEventListener) {
			el.addEventListener(name, fn, useCapture);
		}
	};
	u.rmEvt = function(el, name, fn, useCapture) {
		if (!u.isElement(el)) {
			console.warn('$api.rmEvt Function need el param, el param must be DOM Element');
			return;
		}
		useCapture = useCapture || false;
		if (el.removeEventListener) {
			el.removeEventListener(name, fn, useCapture);
		}
	};
	u.one = function(el, name, fn, useCapture) {
		if (!u.isElement(el)) {
			console.warn('$api.one Function need el param, el param must be DOM Element');
			return;
		}
		useCapture = useCapture || false;
		var that = this;
		var cb = function() {
			fn && fn();
			that.rmEvt(el, name, cb, useCapture);
		};
		that.addEvt(el, name, cb, useCapture);
	};
	u.dom = function(el, selector) {
		if (arguments.length === 1 && typeof arguments[0] == 'string') {
			if (document.querySelector) {
				return document.querySelector(arguments[0]);
			}
		} else if (arguments.length === 2) {
			if (el.querySelector) {
				return el.querySelector(selector);
			}
		}
	};
	u.domAll = function(el, selector) {
		if (arguments.length === 1 && typeof arguments[0] == 'string') {
			if (document.querySelectorAll) {
				return document.querySelectorAll(arguments[0]);
			}
		} else if (arguments.length === 2) {
			if (el.querySelectorAll) {
				return el.querySelectorAll(selector);
			}
		}
	};
	u.byId = function(id) {
		return document.getElementById(id);
	};
	u.first = function(el, selector) {
		if (arguments.length === 1) {
			if (!u.isElement(el)) {
				console.warn('$api.first Function need el param, el param must be DOM Element');
				return;
			}
			return el.children[0];
		}
		if (arguments.length === 2) {
			return this.dom(el, selector + ':first-child');
		}
	};
	u.last = function(el, selector) {
		if (arguments.length === 1) {
			if (!u.isElement(el)) {
				console.warn('$api.last Function need el param, el param must be DOM Element');
				return;
			}
			var children = el.children;
			return children[children.length - 1];
		}
		if (arguments.length === 2) {
			return this.dom(el, selector + ':last-child');
		}
	};
	u.eq = function(el, index) {
		return this.dom(el, ':nth-child(' + index + ')');
	};
	u.not = function(el, selector) {
		return this.domAll(el, ':not(' + selector + ')');
	};
	u.prev = function(el) {
		if (!u.isElement(el)) {
			console.warn('$api.prev Function need el param, el param must be DOM Element');
			return;
		}
		var node = el.previousSibling;
		if (node.nodeType && node.nodeType === 3) {
			node = node.previousSibling;
			return node;
		}
	};
	u.next = function(el) {
		if (!u.isElement(el)) {
			console.warn('$api.next Function need el param, el param must be DOM Element');
			return;
		}
		var node = el.nextSibling;
		if (node.nodeType && node.nodeType === 3) {
			node = node.nextSibling;
			return node;
		}
	};
	u.closest = function(el, selector) {
		if (!u.isElement(el)) {
			console.warn('$api.closest Function need el param, el param must be DOM Element');
			return;
		}
		var doms, targetDom;
		var isSame = function(doms, el) {
			var i = 0, len = doms.length;
			for (i; i < len; i++) {
				if (doms[i].isEqualNode(el)) {
					return doms[i];
				}
			}
			return false;
		};
		var traversal = function(el, selector) {
			doms = u.domAll(el.parentNode, selector);
			targetDom = isSame(doms, el);
			while (!targetDom) {
				el = el.parentNode;
				if (el != null && el.nodeType == el.DOCUMENT_NODE) {
					return false;
				}
				traversal(el, selector);
			}

			return targetDom;
		};

		return traversal(el, selector);
	};
	u.contains = function(parent, el) {
		var mark = false;
		if (el === parent) {
			mark = true;
			return mark;
		} else {
			do {
				el = el.parentNode;
				if (el === parent) {
					mark = true;
					return mark;
				}
			} while(el === document.body || el === document.documentElement);

			return mark;
		}

	};
	u.remove = function(el) {
		if (el && el.parentNode) {
			el.parentNode.removeChild(el);
		}
	};
	u.attr = function(el, name, value) {
		if (!u.isElement(el)) {
			console.warn('$api.attr Function need el param, el param must be DOM Element');
			return;
		}
		if (arguments.length == 2) {
			return el.getAttribute(name);
		} else if (arguments.length == 3) {
			el.setAttribute(name, value);
			return el;
		}
	};
	u.removeAttr = function(el, name) {
		if (!u.isElement(el)) {
			console.warn('$api.removeAttr Function need el param, el param must be DOM Element');
			return;
		}
		if (arguments.length === 2) {
			el.removeAttribute(name);
		}
	};
	u.hasCls = function(el, cls) {
		if (!u.isElement(el)) {
			console.warn('$api.hasCls Function need el param, el param must be DOM Element');
			return;
		}
		if (el.className.indexOf(cls) > -1) {
			return true;
		} else {
			return false;
		}
	};
	u.addCls = function(el, cls) {
		if (!u.isElement(el)) {
			console.warn('$api.addCls Function need el param, el param must be DOM Element');
			return;
		}
		if ('classList' in el) {
			el.classList.add(cls);
		} else {
			var preCls = el.className;
			var newCls = preCls + ' ' + cls;
			el.className = newCls;
		}
		return el;
	};
	u.removeCls = function(el, cls) {
		if (!u.isElement(el)) {
			console.warn('$api.removeCls Function need el param, el param must be DOM Element');
			return;
		}
		if ('classList' in el) {
			el.classList.remove(cls);
		} else {
			var preCls = el.className;
			var newCls = preCls.replace(cls, '');
			el.className = newCls;
		}
		return el;
	};
	u.toggleCls = function(el, cls) {
		if (!u.isElement(el)) {
			console.warn('$api.toggleCls Function need el param, el param must be DOM Element');
			return;
		}
		if ('classList' in el) {
			el.classList.toggle(cls);
		} else {
			if (u.hasCls(el, cls)) {
				u.removeCls(el, cls);
			} else {
				u.addCls(el, cls);
			}
		}
		return el;
	};
	u.val = function(el, val) {
		if (!u.isElement(el)) {
			console.warn('$api.val Function need el param, el param must be DOM Element');
			return;
		}
		if (arguments.length === 1) {
			switch(el.tagName) {
				case 'SELECT':
					var value = el.options[el.selectedIndex].value;
					return value;
					break;
				case 'INPUT':
					return el.value;
					break;
				case 'TEXTAREA':
					return el.value;
					break;
			}
		}
		if (arguments.length === 2) {
			switch(el.tagName) {
				case 'SELECT':
					el.options[el.selectedIndex].value = val;
					return el;
					break;
				case 'INPUT':
					el.value = val;
					return el;
					break;
				case 'TEXTAREA':
					el.value = val;
					return el;
					break;
			}
		}

	};
	u.prepend = function(el, html) {
		if (!u.isElement(el)) {
			console.warn('$api.prepend Function need el param, el param must be DOM Element');
			return;
		}
		el.insertAdjacentHTML('afterbegin', html);
		return el;
	};
	u.append = function(el, html) {
		if (!u.isElement(el)) {
			console.warn('$api.append Function need el param, el param must be DOM Element');
			return;
		}
		el.insertAdjacentHTML('beforeend', html);
		return el;
	};
	u.before = function(el, html) {
		if (!u.isElement(el)) {
			console.warn('$api.before Function need el param, el param must be DOM Element');
			return;
		}
		el.insertAdjacentHTML('beforebegin', html);
		return el;
	};
	u.after = function(el, html) {
		if (!u.isElement(el)) {
			console.warn('$api.after Function need el param, el param must be DOM Element');
			return;
		}
		el.insertAdjacentHTML('afterend', html);
		return el;
	};
	u.html = function(el, html) {
		if (!u.isElement(el)) {
			console.warn('$api.html Function need el param, el param must be DOM Element');
			return;
		}
		if (arguments.length === 1) {
			return el.innerHTML;
		} else if (arguments.length === 2) {
			el.innerHTML = html;
			return el;
		}
	};
	u.text = function(el, txt) {
		if (!u.isElement(el)) {
			console.warn('$api.text Function need el param, el param must be DOM Element');
			return;
		}
		if (arguments.length === 1) {
			return el.textContent;
		} else if (arguments.length === 2) {
			el.textContent = txt;
			return el;
		}
	};
	u.offset = function(el) {
		if (!u.isElement(el)) {
			console.warn('$api.offset Function need el param, el param must be DOM Element');
			return;
		}
		var sl = Math.max(document.documentElement.scrollLeft, document.body.scrollLeft);
		var st = Math.max(document.documentElement.scrollTop, document.body.scrollTop);

		var rect = el.getBoundingClientRect();
		return {
			l : rect.left + sl,
			t : rect.top + st,
			w : el.offsetWidth,
			h : el.offsetHeight
		};
	};
	u.css = function(el, css) {
		if (!u.isElement(el)) {
			console.warn('$api.css Function need el param, el param must be DOM Element');
			return;
		}
		if ( typeof css == 'string' && css.indexOf(':') > 0) {
			el.style && (el.style.cssText += ';' + css);
		}
	};
	u.cssVal = function(el, prop) {
		if (!u.isElement(el)) {
			console.warn('$api.cssVal Function need el param, el param must be DOM Element');
			return;
		}
		if (arguments.length === 2) {
			var computedStyle = window.getComputedStyle(el, null);
			return computedStyle.getPropertyValue(prop);
		}
	};
	u.jsonToStr = function(json) {
		if ( typeof json === 'object') {
			return JSON && JSON.stringify(json);
		}
	};
	u.strToJson = function(str) {
		if ( typeof str === 'string') {
			return JSON && JSON.parse(str);
		}
	};
	u.setStorage = function (key, value) {
		api.setPrefs({
		    key: key,
		    value: value
		});
	}
	u.getStorage = function (key) {
		var value = api.getPrefs({
				sync: true,
				key: key
			});
		return value==null?"":value;
	}
	u.rmStorage = function (key) {
		api.removePrefs({
    	key: key
		});
	}
	u.setPrefs = function(key, value) {
		if (arguments.length === 2) {
			var v = value;
			if ( typeof v == 'object') {
				v = JSON.stringify(v);
				v = 'obj-' + v;
			} else {
				v = 'str-' + v;
			}
			var ls = uzStorage();
			if (ls) {
				ls.setItem(key, v);
			}
		}
	};
	u.getPrefs = function(key) {
		var ls = uzStorage();
		if (ls) {
			var v = ls.getItem(key);
			if (!v) {
				return;
			}
			if (v.indexOf('obj-') === 0) {
				v = v.slice(4);
				return JSON.parse(v);
			} else if (v.indexOf('str-') === 0) {
				return v.slice(4);
			}
		}
	};
	u.removePrefs = function(key) {
		var ls = uzStorage();
		if (ls && key) {
			ls.removeItem(key);
		}
	};
	u.clearStorage = function() {
		var ls = uzStorage();
		if (ls) {
			ls.clear();
		}
	};

	/*by king*/
	u.fixIos7Bar = function(el) {
		if (!u.isElement(el)) {
			console.warn('$api.fixIos7Bar Function need el param, el param must be DOM Element');
			return;
		}
		var strDM = api.systemType;
		if (strDM == 'ios') {
			var strSV = api.systemVersion;
			var numSV = parseInt(strSV, 10);
			var fullScreen = api.fullScreen;
			var iOS7StatusBarAppearance = api.iOS7StatusBarAppearance;
			if (numSV >= 7 && !fullScreen && iOS7StatusBarAppearance) {
				el.style.paddingTop = api.safeArea.top + 'px';
				el.style.height = api.safeArea.top + 45 + 'px';
			}
		}
	};
	u.fixStatusBar = function(el) {
		if (!u.isElement(el)) {
			console.warn('$api.fixStatusBar Function need el param, el param must be DOM Element');
			return;
		}
		var sysType = api.systemType;
		if (sysType == 'ios') {
			u.fixIos7Bar(el);
		} else if (sysType == 'android') {
			var ver = api.systemVersion;
			ver = parseFloat(ver);
			if (ver >= 4.4) {
				el.style.paddingTop = api.safeArea.top + 'px';
				el.style.height = api.safeArea.top + 45 + 'px';
			}
		}
	};
	u.perPix = function(per){
		// var sysType = api.systemType;
		// if (sysType == 'ios') {
		// 	var strDM = api.systemType;
		// 	if (strDM == 'ios') {
		// 		var strSV = api.systemVersion;
		// 		var numSV = parseInt(strSV, 10);
		// 		var fullScreen = api.fullScreen;
		// 		var iOS7StatusBarAppearance = api.iOS7StatusBarAppearance;
		// 		if (numSV >= 7 && !fullScreen && iOS7StatusBarAppearance) {
		// 			return (api.winHeight-20)/16;
		// 		}
		// 	}
		// } else if (sysType == 'android') {
		// 	var ver = api.systemVersion;
		// 	ver = parseFloat(ver);
		// 	if (ver >= 4.4) {
		// 		return (api.winHeight-25)/16;
		// 	}
		// }
		return per*api.winHeight/16;
	}
	u.toast = function(title, text, time) {
		var opts = {};
		var show = function(opts, time) {
			api.showProgress(opts);
			setTimeout(function() {
				api.hideProgress();
			}, time);
		};
		if (arguments.length === 1) {
			var time = time || 500;
			if ( typeof title === 'number') {
				time = title;
			} else {
				opts.title = title + '';
			}
			show(opts, time);
		} else if (arguments.length === 2) {
			var time = time || 500;
			var text = text;
			if ( typeof text === "number") {
				var tmp = text;
				time = tmp;
				text = null;
			}
			if (title) {
				opts.title = title;
			}
			if (text) {
				opts.text = text;
			}
			show(opts, time);
		}
		if (title) {
			opts.title = title;
		}
		if (text) {
			opts.text = text;
		}
		time = time || 500;
		show(opts, time);
	};
	u.post = function(/*url,data,fnSuc,dataType*/) {
		var argsToJson = parseArguments.apply(null, arguments);
		var json = {};
		var fnSuc = argsToJson.fnSuc;
		argsToJson.url && (json.url = argsToJson.url);
		argsToJson.data && (json.data = argsToJson.data);
		if (argsToJson.dataType) {
			var type = argsToJson.dataType.toLowerCase();
			if (type == 'text' || type == 'json') {
				json.dataType = type;
			}
		} else {
			json.dataType = 'json';
		}
		json.method = 'post';

		api.ajax(json, function(ret, err) {
			if (ret) {
				fnSuc && fnSuc(ret);
			}
		});
	};
	u.get = function(/*url,fnSuc,dataType*/) {
		var argsToJson = parseArguments.apply(null, arguments);
		var json = {};
		var fnSuc = argsToJson.fnSuc;
		argsToJson.url && (json.url = argsToJson.url);
		//argsToJson.data && (json.data = argsToJson.data);
		if (argsToJson.dataType) {
			var type = argsToJson.dataType.toLowerCase();
			if (type == 'text' || type == 'json') {
				json.dataType = type;
			}
		} else {
			json.dataType = 'text';
		}
		json.method = 'get';
		api.ajax(json, function(ret, err) {
			if (ret) {
				fnSuc && fnSuc(ret);
			}
		});
	};

	window.$S = u;

})(window);
