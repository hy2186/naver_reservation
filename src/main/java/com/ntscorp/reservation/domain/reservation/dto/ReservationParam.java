package com.ntscorp.reservation.domain.reservation.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationParam {
	private int id;

	@NotNull
	@Positive
	private int displayInfoId;

	@NotEmpty
	private List<ReservationPrice> prices;

	@NotNull
	@Positive
	private int productId;

	@NotBlank
	@Email
	private String reservationEmail;

	@NotBlank
	private String reservationName;

	@NotBlank
	private String reservationTelephone;

	@NotNull
	private LocalDate reservationYearMonthDay;
}
