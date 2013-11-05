/*
 * Client-side JavaScript library to access Evrythng API
 * https://github.com/evrythng/evrythng-tools
 *
 * Copyright [2013] [EVRYTHNG Ltd. London / Zurich]
 *
 * Released under the http://www.apache.org/licenses/LICENSE-2.0
 * https://github.com/evrythng/evrythng-tools/blob/master/LICENSE.txt
 *
 */

Evrythng = function(options) {
	this.options = {};
	if (typeof options === 'object') {
		for (var i in options) {
			this.options[i] = options[i];
		}
	}
};


/*
	JSONP wrapper
*/
Evrythng.prototype.jsonp = function(url, callback) {
	if (typeof this.options.jQuery === 'function') {
		var promise = this.options.jQuery.getJSON(url);
		return (typeof callback === 'function') ? promise.then(callback) : promise;
	}
	else {
		return load.jsonp(url, callback);
	}
};


/*
	Facebook
*/
Evrythng.prototype.fbInit = function(callback) {
	var self = this;
	if (typeof callback === 'function') {
		this.options.loginCallback = callback;
	}
	window.fbAsyncInit = function() {
		self.fbAsyncInit.call(self);
	};
	if (typeof this.options.loadingCallback === 'function') this.options.loadingCallback.call(this, true);
	load.js('//connect.facebook.net/en_US/all.js', function() {
		if (typeof FB != 'object') {
			if (typeof self.options.loadingCallback === 'function') self.options.loadingCallback.call(self, false);
			console.log('It seems that Facebook is not available on your network.<br/>Please use another Internet connection');
		}
	});
};


Evrythng.prototype.fbAsyncInit = function() {
	var self = this,
		checkinButton;
	FB.init({appId: this.options.facebookAppId, status: true, cookie: true, xfbml: false, oauth: true});
	FB.getLoginStatus(function(response) {
		if (self.options.checkinButton && (checkinButton = document.getElementById(self.options.checkinButton))) {
			if (response.status === 'connected') {
				if (checkinButton) {
					checkinButton.onclick = function() {
						self.fbCallback.call(self, response);
					};
				}
				if (self.options.forceLogin) self.fbCallback.call(self, response);
			}
			else {
				if (checkinButton) checkinButton.onclick = function() {
					self.fbLogin.call(self, self.fbCallback);
				};
			}
		}
	});
	if (typeof this.options.loadingCallback === 'function') this.options.loadingCallback.call(this, false);
};


Evrythng.prototype.fbLogin = function(callback) {
	var self = this;
	if (typeof this.options.loadingCallback === 'function') this.options.loadingCallback.call(this, true);
	FB.login(function(response) {
		if (!response.authResponse) {
			if (typeof self.options.loadingCallback === 'function') self.options.loadingCallback.call(self, false);
			console.log('FB User cancelled login or did not fully authorize');
		}
		if (typeof callback === 'function') callback.call(self, response);
	}, {scope: 'publish_actions,email,user_birthday,user_location'});
};


Evrythng.prototype.fbCallback = function(response) {
	var self = this;
	if (response.status === 'connected') {
		if (response.authResponse) {
			if (typeof this.options.loadingCallback === 'function') this.options.loadingCallback.call(this, true);
			FB.api('/me', function(fbUser) {
				if (!fbUser.name) {
					self.fbLogin.call(self, self.fbCallback);
				}
				else {
					var data = {
							'access': {
								'token': response.authResponse.accessToken
							}
						},
						dataEscaped = encodeURIComponent(JSON.stringify(data));
					self.jsonp(self.options.evrythngApiUrl + '/auth/facebook?access_token=' + self.options.evrythngApiKey + '&data=' + dataEscaped + '&method=post&callback=?', function(access) {
						if (access.evrythngApiKey) {
							if (typeof self.options.loginCallback === 'function') {
								self.options.loginCallback.call(self, access, fbUser);
								//if (typeof self.options.loadingCallback === 'function') self.options.loadingCallback.call(self, false);
							}
						}
					});
				}
			});
		}
		else {
			console.log('Cannot login via Facebook');
		}
	}
	else if (response.status === 'not_authorized') {
		if (typeof this.options.loadingCallback === 'function') this.options.loadingCallback.call(this, false);
		console.log('User is logged in to Facebook, but has not authenticated your app');
	}
	else {
		if (typeof this.options.loadingCallback === 'function') this.options.loadingCallback.call(this, false);
		console.log('User is not logged in to Facebook');
	}
};


