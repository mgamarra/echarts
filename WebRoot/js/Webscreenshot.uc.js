// ==UserScript==
// @name           WebScreenShot.uc.xul
// @namespace      http://d.hatena.ne.jp/Griever
// @include        main
// @version        0.0.2
// ==/UserScript==


document.getElementById('contentAreaContextMenu').addEventListener('popupshowing', function func(event) {
	if (event.target != event.currentTarget) return;
	document.getElementById('WebScreenShotOnImage').hidden = !gContextMenu.onImage && !gContextMenu.onCanvas;
}, false);


window.WebScreenShot = function(){
	this.init();
};


window.WebScreenShot.prototype = {
	init: function() {
		this.browser = gBrowser.mCurrentBrowser;
		this.statusField = document.getElementById('statusbar-display');
	},

	handleEvent: function(event) {
		switch(event.type) {
			case 'mousedown':
				this.browser.removeEventListener('mousedown', this, true);
				this.x = event.screenX;
				this.y = event.screenY;
				this.innerTop = content.mozInnerScreenY;
				this.innerLeft = content.mozInnerScreenX;
				this.innerBottom = this.innerTop + content.innerHeight;
				this.innerRight = this.innerLeft + content.innerWidth;
				this.box.openPopupAtScreen(this.x, this.y);
				this.browser.addEventListener('mousemove', this, true);
				this.browser.addEventListener('mouseup', this, true);
				break;

			case 'mousemove':
				var moveX = event.screenX;
				var moveY = event.screenY;
				
				if (moveX <= this.innerLeft) moveX = this.innerLeft;
				else if (moveX >= this.innerRight) moveX = this.innerRight;
				if (moveY <= this.innerTop) moveY = this.innerTop;
				else if (moveY >= this.innerBottom) moveY = this.innerBottom;
				if (this.x > moveX || this.y > moveY)
					this.box.moveTo(this.x > moveX? moveX : this.x, this.y > moveY? moveY : this.y);
				this.box.width  = Math.abs(moveX - this.x);
				this.box.height = Math.abs(moveY - this.y);
				this.statusField.label = this.box.width + 'x' + this.box.height;
				break;

			case 'mouseup':
				this.browser.removeEventListener('mousemove', this, true);
				this.browser.removeEventListener('mouseup', this, true);
				var borderWidth = Math.round( (this.box.boxObject.width - this.box.clientWidth) / 2);
				var x = this.box.boxObject.screenX - content.mozInnerScreenX + content.scrollX + borderWidth;
				var y = this.box.boxObject.screenY - content.mozInnerScreenY + content.scrollY + borderWidth;
				var w = this.box.boxObject.width  - borderWidth;
				var h = this.box.boxObject.height - borderWidth;
				this.capture(content, x, y, w, h);
				this.box.removeEventListener('popuphiding', this, false);
				this.box.parentNode.removeChild(this.box);
				this.resetCursor();
				break;

			case 'mouseover':
				this.target = event.target;
				this.highlight();
				this.relatedTarget = this.target;
				this.statusField.label = this.target.offsetWidth + 'x' + this.target.offsetHeight;
				break;

			case 'click':
				this.relatedTarget = this.target;
				this.lowlight();
				var win = this.target.ownerDocument.defaultView;
				var rect = this.target.getBoundingClientRect();
				this.capture(win, rect.left + win.scrollX, rect.top + win.scrollY, rect.width, rect.height, false);
				
				this.browser.removeEventListener('mouseover', this, true);
				this.browser.removeEventListener('click', this, true);
				break;

			case 'popuphiding':
				break;
		}
		event.preventDefault();
		event.stopPropagation();
	},

	highlight: function() {
		if (this.relatedTarget) 
			this.lowlight();
		this.defStyle = this.target.getAttribute('style');
		this.target.style.setProperty('outline', '2px solid red', 'important');
		this.target.style.setProperty('outline-offset', '1px', 'important');
	},

	lowlight: function() {
		if (this.defStyle == null)
			this.relatedTarget.removeAttribute('style');
		else 
			this.relatedTarget.setAttribute('style', this.defStyle || '');
	},

	getWindowList: function() {
		this.windowList = (function(win) {
			var array = win.document.body instanceof HTMLFrameSetElement? []: [win];
			for (var i = 0, l = win.frames.length; i < l; i++) {
				array.push.apply(array, arguments.callee(win.frames[i]));
			}
			return array;
		})(window.content);
	},

	setCursor: function() {
		this.rootList = this.windowList.map(function(win) win.document.documentElement);
		this.rootList.forEach(function(root) {
			root.style.setProperty('cursor', 'crosshair', 'important');
		});
	},
	
	resetCursor: function() {
		this.rootList.forEach(function(root){
			if (root)
				root.style.setProperty('cursor', '', '');
		});
	},

	capture: function(win, x, y, w, h) {
		var tab = gBrowser.addTab();
		var browser = gBrowser.getBrowserForTab(tab);
		browser.addEventListener('load', function(event) {
			browser.removeEventListener('load', arguments.callee, true);
			var doc = browser.contentDocument;
			var canvas = doc.body.appendChild(doc.createElement('canvas'));
			canvas.style.display = 'inline';
			canvas.width = w;
			canvas.height = h;
			var ctx = canvas.getContext("2d");
			ctx.drawWindow(win, x, y, x + w, y + h, "rgb(255,255,255)");
			gBrowser.selectedTab = tab;
		}, true);
	},


	clickCapture: function() {
		this.browser.addEventListener('mouseover', this, true);
		this.browser.addEventListener('click', this, true);
	},


	pageCapture: function() {
		this.capture(content, content.scrollX, content.scrollY, content.innerWidth, content.innerHeight);
	},

	fullCapture: function() {
		this.capture(content, 0, 0, content.innerWidth + content.scrollMaxX, content.innerHeight + content.scrollMaxY);
	},
};



