package com.bt.helper;

import com.avis.datautils.FileReader;
import com.avis.datautils.PropertyUtils;

import static com.avis.core.Configuration.IMF_REQUEST_FILE;

public class BTConstants {
	
	public static final String CONTENT_TYPE = "Content-Type";
	public static final String CONTENT_TYPE_JSON = "application/json;charset=UTF-8";
	public static final String SERVER_TYPE = "server";
	public static final String SERVER_TYPE_VALUE = "envoy";
	public static final String CONNECTION_TYPE = "Connection";
	public static final String CONNECTION_TYPE_VALUE = "keep-alive";
	public static final String X_ENVOY_DEC_TYPE = "x-envoy-decorator-operation";
	public static final String X_ENVOY_DEC_VALUE_HIERARCHY = "location-service-hierarchy-graphql.location-main.svc.cluster.local:8080/*";
	public static final String X_ENVOY_DEC_VALUE_GENERAL = "location-service-general-graphql.location-main.svc.cluster.local:8080/*";
	public static final String X_ENVOY_DEC_VALUE = "location-service-general-graphql.location-main.svc.cluster.local:8080/*";
	public static final String RMI_USER = PropertyUtils.getValue("RMI_USER");
	public static final String RMI_PASSWORD = PropertyUtils.getValue("RMI_PASSWORD");
	public static final String REPLACE_STRING = "{ number: 30702, brand: \"A\" }";

	public static final int AVL_SUCCESS = 200;
	public static final int AVL_BAD_REQUEST = 400;

	public static final String PRODUCT_MISMATCH = "Cars object did not Match";

	public static final String PRODUCT_COUNT_MISMATCH1 = "Number of products(cars returned is less than in IMF)";
	public static final String PRODUCT_COUNT_MISMATCH2 = "Number of products(cars returned is more than in IMF)";

	public static String IMF_REQUEST = FileReader.readFile(System.getProperty("user.dir") + IMF_REQUEST_FILE);
	public static final String COMPARISON_ERROR_MSG = "IMF value(%s = %s) is not equal to JSON value(%s = %s)\n";

}