Evrythng.prototype.fbPost = function(options, callback) {
	var self = this;
	/*FB.ui({
		method: 'stream.publish',
		message: options.message,
		attachment: options.attachment,
		action_links: options.action_links,
		user_prompt_message: options.user_prompt_message
	},
	function(response) {
		console.log(response);
		if (response && response.post_id) {
			console.log('Post was published');
		}
		else {
			console.log('Post was not published');
		}
		if (typeof callback === 'function') {
			callback.call(self, response);
		}
	});*/
	//if (typeof this.options.loadingCallback === 'function') this.options.loadingCallback.call(this, true);
	FB.api('/me/feed', 'post', {
		message: options.message,
		picture: options.picture,
		link: options.link,
		name: options.name,
		description: options.description
	}, function(data) {
		//if (typeof self.options.loadingCallback === 'function') self.options.loadingCallback.call(self, false);
		if (typeof callback === 'function') {
			callback.call(self, response);
		}
	});
};

/*
	Actions
*/
Evrythng.prototype.readActionTypes = function(options, callback) {
	var self = this;
	return self.query({
		url: '/actions'
	}, callback);
};


Evrythng.prototype.readActions = function(options, callback) {
	var self = this;
	return self.query({
		url: self.buildUrl('/actions/%s', options.type),
		params: options.params
	}, callback);
};


Evrythng.prototype.readAction = function(options, callback) {
	var self = this;
	return self.query({
		url: self.buildUrl('/actions/' + options.type + '/%s', options.action),
		params: options.params
	}, callback);
};


/*
	Checkin
*/
Evrythng.prototype.checkin = function(options, callback) {
	var self = this;
	var data = {
		thng: options.thng,
		timestamp: new Date().getTime(),
		type: 'checkins',
		tags: options.tags,
		location: {
			latitude: options.defaultLocation ? options.defaultLocation.latitude : null,
			longitude: options.defaultLocation ? options.defaultLocation.longitude : null
		},
		locationSource: 'sensor'
	},
	doCheckin = function() {
		var dataEscaped = encodeURIComponent(JSON.stringify(data));
		self.jsonp(self.options.evrythngApiUrl + '/actions/checkins?access_token=' + options.evrythngApiKey + '&data=' + dataEscaped + '&method=post&callback=?', function(response) {
			if (typeof self.options.loadingCallback === 'function') self.options.loadingCallback.call(self, false);
			if (typeof callback === 'function') {
				callback.call(self, response);
			}
		});
	};
	if (typeof this.options.loadingCallback === 'function') this.options.loadingCallback.call(this, true);
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(position) {
			data.location.latitude = position.coords.latitude;
			data.location.longitude = position.coords.longitude;
			doCheckin();
		}, function(error) {
			// error callback. Just checkin
			doCheckin();
		});
	}
	else {
		doCheckin();
	}
};


/*
	Scan
*/
Evrythng.prototype.scan = function(options, callback) {
	var self = this;
	var data = {
		thng: options.thng,
		timestamp: new Date().getTime(),
		type: 'scans',
		location: {
			latitude: options.defaultLocation ? options.defaultLocation.latitude : null,
			longitude: options.defaultLocation ? options.defaultLocation.longitude : null
		},
		locationSource: 'sensor'
	}
	var doScan = function() {
		var dataEscaped = encodeURIComponent(JSON.stringify(data));
		load.jsonp(self.options.evrythngApiUrl + '/actions/scans?access_token=' + options.evrythngApiKey + '&data=' + dataEscaped + '&method=post&callback=?', function(response) {
			if (typeof self.options.loadingCallback === 'function') self.options.loadingCallback.call(self, false);
			if (typeof callback === 'function') {
				callback.call(self, response);
			}
		});
	};
	if (typeof this.options.loadingCallback === 'function') this.options.loadingCallback.call(this, true);
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(position) {
			data.location.latitude = position.coords.latitude;
			data.location.longitude = position.coords.longitude;
			doScan();
		}, function(error) {
			// error callback. Just scan
			doScan();
		});
	} else {
		doScan();
	}
};


/*
	Products CRUD
*/
Evrythng.prototype.createProduct = function(options, callback) {
	var self = this;
	return self.query({
		url: '/products',
		method: 'post',
		data: options.data
	}, callback);
};


Evrythng.prototype.readProducts = function(options, callback) {
	var self = this;
	return self.query({
		url: '/products'
	}, callback);
};


Evrythng.prototype.readProduct = function(options, callback) {
	var self = this;
	return self.query({
		url: self.buildUrl('/products/%s', options.product)
	}, callback);
};


