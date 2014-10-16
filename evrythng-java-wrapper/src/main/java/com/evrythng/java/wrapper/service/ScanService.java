/*
 * (c) Copyright 2014 EVRYTHNG Ltd London / Olten
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.service;

import java.io.IOException;
import java.io.InputStream;

import com.evrythng.java.wrapper.ApiManager;
import com.evrythng.java.wrapper.core.EvrythngApiBuilder.Builder;
import com.evrythng.java.wrapper.core.EvrythngServiceBase;
import com.evrythng.java.wrapper.core.http.Status;
import com.evrythng.java.wrapper.exception.EvrythngClientException;
import com.evrythng.java.wrapper.exception.EvrythngException;
import com.evrythng.thng.resource.model.li.UrlBinding;
import com.fasterxml.jackson.core.type.TypeReference;

/** 
 * 
 **/
public class ScanService extends EvrythngServiceBase {

	public static final String PATH_SCAN = "/scan";

	public static final String PATH_SCAN_RECOGNIZE = PATH_SCAN + "/recognitions";
	
	public static final String PATH_SCAN_BARCODE = PATH_SCAN + "/barcode";
	
	public static enum ScanMethod {
		
		QRCODE("qrcode"),
 ONEDBARCODE("1dbarcode"),
		OBJPIC("objpic");
		
		private String queryParam;
		
		private ScanMethod(String queryParam) {
			this.queryParam = queryParam;
		}

		public String getQueryParam() {
			return this.queryParam;
		}
	}
	/**
	 * @param api
	 */
	public ScanService(ApiManager api) {
		super(api);
	}
	
	/**
	 * POST {@value #PATH_SCAN_RECOGNIZE}
	 * 
	 * Scan a Base64 image. Return the corresponding binding in 
	 */
	public Builder<UrlBinding> recognitionsCreator(UrlBinding imageInBinding, ScanMethod... methods) throws EvrythngClientException, EvrythngException {
		Builder<UrlBinding> result = post(PATH_SCAN_RECOGNIZE, imageInBinding, Status.OK, new TypeReference<UrlBinding>() {
		});
		for (ScanMethod m : methods) {
			result.queryParam(m.getQueryParam(), "true");
		}
		return result;
	}

	/**
	 * POST {@value #PATH_SCAN_RECOGNIZE}
	 * 
	 * @param base64Image
	 *            base64 encoded image, with mime type header.
	 * @param methods
	 * @return
	 * @throws EvrythngClientException
	 * @throws EvrythngException
	 */
	public Builder<UrlBinding> recognitionsCreator(String base64Image, ScanMethod... methods) throws EvrythngClientException, EvrythngException {
		UrlBinding imageInBinding = new UrlBinding();
		imageInBinding.setImage(base64Image);
		return recognitionsCreator(imageInBinding, methods);
	}

	/**
	 * POST {@value #PATH_SCAN_RECOGNIZE}
	 * 
	 * @param image
	 *            image input stream. The input stream is quietly closed after
	 *            call of this method.
	 * @param mime
	 * @param methods
	 * @return
	 * @throws EvrythngClientException
	 * @throws EvrythngException
	 * @throws IOException
	 */
	public Builder<UrlBinding> recognitionsCreator(InputStream image, String mime, ScanMethod... methods) throws EvrythngClientException, EvrythngException, IOException {
		String mimeAndB64 = encodeBase64(image, mime);
		return recognitionsCreator(mimeAndB64, methods);
	}
	
	/**
	 * POST {@value #PATH_SCAN_BARCODE}
	 * 
	 * Scan a barcode. Return the corresponding binding in 
	 */
	public Builder<UrlBinding> scanBarcodeCreator(UrlBinding scanBarcodeData) throws EvrythngClientException, EvrythngException {
		Builder<UrlBinding> result = post(PATH_SCAN_BARCODE, scanBarcodeData, Status.OK, new TypeReference<UrlBinding>() {
		});
		
		return result;
	}	
}
