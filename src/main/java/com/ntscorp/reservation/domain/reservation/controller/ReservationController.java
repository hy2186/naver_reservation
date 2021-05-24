package com.ntscorp.reservation.domain.reservation.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ntscorp.reservation.domain.reservation.dto.ReservationInfo;
import com.ntscorp.reservation.domain.reservation.dto.ReservationInfoResponse;
import com.ntscorp.reservation.domain.reservation.dto.ReservationParam;
import com.ntscorp.reservation.domain.reservation.dto.ReservationResponse;
import com.ntscorp.reservation.domain.reservation.service.ReservationService;

@RestController
@Validated
public class ReservationController {
	private static final String DUMMY_EMAIL = "aaa@bbb.ccc";
	private static final String DUMMY_NAME = "xyz";
	private static final String DUMMY_TELEPHONE = "012-345-6789";

	private final ReservationService reservationService;
	
	public ReservationController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@PostMapping("/api/reservations")
	@ResponseStatus(HttpStatus.CREATED)
	public ReservationResponse addReservation(@RequestBody @Valid ReservationParam reservationParam) {
		int reservationInfoId = reservationService.addReservation(reservationParam);

		return ReservationResponse.builder()
			.cancelYn(true)
			.createDate(LocalDateTime.now())
			.displayInfoId(reservationInfoId)
			.modifyDate(LocalDateTime.now())
			.prices(Collections.emptyList())
			.productId(0)
			.reservationDate(LocalDate.now())
			.reservationEmail(DUMMY_EMAIL)
			.reservationInfoId(0)
			.reservationName(DUMMY_NAME)
			.reservationTelephone(DUMMY_TELEPHONE)
			.build();
	}

	@GetMapping("/api/reservations")
	public ReservationInfoResponse getReservationList(@RequestParam @NotBlank @Email String reservationEmail) {
		List<ReservationInfo> reservationList = reservationService.getReservationList(reservationEmail);

		return ReservationInfoResponse.builder()
			.reservations(reservationList)
			.build();
	}

	@PutMapping("/api/reservations/{reservationId}")
	public ReservationResponse cancelReservation(@PathVariable @Positive int reservationId) {
		reservationService.cancelReservation(reservationId);

		return ReservationResponse.builder()
			.cancelYn(true)
			.createDate(LocalDateTime.now())
			.displayInfoId(reservationId)
			.modifyDate(LocalDateTime.now())
			.prices(Collections.emptyList())
			.productId(0)
			.reservationDate(LocalDate.now())
			.reservationEmail(DUMMY_EMAIL)
			.reservationInfoId(0)
			.reservationName(DUMMY_NAME)
			.reservationTelephone(DUMMY_TELEPHONE)
			.build();
	}
}
