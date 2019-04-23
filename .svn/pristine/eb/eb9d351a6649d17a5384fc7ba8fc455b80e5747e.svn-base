/**
 * jQuery甯︾澶存彁绀烘鎻掍欢
 * email: tianshaojie@msn.com
 * date: 2013-01-15
 * version: 1.0.0
 */
(function($) {
	var max = Math.max,
		min = Math.min;
	$.pt = $.pureToolTips = function(options) {
		var opts = $.extend({
			target 		: null,		//鐩爣鍏冪礌锛屼笉鑳戒负绌�
			position 	: 't',		//鎻愮ず妗嗙浉瀵圭洰鏍囧厓绱犱綅缃� t=top,b=bottom,r=right,l=left
			align		: 'c',		//鎻愮ず妗嗕笌鐩爣鍏冪礌鐨勫榻愭柟寮忥紝鑷姩璋冭妭绠ご鏄剧ず浣嶇疆锛屾寚鍚戠洰鏍囧厓绱犱腑闂翠綅缃紝c=center, t=top, b=bottom, l=left, r=right [postion=t|b鏃讹紝align=l|r鏈夋晥][position=t|b鏃讹紝align=t|d鏈夋晥]
			arrow		: true,		//鏄惁鏄剧ず绠ご
			content 	: '',		//鍐呭
			width 		: 200,		//瀹藉害
			height 		: 'auto',	//楂樺害
			autoClose 	: true,		//鏄惁鑷姩鍏抽棴
			time 		: 2000,		//鑷姩鍏抽棴寤舵椂鏃堕暱
			leaveClose 	: false,	//鎻愮ず妗嗗け鍘荤劍鐐瑰悗鍏抽棴
			close 		: null		//鍏抽棴鍥炶皟鍑芥暟
		}, options || {}),

		$ao, $ai, w, h,
		$pt = $('.pt'),
		$target = $(opts.target),
		top = $target.offset().top,
		left = $target.offset().left,
		width = $target.outerWidth(),
		height = $target.outerHeight(),

		position = opts.position,
		align = opts.align,
		arrow = opts.arrow,

		constant = {b:'pt-up', t:'pt-down', r:'pt-left', l:'pt-right'}, //鐩稿浣嶇疆姝ｅソ鍜岀澶存柟鍚戠浉鍙�
		arrowClass = constant[position] || constant.t;

		//鍒濆鍖栧厓绱狅紝浜嬩欢
		function init() {
			if(!opts.target) {
				return;
			}
			if(!$pt.length) {
				$pt = $('<div class="pt pt-down"><div class="cont"></div><b class="out"></b><b class="in"></b></div>').appendTo(document.body);
			}
			$pt.removeClass().addClass('pt ' + (arrow ? arrowClass : '')).find('.cont').html(opts.content).css({width:opts.width, height:opts.height});
			$ao = $pt.find('.out').toggle(arrow);
			$ai =  $pt.find('.in').toggle(arrow);
			w = $pt.outerWidth();
			h = $pt.outerHeight();
			arrow && autoAdjust();			//璁剧疆绠ご鑷姩灞呬腑
			$pt.css(setPos()).show();		//璁剧疆鏄剧ず妗嗕綅缃拰鑷姩闅愯棌浜嬩欢
			opts.leaveClose && leaveClose();//绂诲紑鍏抽棴
			opts.autoClose && !opts.leaveClose && autoClose(opts.timeout);	//榛樿鑷姩鍏抽棴锛屼紭鍏堢寮€鍏抽棴
			return $pt;
		}
		//璁＄畻鎻愮ず妗嗗簲璇ュ嚭鐜板湪鐩爣鍏冪礌浣嶇疆
		function setPos() {
			var btw = arrow ? parseInt($ao.css('border-top-width'), 10) : 3,
				brw = arrow ? parseInt($ao.css('border-right-width'), 10) : 3,
				result = {};
			switch(align) {
				case 'c': break;
				case 't': result.top = top; break;
				case 'b': result.top = top + height - h; break;
				case 'l': result.left = left; break;
				case 'r': result.left = left + width - w; break;
			}
			switch(position) {
				case 't': result.top = top - h - brw; break;
				case 'b': result.top = top + height + brw; break;
				case 'l': result.left = left - w - btw; break;
				case 'r': result.left = left + width + btw; break;
			}
			result.top || (result.top = top + height/2 - h/2);
			result.left || (result.left = left + width/2 - w/2);
			return result;
		}

		//璁剧疆绠ご鑷姩灞呬腑
		function autoAdjust() {
			var aop, aip, bw, auto='auto';
			switch(position) {
				case't':
					bw = parseInt($ao.css('border-top-width'), 10);
					aop = {bottom:-bw, left:w/2-bw, top:auto, right:auto};
					alignLR();
					aip = {top:auto, left:aop.left+1, bottom:aop.bottom+1, right:auto};
					break;
				case'b':
					bw = parseInt($ao.css('border-bottom-width'), 10);
					aop = {top:-bw, left:w/2 - bw, right:auto, bottom:auto};
					alignLR();
					aip = {top:aop.top+1, left:aop.left+1, bottom:auto, right:auto};
					break;
				case'l':
					bw = parseInt($ao.css('border-left-width'), 10);
					aop = {top:h/2 - bw, right:-bw, left:auto, bottom:auto};
					alignTB();
					aip = {top:aop.top+1, right:aop.right+1, left:auto, bottom:auto};
					break;
				case'r':
					bw = parseInt($ao.css('border-right-width'), 10);
					aop = {top:h/2 - bw, left:-bw, right:auto, bottom:auto};
					alignTB();
					aip = {top:aop.top+1, left:aop.left+1, right:auto, bottom:auto};
					break;
			}
			//涓婁笅渚э紝宸﹀彸瀵归綈
			function alignLR() {
				if(align === 'l' && width/2 > bw && width/2 < w-bw) {
					aop.left = width/2-bw/2;
				} else if(align === 'r' && width/2 > bw && width/2 < w-bw) {
					aop.left = w-width/2-bw/2;
				}
			}
			//宸﹀彸渚э紝涓婁笅瀵归綈
			function alignTB() {
				if(align === 't' && height/2 > bw && height/2 < h-bw) {
					aop.top = height/2 - bw;
				} else if(align === 'b' && height/2 > bw && height/2 < h-bw) {
					aop.top = h - height/2 - bw;
				}
			}
			$ao.css(aop);
			$ai.css(aip);
		}
		//璁剧疆鎻愮ず妗嗚嚜鍔ㄥ叧闂�
		function autoClose() {
			window.ptt && clearTimeout(ptt);
			window.pta && clearTimeout(pta);
			window.pta = setTimeout(function() {
				$pt.hide();
				$.isFunction(opts.close) && opts.close();
			}, opts.time);
		}
		//璁剧疆鎻愮ず妗嗗け鍘荤劍鐐瑰叧闂�
		function leaveClose() {
			//鍏堣В缁戝啀缁戝畾锛屼笉鐒朵細褰㈡垚浜嬩欢閾�
			$pt.unbind('mouseleave').mouseleave(function(e) {
				$pt.hide();
				$.isFunction(opts.close) && opts.close();
			}).unbind('mouseenter').mouseenter(function() {
				window.ptt && clearTimeout(ptt);
			});
		}
		return init();
	};

	//鎵╁睍鍒板寘瑁呴泦涓�
	$.fn.pt = $.fn.pureToolTips = function(options) {
		var opts = $.extend({
			leaveClose:true
		}, options || {});
		return this.each(function() {
			$(this).mouseenter(function() {
				window.ptt && clearTimeout(ptt);
				window.pta && clearTimeout(pta);
				opts.target = this;
				$.pt(opts);
			}).mouseleave(function() {
				window.ptt = setTimeout(function() {
					$('.pt').hide();
					$.isFunction(opts.close) && opts.close();
				}, 500);
			});
		});
	};
})(jQuery);