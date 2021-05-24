package com.ntscorp.reservation.viewcontroller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ntscorp.reservation.domain.reservation.dto.ReservationInfo;
import com.ntscorp.reservation.domain.reservation.service.ReservationService;

@Controller
@Validated
public class LoginController {
	private ReservationService reservationService;
	
	public LoginController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

	@PostMapping("/login")
	public String login(@RequestParam @NotBlank @Email String reservationEmail, 
		HttpSession session, RedirectAttributes redirectAttributes) {
		List<ReservationInfo> reservationList = reservationService.getReservationList(reservationEmail);

		if (reservationList.isEmpty()) {
			redirectAttributes.addFlashAttribute("errorMessage", "예매 내역이 없습니다.");
			return "redirect:/login";
		}

		session.setAttribute("reservationEmail", reservationEmail);
		return "myreservation";
	}
}
