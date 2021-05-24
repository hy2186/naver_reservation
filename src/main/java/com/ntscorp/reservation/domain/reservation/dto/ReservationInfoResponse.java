package com.ntscorp.reservation.domain.reservation.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ReservationInfoResponse {
	private List<ReservationInfo> reservations;
	private int size;
	
	@Builder
	public ReservationInfoResponse(List<ReservationInfo> reservations) {
		this.reservations = reservations;
		this.size = reservations.size();
	}
}
