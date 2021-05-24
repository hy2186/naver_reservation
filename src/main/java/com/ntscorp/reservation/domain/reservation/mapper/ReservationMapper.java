package com.ntscorp.reservation.domain.reservation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ntscorp.reservation.domain.reservation.dto.ReservationInfo;
import com.ntscorp.reservation.domain.reservation.dto.ReservationParam;
import com.ntscorp.reservation.domain.reservation.dto.ReservationPrice;

@Repository
public interface ReservationMapper {
	int insertReservation(ReservationParam reservationParam);

	int insertReservationPrice(List<ReservationPrice> reservationPrice);

	List<ReservationInfo> selectReservationByEmail(String reservationEmail);

	long selectTotalPriceByReservationId(int reservationInfoId);

	int updateCancelFlagById(@Param("reservationInfoId") int reservationInfoId, @Param("cancelFlag") int cancelFlag);
}
