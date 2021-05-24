package com.ntscorp.reservation.exception.status;

import lombok.Getter;

@Getter
public enum ErrorStatus {
	INVALID_INPUT_VALUE("INVALID INPUT VALUE"),
	NOT_FOUND("NOT FOUND"),
	INTERNAL_SERVER_ERROR("INTERNAL SERVER ERROR");

	private final String message;

	ErrorStatus(String message) {
		this.message = message;
	}
}
