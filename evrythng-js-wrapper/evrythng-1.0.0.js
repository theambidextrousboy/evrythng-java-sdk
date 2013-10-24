/*!
 * Client-side JavaScript library to access Evrythng API $Id$
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


Evrythng.prototype.jsonp = function(url, callback) {
	if (typeof this.options.jQuery === 'function') {
		return this.options.jQuery.getJSON(url).then(callback);
	}
	else {
		return load.jsonp(url, callback);
	}
};


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
	var self = this;
	FB.init({appId: this.options.facebookAppId, status: true, cookie: true, xfbml: false, oauth: true});
	FB.getLoginStatus(function(response) {
		var checkinButton = document.getElementById(self.options.checkinButton);
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


Evrythng.prototype.getProduct = function(options, callback) {
	var self = this;
	var url = this.buildUrl("/products/%s", options.product);
	return self.getResource(options, url, callback);
};

/** 
 * Helper method to build a resource path
 * e.g., buildUrl("/thngs/%s", options.thng);
 */
Evrythng.prototype.buildUrl = function(str) {
	var self = this;
    var args = [].slice.call(arguments, 1), i = 0;
    return self.options.evrythngApiUrl + '/' + str.replace(/%s/g, function() {
        return args[i++];
    });
};

Evrythng.prototype.getResource = function(options, url, callback) {
	var self = this;
	return self.jsonp(url + '?access_token=' + self.options.evrythngApiKey + '&callback=?', function(response) {
		console.log(response);
		if (typeof callback === 'function') {
			callback.call(self, response);
		}
	});
};

Evrythng.prototype.getThng = function(options, callback) {
	var self = this;
	var url = this.buildUrl("/thngs/%s", options.thng);
	return self.getResource(options, url, callback);
};

Evrythng.prototype.getProperties = function(options, callback) {
	var self = this;
	var url = this.buildUrl("/thngs/%s/properties/", options.thng);
	return self.getResource(options, url, callback);
};

Evrythng.prototype.getProperty = function(options, callback) {
	var self = this;
	var url = this.buildUrl("/thngs/%s/properties/%s", options.thng, options.property);
	return self.getResource(options, url, callback);
};

Evrythng.prototype.getAnalytics = function(options, callback) {
	var self = this;
	//if (typeof this.options.loadingCallback === 'function') this.options.loadingCallback.call(this, true);
	return self.jsonp(self.options.evrythngApiUrl + '/analytics/query/' + options.kpi + '?access_token=' + self.options.evrythngApiKey + options.additionalParams + '&callback=?', function(response) {
		//if (typeof self.options.loadingCallback === 'function') self.options.loadingCallback.call(self, false);
		if (typeof callback === 'function') {
			callback.call(self, response);
		}
	});
};


Evrythng.prototype.getParam = function(name) {
	name = name.replace(/[\[]/, '\\\[').replace(/[\]]/, '\\\]');
	var regex = new RegExp('[\\?&]' + name + '=([^&#]*)'),
		results = regex.exec(location.search);
	return results == null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
};





