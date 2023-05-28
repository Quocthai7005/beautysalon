package com.doctor.spa.common.response;

import java.util.Collection;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseBody<T> {

	private final int code;
	private String msg;
	public ResponseBody(int code) {
		super();
		this.code = code;
	}

	private Collection<ErrorDetail> errors;
	private T data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Collection<ErrorDetail> getErrors() {
		return errors;
	}

	public void setErrors(Collection<ErrorDetail> errors) {
		this.errors = errors;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public ResponseBody(HttpStatus code) {
		this(code.value());
	}

	public ResponseBody(HttpStatus code, T data) {
		this(code.value(), data);
	}

	public ResponseBody(int code, T data) {
		this(code, null, null, data);
	}

	public ResponseBody(HttpStatus code, String msg) {
		this(code.value(), msg);
	}

	public ResponseBody(int code, String msg) {
		this(code, msg, null);
	}

	public ResponseBody(HttpStatus code, String msg, Collection<ErrorDetail> errors) {
		this(code.value(), msg, errors);
	}

	public ResponseBody(int code, String msg, Collection<ErrorDetail> errors) {
		this(code, msg, errors, null);
	}

	public ResponseBody(int code, String msg, Collection<ErrorDetail> errors, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.errors = errors;
		this.data = data;
	}
}
