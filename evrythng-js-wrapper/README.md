Evrythng.js
===========

Client-side JavaScript library to access Evrythng API.

## Initialization

The library can work both with and without jQuery.

	Evt = new Evrythng({
		evrythngApiKey: 'iZpKaNclRS...vuo59MouTs42',
		evrythngApiUrl: 'https://js...evrythng.net',
		other options...
	});

If you have to use jQuery (e.g. to use with with Ember.js), pass it's reference as an option:

	Evt = new Evrythng({
		jQuery: jQuery
	});

