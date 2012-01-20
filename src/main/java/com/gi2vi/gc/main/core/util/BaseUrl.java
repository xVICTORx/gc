package com.gi2vi.gc.main.core.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class BaseUrl {
	private static final Map<String, Integer> defaultSchemePortMap;

	static {
		defaultSchemePortMap = new HashMap<String, Integer>();
		defaultSchemePortMap.put("http", new Integer(80));
		defaultSchemePortMap.put("https", new Integer(443));
	}

	public static String getBaseURL(HttpServletRequest request, String resource) {
		StringBuffer baseUrl = new StringBuffer();

		baseUrl.append(request.getScheme()).append("://");
		baseUrl.append(getServerName(request));
		baseUrl.append(request.getContextPath());

		if (StringUtils.isNotBlank(resource)) {
			if (!baseUrl.toString().endsWith("/")) {
				baseUrl.append("/");
			}

			baseUrl.append(resource);
		}

		return baseUrl.toString();
	}

	private static String getServerName(HttpServletRequest request) {
		String name = request.getHeader("X-Forwarded-Host");
		if (name == null) {
			name = request.getServerName();
			int portnum = request.getServerPort();
			Integer defaultPortnum = (Integer) defaultSchemePortMap.get(request
					.getScheme());
			if ((defaultPortnum == null)
					|| (defaultPortnum.intValue() != portnum)) {
				name = name + ":" + String.valueOf(portnum);
			}
			return name;
		}
		return name;
	}
}
