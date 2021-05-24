package com.ntscorp.reservation.domain.reservation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationPrice {
	private int count;
	private int productPriceId;
	private int reservationInfoId;
	private int reservationInfoPriceId;
}