var WebShot= {
	capture : function(win, x, y, width, height, isCopy){
		var mainWindow = document.getElementById('main-window');
		var scrollbox = document.createElement('scrollbox');
		scrollbox.width = '1';
		scrollbox.height = '1';
		mainWindow.appendChild(scrollbox);
		var canvas = document.createElementNS('http://www.w3.org/1999/xhtml', 'canvas');
		canvas.style.display = 'inline';
		canvas.width = width;
		canvas.height = height;
		scrollbox.appendChild(canvas);

		var ctx = canvas.getContext("2d");
		ctx.clearRect(0, 0, width, height);
		ctx.save();
		ctx.scale(1.0, 1.0);
		ctx.drawWindow(win, x, y, width, height, "rgb(255,255,255)");
		ctx.restore();

		var base64 = canvas.toDataURL("image/png");
		if (isCopy){
			Cc['@mozilla.org/widget/clipboardhelper;1'].getService(Ci.nsIClipboardHelper).copyString(base64);
			alert('Copy is Base64 Code.');
		}else{
			gBrowser.addTab(base64);
		}
		mainWindow.removeChild(scrollbox);
	},
	onImage : function(image){
		var doc = image.ownerDocument;

		var canvas = doc.createElement('canvas');
		canvas.width = image.width;
		canvas.height = image.height;
		doc.body.appendChild(canvas);

		var ctx = canvas.getContext('2d');
		ctx.drawImage(image, 0, 0);
		var base64 = canvas.toDataURL('image/png');
		doc.body.removeChild(canvas);
		Cc['@mozilla.org/widget/clipboardhelper;1'].getService(Ci.nsIClipboardHelper).copyString(base64);
		alert('Copy is Base64 Code.');
	},
	handleEvent : function(event){
		if (event.target != event.currentTarget) return;
		document.getElementById('WebShotOnImage').hidden = !gContextMenu.onImage;
	},
	init : function(){
		document.getElementById('contentAreaContextMenu').addEventListener('popupshowing', this, false);
	},
};

var WebShotByClipping = {
	capture : WebShot.capture,
	handleEvent : function(event){
		if (event.button != 0) return false;
		event.preventDefault();
		event.stopPropagation();
		switch(event.type){
			case 'mousedown':
				this.downX = event.pageX;
				this.downY = event.pageY;
				this.bs.left = this.downX + 'px';
				this.bs.top  = this.downY + 'px';
				this.body.appendChild(this.box);
				this.flag = true;
				break;
			case 'mousemove':
				if (!this.flag) return;
				this.moveX = event.pageX;
				this.moveY = event.pageY;
				if (this.downX > this.moveX) this.bs.left = this.moveX + 'px';
				if (this.downY > this.moveY) this.bs.top  = this.moveY + 'px';
				this.bs.width  = Math.abs(this.moveX - this.downX) + 'px';
				this.bs.height = Math.abs(this.moveY - this.downY) + 'px';
				break;
			case 'mouseup':
				this.uninit();
				break;
		}
	},
	init : function(){
		this.win = document.commandDispatcher.focusedWindow;
		if (this.win == window) this.win = content;
		this.doc = this.win.document;
		this.body = this.doc.body;
		if (!this.body instanceof HTMLBodyElement){
			alert("Can not capture.");
			return false;
		}
		this.flag = null;
		this.box = this.doc.createElement('div');
		this.bs = this.box.style;
		this.bs.border = '#6F2691 dashed 2px';
		this.bs.position = 'absolute';
		this.bs.zIndex = '2147483647';
		this.defaultCursor = getComputedStyle(this.body, '').cursor;
		this.body.style.cursor = 'crosshair';
		this.doc.addEventListener('mousedown', this, true);
		this.doc.addEventListener('mousemove', this ,true);
		this.doc.addEventListener('mouseup', this ,true);
		this.doc.addEventListener('click', this, true);
	},
	uninit : function(){
		var pos = [this.win, parseInt(this.bs.left), parseInt(this.bs.top), parseInt(this.bs.width), parseInt(this.bs.height)];
		this.doc.removeEventListener('mousedown', this, true);
		this.doc.removeEventListener('mousemove', this, true);
		this.doc.removeEventListener('mouseup', this, true);
		this.doc.removeEventListener('click', this, true);
		this.body.style.cursor = this.defaultCursor;
		this.body.removeChild(this.box);
		this.capture.apply(this, pos);
	},
}

