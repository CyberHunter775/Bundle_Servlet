package com.bundle.servlet.bean;

import javax.servlet.http.HttpServlet;

import org.json.JSONObject;

public class BaseServlet extends HttpServlet {


	protected String success(Object obj) {
		ApiResult apiResult = new ApiResult();
		apiResult.success(obj);
		return (new JSONObject(apiResult)).toString();
	}

	protected String fail(Integer code, String msg) {
		ApiResult apiResult = new ApiResult();
		apiResult.error(code, msg);
		return (new JSONObject(apiResult)).toString();
	}
}
