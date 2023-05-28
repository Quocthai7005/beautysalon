package com.doctor.spa.common.response;

import com.doctor.spa.common.error.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorDetail {
	private int code;
	private String msg;
	private Object value;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public ErrorDetail(int code, String msg) {
		this(code, msg, null);
	}

	public ErrorDetail(ErrorCode errorCode) {
		this(errorCode, null);
	}

	public ErrorDetail(ErrorCode errorCode, Object value) {
		this(errorCode.getCode(), errorCode.getMsg(), value);
	}

	public ErrorDetail(int code, String msg, Object value) {
		super();
		this.code = code;
		this.msg = msg;
		this.value = value;
	}
}
