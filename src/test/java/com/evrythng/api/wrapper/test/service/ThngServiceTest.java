package com.evrythng.api.wrapper.test.service;

import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.evrythng.api.wrapper.ApiConfiguration;
import com.evrythng.api.wrapper.ApiManager;
import com.evrythng.api.wrapper.service.ThngService;

/**
 * 
 * @author tpham
 */
public class ThngServiceTest {

	private ApiManager apiManager;
	private ThngService thngService;

	private static final Logger logger = LoggerFactory.getLogger(ThngServiceTest.class);

	@Before
	public void init() {
		ApiConfiguration config = new ApiConfiguration("MaEDwlYA2Xxp2oSzt9uxbenTTyiYdZgnnVQlGnmnDCiLgfUO2pKzpbhzAeotwpc0KV9J8M2QssIdxOOt");
		config.setUrl("http://api.staging.evrythng.net");

		apiManager = new ApiManager(config);
		thngService = apiManager.thngService();
	}
}
