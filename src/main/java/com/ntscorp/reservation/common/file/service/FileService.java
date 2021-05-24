package com.ntscorp.reservation.common.file.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ntscorp.reservation.common.file.mapper.FileMapper;
import com.ntscorp.reservation.common.file.vo.ImageFile;
import com.ntscorp.reservation.exception.custom.NotFoundException;

@Service
public class FileService {
	@Value("${spring.directory.root}")
	private String rootPath;
	@Value("${spring.directory.comment-image}")
	private String commentImagePath;

	private final FileMapper fileMapper;

	public FileService(FileMapper fileMapper) {
		this.fileMapper = fileMapper;
	}

	public int addImageFile(ImageFile imageFile) {
		fileMapper.insertImageFile(imageFile);

		return imageFile.getId();
	}

	public ImageFile getImageFile(int fileId) {
		return fileMapper.selectImageFile(fileId).orElseThrow(() -> new NotFoundException("Image not found!"));
	}
}
