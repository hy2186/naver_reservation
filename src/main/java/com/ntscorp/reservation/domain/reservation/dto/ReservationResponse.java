package com.ntscorp.reservation.domain.reservation.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReservationResponse {
	private boolean cancelYn;
	private LocalDateTime createDate;
	private int displayInfoId;
	private LocalDateTime modifyDate;
	private List<ReservationPrice> prices;
	private int productId;
	private LocalDate reservationDate;
	private String reservationEmail;
	private int reservationInfoId;
	private String reservationName;
	private String reservationTelephone;
}
