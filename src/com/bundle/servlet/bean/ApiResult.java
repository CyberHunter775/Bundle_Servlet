package com.bundle.servlet.bean;

public class ApiResult {

	private Integer code;
	private String msg;
	private Object data;

	public void success(Object data) {
		this.code = Integer.valueOf(200);
		this.msg = "success";
		this.data = data;
	}

	public void error(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
