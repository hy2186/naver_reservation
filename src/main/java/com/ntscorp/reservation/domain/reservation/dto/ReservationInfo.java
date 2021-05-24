package com.ntscorp.reservation.domain.reservation.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.ntscorp.reservation.domain.displayInfo.dto.DisplayInfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationInfo {
	private boolean cancelYn;
	private LocalDateTime createDate;
	private DisplayInfo displayInfo;
	private int displayInfoId;
	private LocalDateTime modifyDate;
	private int productId;
	private LocalDate reservationDate;
	private String reservationEmail;
	private int reservationInfoId;
	private String reservationName;
	private String reservationTelephone;
	private long totalPrice;
}
