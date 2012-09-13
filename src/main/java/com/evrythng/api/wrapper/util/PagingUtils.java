/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 * 
 */
package com.evrythng.api.wrapper.util;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/** 
 * 
 * TODO Comment this class
 * 
 * @author     Username (tpham)
 * @copyright  2012 Evrythng Ltd London / Zurich
 **/

public class PagingUtils {
	public static MultiValueMap<String, String> buildPagingParametersWithSize(int page, int pageSize, long sinceId, long maxId) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.set("page", String.valueOf(page));
        parameters.set("size", String.valueOf(pageSize));
        if (sinceId > 0) {
            parameters.set("since_id", String.valueOf(sinceId));
        }
        if (maxId > 0) {
            parameters.set("max_id", String.valueOf(maxId));
        }
        return parameters;
    }
}
