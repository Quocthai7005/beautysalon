package com.majestic.nails.common.error;

public enum NewsErrCode implements ErrorCode{
	INVALID_DTO(1200, "Invalid dto."),
	BLANK_NAME(1201, "blank name."),
	BLANK_CONTENT(1202, "blank content.");
	
	private NewsErrCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	private final int code;
	private final String msg;
	
	@Override
	public int getCode() {
		return this.code;
	}
	@Override
	public String getMsg() {
		return this.msg;
	}
}
