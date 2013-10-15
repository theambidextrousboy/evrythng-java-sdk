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
		return aloader.jsonp(url, callback);
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
	aloader.load('//connect.facebook.net/en_US/all.js', function() {
		if (typeof FB != 'object') {
			if (typeof self.options.loadingCallback === 'function') self.options.loadingCallback.call(self, false);
			console.log('It seems that Facebook is not available on your network.<br/>Please use another Internet connection');
		}
	}, true);
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
					self.jsonp(self.options.evrythngApiUrl + '/auth/facebook?access_token=' + self.options.evrythngApiKey + '&data=' + dataEscaped + '&method=post', function(access) {
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
			longitude: options.defaultLocation ? options.defaultLocation.longitude : null,
			locationSource: 'sensor'
		}
	},
	doCheckin = function() {
		var dataEscaped = encodeURIComponent(JSON.stringify(data));
		self.jsonp(self.options.evrythngApiUrl + '/actions/checkins?access_token=' + options.evrythngApiKey + '&data=' + dataEscaped + '&method=post', function(response) {
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
			longitude: options.defaultLocation ? options.defaultLocation.longitude : null,
			locationSource: 'sensor'
		}
	}
	var doScan = function() {
		var dataEscaped = encodeURIComponent(JSON.stringify(data));
		aloader.jsonp(self.options.evrythngApiUrl + '/actions/scans?access_token=' + options.evrythngApiKey + '&data=' + dataEscaped + '&method=post', function(response) {
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
	self.getResource(options, url, callback);
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
	self.jsonp(url + '?access_token=' + self.options.evrythngApiKey, function(response) {
		console.log(response);
		if (typeof callback === 'function') {
			callback.call(self, response);
		}
	});
};

Evrythng.prototype.getThng = function(options, callback) {
	var self = this;
	var url = this.buildUrl("/thngs/%s", options.thng);
	self.getResource(options, url, callback);
};

Evrythng.prototype.getProperties = function(options, callback) {
	var self = this;
	var url = this.buildUrl("/thngs/%s/properties/", options.thng);
	self.getResource(options, url, callback);
};

Evrythng.prototype.getProperty = function(options, callback) {
	var self = this;
	var url = this.buildUrl("/thngs/%s/properties/%s", options.thng, options.property);
	self.getResource(options, url, callback);
};

Evrythng.prototype.getAnalytics = function(options, callback) {
	var self = this;
	//if (typeof this.options.loadingCallback === 'function') this.options.loadingCallback.call(this, true);
	self.jsonp(self.options.evrythngApiUrl + '/analytics/query/' + options.kpi + '?access_token=' + self.options.evrythngApiKey + options.additionalParams, function(response) {
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




aloader = {
	queue: [],
	queueCallback: null,
	load: function(src, callback, async, holder) {
		if(src instanceof Array) {
			if(!src.length) {
				return false;
			}
			else if(src.length == 1) {
				src = src[0];
			}
			else{
				aloader.queue = src;
				aloader.queueCallback = callback;
				callback = null;
				src = aloader.queue[0];
			}
		}
		if(!src) {
			return false;
		}
		var self = this,
			tag,
			css = src.lastIndexOf('.css') > -1;
		if (!holder) {
			holder = document.getElementsByTagName('head')[0] || document.body;
		}
		if (css) {
			tag = document.createElement('link');
			tag.rel = 'stylesheet';
			tag.href = src;
		}
		else {
			tag = document.createElement('script');
			tag.async = async ? true : false;
			tag.src = src;
		}
		holder.appendChild(tag);
		var receive = function(tag) {
			if(aloader.queue.length && src == aloader.queue[0]) {
				aloader.queue.shift();
				if(aloader.queue.length) {
					aloader.load(aloader.queue[0]);
				}
				else{
					callback = aloader.queueCallback;
				}
			}
			if(typeof callback == 'function') {
				callback.call(self, tag);
			}
			if (!css && tag && tag.parentNode) {
				tag.parentNode.removeChild(tag);
			}
		};
		if(navigator.userAgent.indexOf('MSIE') >= 0) {
			tag.onreadystatechange = function() {
				if(this.readyState == 'loaded' || this.readyState == 'complete') {
					receive(this);
				}
			};
		}
		else{
			tag.onload = function() {
				receive(this);
			};
		}
	},
	jsonp: function(src, callback, async, holder) {
		if (!aloader.jsonpIndex) {
			aloader.jsonpIndex = 1;
		}
		else {
			aloader.jsonpIndex++;
		}
		if (typeof callback == 'function') {
			window['aloaderCallback' + aloader.jsonpIndex] = callback;
			src += ((src.indexOf('?') < 0) ? '?' : '&') + 'callback=' + 'aloaderCallback' + aloader.jsonpIndex;
		}
		else {
			src += callback;
		}
		aloader.load(src, null, async, holder);
	},
	ajax: function(src, callback, options) {
		if (window.XMLHttpRequest) {
			httpRequest = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			try {
				httpRequest = new ActiveXObject('Msxml2.XMLHTTP');
			}
			catch (e) {
				try {
					httpRequest = new ActiveXObject('Microsoft.XMLHTTP');
				}
				catch (e) {}
			}
		}
		if (!httpRequest) return false;
		httpRequest.onreadystatechange = function() {
			if (httpRequest.readyState === 4 && httpRequest.status === 200) {
				if (typeof callback == 'function') callback.call(httpRequest, httpRequest.responseText);
			}
		};
		httpRequest.open('GET', src);
		httpRequest.send();
	}
};