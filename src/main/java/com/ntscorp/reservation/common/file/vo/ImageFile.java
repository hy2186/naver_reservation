package com.ntscorp.reservation.common.file.vo;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ImageFile {
	private int id;
	private String fileName;
	private String saveFileName;
	private String contentType;
	private boolean deleteFlag;
	private LocalDateTime createDate;
	private LocalDateTime modifyDate;
	
	@Builder
	public ImageFile(String fileName, String saveFileName, String contentType) {
		this.fileName = fileName;
		this.saveFileName = saveFileName;
		this.contentType = contentType;
	}
}
