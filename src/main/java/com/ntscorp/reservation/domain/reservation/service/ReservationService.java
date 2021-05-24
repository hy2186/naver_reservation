package com.ntscorp.reservation.domain.reservation.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ntscorp.reservation.domain.reservation.dto.ReservationInfo;
import com.ntscorp.reservation.domain.reservation.dto.ReservationParam;
import com.ntscorp.reservation.domain.reservation.dto.ReservationPrice;
import com.ntscorp.reservation.domain.reservation.mapper.ReservationMapper;

@Service
public class ReservationService {
	private final ReservationMapper reservationMapper;
	
	public ReservationService(ReservationMapper reservationMapper) {
		this.reservationMapper = reservationMapper;
	}
	
	@Transactional
	public int addReservation(ReservationParam reservationParam) {
		reservationMapper.insertReservation(reservationParam);
		int reservationInfoId = reservationParam.getId();

		List<ReservationPrice> prices = reservationParam.getPrices();
		prices.forEach(price -> price.setReservationInfoId(reservationInfoId));

		reservationMapper.insertReservationPrice(prices);

		return reservationInfoId;
	}

	public List<ReservationInfo> getReservationList(String reservationEmail) {
		return reservationMapper.selectReservationByEmail(reservationEmail);
	}

	public int cancelReservation(int reservationId) {
		return reservationMapper.updateCancelFlagById(reservationId, 1);
	}
}