Evrythng.prototype.updateProduct = function(options, callback) {
	var self = this;
	return self.query({
		url: self.buildUrl('/products/%s', options.product),
		method: 'put',
		data: options.data
	}, callback);
};


Evrythng.prototype.deleteProduct = function(options, callback) {
	var self = this;
	return self.query({
		url: self.buildUrl('/products/%s', options.product),
		method: 'delete'
	}, callback);
};


/*
	Properties CRUD
*/
Evrythng.prototype.createProperty = function(options, callback) {
	var self = this;
	return self.query({
		url: self.buildUrl('/thngs/%s/properties', options.thng),
		method: 'post',
		data: options.data
	}, callback);
};


Evrythng.prototype.readProperties = function(options, callback) {
	var self = this;
	return self.query({
		url: self.buildUrl('/thngs/%s/properties', options.thng)
	}, callback);
};


Evrythng.prototype.readProperty = function(options, callback) {
	var self = this;
	return self.query({
		url: self.buildUrl('/thngs/%s/properties/%s', options.thng, options.property)
	}, callback);
};


Evrythng.prototype.updateProperty = function(options, callback) {
	var self = this;
	return self.query({
		url: self.buildUrl('/thngs/%s/properties', options.thng),
		method: 'put',
		data: options.data
	}, callback);
};


Evrythng.prototype.deleteProperty = function(options, callback) {
	var self = this;
	return self.query({
		url: self.buildUrl('/thngs/%s/properties/%s', options.thng, options.property),
		method: 'delete'
	}, callback);
};


/*
	Thngs R
*/
Evrythng.prototype.readThng = function(options, callback) {
	var self = this;
	return self.query({
		url: self.buildUrl('/thngs/%s', options.thng)
	}, callback);
};


/*
	Analytics R
*/
Evrythng.prototype.readAnalytics = function(options, callback) {
	var self = this;
	return self.query({
		url: self.buildUrl('/analytics/query/%s', options.kpi),
		params: options.params
	}, callback);
};


/*
	Users R
*/
Evrythng.prototype.readUsers = function(options, callback) {
	var self = this;
	return self.query({
		url: self.buildUrl('/users')
	}, callback);
};


Evrythng.prototype.readUser = function(options, callback) {
	var self = this;
	return self.query({
		url: self.buildUrl('/users/%s', options.user)
	}, callback);
};


/*
	Loyalty R
*/
Evrythng.prototype.readLoyaltyStatus = function(options, callback) {
	var self = this;
	return self.query({
		url: self.buildUrl('/loyalty/%s/status', options.user)
	}, callback);
};


Evrythng.prototype.readLoyaltyTransactions = function(options, callback) {
	var self = this;
	return self.query({
		url: self.buildUrl('/loyalty/%s/transactions', options.user)
	}, callback);
};


/*
	Actions R
*/

Evrythng.prototype.readActionTypes = function(options, callback) {
	var self = this;
	return self.query({
		url: '/actions'
	}, callback);
};


Evrythng.prototype.readActions = function(options, callback) {
	var self = this;
	return self.query({
		url: self.buildUrl('/actions/%s', options.type),
		params: options.params
	}, callback);
};


Evrythng.prototype.readAction = function(options, callback) {
	var self = this;
	return self.query({
		url: self.buildUrl('/actions/' + options.type + '/%s', options.action),
		params: options.params
	}, callback);
};


////////////////////////
////// UTILITIES ///////
////////////////////////


/*
	Query API utility
*/
Evrythng.prototype.query = function(options, callback) {
	var self = this;
	if (typeof options.params !== 'object') options.params = {};
	if (options.method) options.params.method = options.method;
	if (options.data) options.params.data = JSON.stringify(options.data);
	options.params.access_token = self.options.evrythngApiKey;
	return self.jsonp(self.options.evrythngApiUrl + options.url + (options.url.indexOf('?') > -1 ? '&' : '?') + 'callback=?&' + self.buildParams(options.params), function(response) {
		console.log(response);
		if (typeof callback === 'function') {
			callback.call(self, response);
		}
		return response;
	});
};


/*
	Helper method to build a resource path
	e.g., buildUrl('/thngs/%s', thngId);
*/
Evrythng.prototype.buildUrl = function(str) {
    var args = [].slice.call(arguments, 1), i = 0;
    return str.replace(/%s/g, function() {
        return args[i++];
    });
};