WebShot.init();

//Statusbar

(function(){
    var WebScreenShotPopup =document.createElement("menupopup");
    WebScreenShotPopup.setAttribute("id","WebScreenShotPopupid");

    var label1=document.createElement("menuitem");
    label1.setAttribute("label","\u622A\u53D6\u6574\u4E2A\u7F51\u9875");
    label1.setAttribute("oncommand","new WebScreenShot().fullCapture()");

    var label2=document.createElement("menuitem");
    label2.setAttribute("label","\u622A\u53D6\u53EF\u89C1\u533A\u57DF");
    label2.setAttribute("oncommand","new WebScreenShot().pageCapture();");

    var label3=document.createElement("menuitem");
    label3.setAttribute("label","\u622A\u53D6\u9009\u62E9\u533A\u57DF");
    label3.setAttribute("oncommand","WebShotByClipping.init()");

    var label4=document.createElement("menuitem");
    label4.setAttribute("label","\u622A\u53D6\u70B9\u9009\u5143\u7D20");
    label4.setAttribute("oncommand","new WebScreenShot().clickCapture();");


    WebScreenShotPopup.appendChild(label1);
    WebScreenShotPopup.appendChild(label2);
    WebScreenShotPopup.appendChild(label3);
    WebScreenShotPopup.appendChild(label4);

    var statusbarW = document.getElementById("nav-bar");
    var WebScreenShot = document.createElement("statusbarpanel");
    WebScreenShot.setAttribute("id","WebScreenShot");
    WebScreenShot.setAttribute("class","statusbarpanel-menu-iconic");
    WebScreenShot.setAttribute("src","data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAC/0lEQVQ4jX3Tb0wMcBzH8Z8nPHBYiyfGE/9S14WG0L9JOplJEaOwy58IpxDlb61RRnby36pNWpu7uCRdTqK7Q7fr5K7TH6tEQojDKpLeHnhgRj6PP6/t++D7EWKAYAgdhSVyPtUr1mNZspGqhfMpn+c6UP83rJjrRlXYRaqXd1C/E56lQ9MhPjqSwKpoxyA/jFoq+TcuD4jtN4Q4HdZg7M1ynK3xdLXlkFn/kNg2Jyk2A9/syWCIqEQ9bsSfuNh7HxWBqG/5ctSxDlXLIvKe+FFSVUhwWQuye6+YeElPly0FHPvgdmjub3xtQiSl06DSn0ydDzL1BMZnC66UBuGwF+CemsuY3afJzd4DDYegdj+Yt3ynSCYTGP1cKPN+gdoTNAt4ow3gwqWJlOTPos+uhPfZvLMf54M5BZqOQGMa2JPBuhN0wQmCppiNaIJg7yrIOQO7FoNpMdQpoGEDvDgGby7AyyxoPgp1qWBLAksCGFZnCToPFPYXREPUapgi5YvvaPruhoB1GdSshHoltJ6E5mPQeBgcB6FmN1ji+VwScV7wWWXptazFmTiT98vH0pklpd84D+4vBHM4WCKgdjs8zYD6VLDv/XV+9XYeZcoyBB+z7tMez3eTH73FHlA2DcrnQGUQGEPAFAomOZhXweNEeJwE1gS+3lOgU7qGi3ZTnI7uk9CwFCpmQ5EH3RpPnOqp9Ot9oSIQ7gTC7dmg9wdjFNTs4MnZWS05MWKYeHhClkZPHnQooS6cD1e8ftyIk6jKk0f5d1/1NKKbDqXT4aY3FEmh1IsOjRztVpdoIYQQ6pghbq131vTw4zK83canB3JnW6GPV69+xlS0kxvRuoPWHYqlUDaF13kelCS4nvrjC7XbJFtb9VHQlQvd6dC+4Ru1YWAOhgeBYAqgS+eDTTW+T7t5eJoQYtBfOyiMHRxtzJjc3KJX8M6WyCd7HJ2mMJ5f86VaNann5o6RN67GSgL+u8T8KDFcoxgcpt0kSbmudD1XFOeSrlk/dI06ZojbQOYnJsxKRFpxiNkAAAAASUVORK5CYII=");
    WebScreenShot.setAttribute("tooltiptext","WebScreenShot");

    WebScreenShot.appendChild(WebScreenShotPopup);

    statusbarW.appendChild(WebScreenShot);

    })();



