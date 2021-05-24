package com.ntscorp.reservation.common.file.mapper;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.ntscorp.reservation.common.file.vo.ImageFile;

@Repository
public interface FileMapper {
	int insertImageFile(ImageFile imageFile);

	Optional<ImageFile> selectImageFile(int fileId);
}
