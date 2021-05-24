package com.ntscorp.reservation.viewcontroller;

import java.time.LocalDate;

import javax.validation.constraints.Positive;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ntscorp.reservation.domain.reservation.util.RandomReservationDateGenerator;

@Controller
@Validated
public class ViewController {
	@GetMapping("/")
	public String mainpage() {
		return "main";
	}

	@GetMapping("/myreservation")
	public String myReservation() {
		return "myreservation";
	}

	@GetMapping("/detail")
	public String detail(@RequestParam
	@Positive
	int displayInfoId) {
		return "detail";
	}

	@GetMapping("/reserve")
	public String reserve(@RequestParam
	@Positive
	int displayInfoId, Model model) {
		LocalDate randomReservationDate = RandomReservationDateGenerator.generateRandomReservationDate();
		model.addAttribute("reservationDate", randomReservationDate);

		return "reserve";
	}
}
