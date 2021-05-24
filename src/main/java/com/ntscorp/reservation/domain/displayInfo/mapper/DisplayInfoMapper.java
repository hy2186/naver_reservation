package com.ntscorp.reservation.domain.displayInfo.mapper;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.ntscorp.reservation.domain.displayInfo.dto.DisplayInfo;
import com.ntscorp.reservation.domain.displayInfo.dto.DisplayInfoImage;

@Repository
public interface DisplayInfoMapper {
	Optional<DisplayInfo> selectDisplayInfoById(int displayInfoId);
	
	Optional<DisplayInfoImage> selectDisplayInfoImageById(int displayInfoId);
}