/*
	load.js
*/
(function(context){
	
	/*
		load
	*/
	var self = context.load = function(options) {
		if (typeof options !== 'object' || options instanceof Array) {
			var args = self.args(arguments);
			options = {
				url: args.url,
				callback: args.callback
			};
		}
		if (options.url && options.url.length) {
			if (typeof options.async === 'undefined') options.async = true;
			if (!options.type) options.type = 'js';
			if (!(options.url instanceof Array)) options.url = [options.url];
			self.sequence(options);
		}
		return self;
	};
	
	
	/*
		sequence
	*/
	self.sequence = function(options) {
		var queue = options.url.length,
			queueCallback = function(amount) {
				if (!amount) amount = 1;
				queue = queue - amount;
				if (!queue && typeof options.callback === 'function') options.callback.call(self);
			},
			sequenceCallback = function(url) {
				return url.length ? (function() {
					queueCallback(url.length);
					self.sequence({
						url: url,
						async: options.async,
						type: options.type,
						callback: queueCallback
					});
				}) : queueCallback;
			};
		for (var i=0; i<options.url.length; i++) {
			if (options.url[i] instanceof Array) {
				self.sequence({
					url: options.url[i],
					async: options.async,
					type: options.type,
					callback: sequenceCallback(options.url.slice(i + 1))
				});
				break;
			}
			else {
				self.one({
					url: options.url[i],
					async: options.async,
					type: options.type,
					callback: queueCallback
				});
			}
		}
		return self;
	};
	
	
	/*
		one
	*/
	self.one = function(options) {
		var tag, css = false,
			holder = document.getElementsByTagName('head')[0] || document.body;
		if (options.type === 'css' || options.url.toLowerCase().match(/\.css$/)) {
			css = true;
			tag = document.createElement('link');
			tag.rel = 'stylesheet';
			tag.href = self.path(options.url + (options.url.toLowerCase().match(/\.css$/) ? '' : '.css'));
		}
		else {
			tag = document.createElement('script');
			tag.async = options.async;
			tag.src = self.path(options.url + (options.type === 'jsonp' || options.url.toLowerCase().match(/\.js$/) ? '' : '.js'));
		}
		holder.appendChild(tag);
		var receive = function(tag) {
			if (typeof self.ready === 'function') {
				self.ready.call(self, options.url);
			}
			if (typeof options.callback === 'function') {
				options.callback.call(self);
			}
			if (!css && tag && tag.parentNode) {
				tag.parentNode.removeChild(tag);
			}
		};
		if (navigator.userAgent.indexOf('MSIE') >= 0) {
			tag.onreadystatechange = function() {
				if (this.readyState === 'loaded' || this.readyState === 'complete') {
					receive(this);
				}
			};
		}
		else {
			tag.onload = function() {
				receive(this);
			};
		}
		return self;
	};
	
	
	/*
		js, async
	*/
	self.js =
	self.async = function() {
		var args = self.args(arguments);
		return self({
			url: args.url,
			callback: args.callback
		});
	};
	
	
	/*
		css
	*/
	self.css = function() {
		var args = self.args(arguments);
		return self({
			url: args.url,
			callback: args.callback,
			type: 'css'
		});
	};
	
	
	/*
		sync
	*/
	self.sync = function() {
		var args = self.args(arguments);
		return self({
			url: args.url,
			callback: args.callback,
			async: false
		});
	};
	
	
	/*
		jsonp
	*/
	self.jsonp = function(url, callback, async) {
		if (typeof callback === 'function') {
			if (!self.jsonp.index) {
				self.jsonp.index = 1;
			}
			else {
				self.jsonp.index++;
			}
			window['loadCallback' + self.jsonp.index] = callback;
			url = url.replace('=?', '=loadCallback' + self.jsonp.index);
		}
		return self.one({
			url: url,
			async: async !== false,
			type: 'jsonp'
		});
	};
	
	
	/*
		ajax
	*/
	self.ajax = function(url, callback, async) {
		var xhr;
		if (window.XMLHttpRequest) {
			xhr = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			try {
				xhr = new ActiveXObject('Msxml2.XMLHTTP');
			}
			catch(e) {
				try {
					xhr = new ActiveXObject('Microsoft.XMLHTTP');
				}
				catch(e) {}
			}
		}
		if (!xhr) return null;
		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4 && typeof callback === 'function') callback.call(xhr, xhr.responseText);
		};
		xhr.open('GET', self.path(url), async);
		xhr.send();
		return self;
	}
	
	
	/*
		args
	*/
	self.args = function(input) {
		var args = Array.prototype.slice.call(input);
		return {
			url: args,
			callback: (typeof args[args.length - 1] === 'function') ? args.pop() : undefined
		};
	};
	
	
	/*
		path
	*/
	self.path = function(input) {
		return input.match(/^(https?\:|file\:|\/)/i) ? input : self.root + input;
	};
	
	
	/*
		init
	*/
	self.init = function() {
		self.root = '';
		var scriptTags = document.getElementsByTagName('script'), dataLoad, slashIndex;
		for (var i=0; i<scriptTags.length; i++) {
			if (scriptTags[i].src.indexOf('load.js') > -1 || scriptTags[i].id === 'load.js') {
				dataLoad = scriptTags[i].getAttribute('data-load');
				if (dataLoad) {
					slashIndex = dataLoad.lastIndexOf('/') + 1;
					self.root = slashIndex ? dataLoad.substring(0, slashIndex) : '';
					self({
						url: dataLoad.substring(slashIndex),
						async: scriptTags[i].getAttribute('data-async') !== 'false'
					});
				}
				break;
			}
		}
	};
	
	self.init();
	
})(window);