/*
	Helper method to build query string
*/
Evrythng.prototype.buildParams = function(obj) {
	var out = [];
	for (var i in obj) {
		out.push(i + '=' + encodeURIComponent(obj[i]));
	}
	return out.join('&');
};


/*
	Helper method to read URL parameter
*/
Evrythng.prototype.getParam = function(name) {
	name = name.replace(/[\[]/, '\\\[').replace(/[\]]/, '\\\]');
	var regex = new RegExp('[\\?&]' + name + '=([^&#]*)'),
		results = regex.exec(location.search);
	return results == null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
};


/*
	Load.js - JavaScript js/css, jsonp/ajax, sync/async loader
	Docs and source: https://github.com/articobandurini/load.js
	Distributed under MIT license.
*/
(function(b){var a=b.load=function(d){if(typeof d!=="object"||d instanceof Array){var c=a.args(arguments);d={url:c.url,callback:c.callback}}if(d.url&&d.url.length){if(typeof d.async==="undefined"){d.async=true}if(!d.type){d.type="js"}if(!(d.url instanceof Array)){d.url=[d.url]}a.sequence(d)}return a};a.sequence=function(e){var d=e.url.length,c=function(h){if(!h){h=1}d=d-h;if(!d&&typeof e.callback==="function"){e.callback.call(a)}},g=function(h){return h.length?(function(){c(h.length);a.sequence({url:h,async:e.async,type:e.type,callback:c})}):c};for(var f=0;f<e.url.length;f++){if(e.url[f] instanceof Array){a.sequence({url:e.url[f],async:e.async,type:e.type,callback:g(e.url.slice(f+1))});break}else{a.one({url:e.url[f],async:e.async,type:e.type,callback:c})}}return a};a.one=function(d){var c,f=false,e=document.getElementsByTagName("head")[0]||document.body;if(d.type==="css"||d.url.toLowerCase().match(/\.css$/)){f=true;c=document.createElement("link");c.rel="stylesheet";c.href=a.path(d.url+(d.url.toLowerCase().match(/\.css$/)?"":".css"))}else{c=document.createElement("script");c.async=d.async;c.src=a.path(d.url+(d.type==="jsonp"||d.url.toLowerCase().match(/\.js$/)?"":".js"))}e.appendChild(c);var g=function(h){if(typeof a.ready==="function"){a.ready.call(a,d.url)}if(typeof d.callback==="function"){d.callback.call(a)}if(!f&&h&&h.parentNode){h.parentNode.removeChild(h)}};if(navigator.userAgent.indexOf("MSIE")>=0){c.onreadystatechange=function(){if(this.readyState==="loaded"||this.readyState==="complete"){g(this)}}}else{c.onload=function(){g(this)}}return a};a.js=a.async=function(){var c=a.args(arguments);return a({url:c.url,callback:c.callback})};a.css=function(){var c=a.args(arguments);return a({url:c.url,callback:c.callback,type:"css"})};a.sync=function(){var c=a.args(arguments);return a({url:c.url,callback:c.callback,async:false})};a.jsonp=function(c,e,d){if(typeof e==="function"){if(!a.jsonp.index){a.jsonp.index=1}else{a.jsonp.index++}window["loadCallback"+a.jsonp.index]=e;c=c.replace("=?","=loadCallback"+a.jsonp.index)}return a.one({url:c,async:d!==false,type:"jsonp"})};a.ajax=function(c,h,d){var g;if(window.XMLHttpRequest){g=new XMLHttpRequest()}else{if(window.ActiveXObject){try{g=new ActiveXObject("Msxml2.XMLHTTP")}catch(f){try{g=new ActiveXObject("Microsoft.XMLHTTP")}catch(f){}}}}if(!g){return null}g.onreadystatechange=function(){if(g.readyState===4&&typeof h==="function"){h.call(g,g.responseText)}};g.open("GET",a.path(c),d);g.send();return a};a.args=function(c){var d=Array.prototype.slice.call(c);return{url:d,callback:(typeof d[d.length-1]==="function")?d.pop():undefined}};a.path=function(c){return c.match(/^(https?\:|file\:|\/)/i)?c:a.root+c};a.init=function(){a.root="";var f=document.getElementsByTagName("script"),d,e;for(var c=0;c<f.length;c++){if(f[c].src.match(/(^|\/)load(\.min)?\.js$/)||f[c].id==="load.js"){d=f[c].getAttribute("data-load");if(d){e=d.lastIndexOf("/")+1;a.root=e?d.substring(0,e):"";a({url:d.substring(e),async:f[c].getAttribute("data-async")!=="false"})}break}}};a.init()})(window);
