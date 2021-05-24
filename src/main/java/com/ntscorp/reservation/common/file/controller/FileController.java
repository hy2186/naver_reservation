package com.ntscorp.reservation.common.file.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ntscorp.reservation.common.file.service.FileService;
import com.ntscorp.reservation.common.file.vo.ImageFile;
import com.ntscorp.reservation.exception.custom.FileLoadException;

@Controller
@Validated
@PropertySource("classpath:/resources/application.properties")
public class FileController {
	@Value("${spring.directory.root}")
	private String rootPath;
	
	private final FileService fileService;
	
	public FileController(FileService fileService) {
		this.fileService = fileService;
	}
	
	@GetMapping("/download/image/{fileId}")
	public void getCommentImage(@PathVariable @Positive int fileId, HttpServletResponse response) {
		ImageFile imageFile = fileService.getImageFile(fileId);
		
		File saveFile = new File(imageFile.getSaveFileName());

		try (FileInputStream fileInputStream = new FileInputStream(rootPath + imageFile.getSaveFileName());
			OutputStream outputStream = response.getOutputStream();) {
			fileInputStream.transferTo(outputStream);
			
			response.setHeader("Content-Disposition", "attachment; filename=\"" + imageFile.getFileName() + "\";");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Content-Type", imageFile.getContentType());
			response.setHeader("Content-Length", Long.toString(saveFile.length()));
			response.setHeader("Pragma", "no-cache;");
			response.setHeader("Expires", "-1;");

		} catch (IOException exception) {
			throw new FileLoadException(exception);
		}
	}
}
