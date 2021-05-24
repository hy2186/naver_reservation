package com.ntscorp.reservation.domain.displayInfo.service;

import org.springframework.stereotype.Service;

import com.ntscorp.reservation.domain.displayInfo.dto.DisplayInfo;
import com.ntscorp.reservation.domain.displayInfo.dto.DisplayInfoImage;
import com.ntscorp.reservation.domain.displayInfo.mapper.DisplayInfoMapper;
import com.ntscorp.reservation.exception.custom.NotFoundException;

@Service
public class DisplayInfoService {
	private final DisplayInfoMapper displayInfoMapper;

	public DisplayInfoService(DisplayInfoMapper displayInfoMapper) {
		this.displayInfoMapper = displayInfoMapper;
	}

	public DisplayInfo getDisplayInfo(int displayInfoId) {
		return displayInfoMapper.selectDisplayInfoById(displayInfoId)
			.orElseThrow(() -> new NotFoundException("DisplayInfo not Found!"));
	}

	public DisplayInfoImage getDisplayInfoImage(int displayInfoId) {
		return displayInfoMapper.selectDisplayInfoImageById(displayInfoId)
			.orElseThrow(() -> new NotFoundException("DisplayInfoImage not Found!"));
	}
}
