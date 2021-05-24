package com.ntscorp.reservation.domain.reservation.util;

import java.time.LocalDate;

public class RandomReservationDateGenerator {
	public static LocalDate generateRandomReservationDate() {
		int plusDay = (int)((Math.random() * 10000) % 5) + 1;

		LocalDate reservationDate = LocalDate.now().plusDays(plusDay);

		return reservationDate;
	}
}
