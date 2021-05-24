package com.ntscorp.reservation.domain.displayInfo.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DisplayInfo {
	private int categoryId;
	private String categoryName;
	private LocalDateTime createDate;
	private int displayInfoId;
	private String email;
	private String homepage;
	private LocalDateTime modifyDate;
	private String openingHours;
	private String placeLot;
	private String placeName;
	private String placeStreet;
	private String productContent;
	private String productDescription;
	private String productEvent;
	private int productId;
	private String telephone;
}
