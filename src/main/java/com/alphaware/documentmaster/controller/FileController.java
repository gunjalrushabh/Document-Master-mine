package com.alphaware.documentmaster.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alphaware.documentmaster.exception.FileIsInvalidException;
import com.alphaware.documentmaster.exception.FileNotSelectException;
import com.alphaware.documentmaster.exception.InvalidUuidException;
import com.alphaware.documentmaster.response.FileUploadingResponse;
import com.alphaware.documentmaster.service.FileService;



@RestController
public class FileController {

	@Autowired
	private FileService fileService; //dependancy

	@PostMapping(consumes = "multipart/form-data" ,value =  "/fileUploading")   
	public ResponseEntity<FileUploadingResponse> uploadFiletoFileSystem(@RequestParam("uploadFile")MultipartFile file) throws IOException{
		if(file.isEmpty()) {
			throw new FileNotSelectException("Please select a file to be uploaded");
		}
		
		if(!file.getContentType().equals(MediaType.IMAGE_PNG_VALUE) && !file.getContentType().equals(MediaType.IMAGE_JPEG_VALUE)) {
			throw new FileIsInvalidException("Please select Image PNG or JPEG file only");
		}
			FileUploadingResponse uploadFileResponse = fileService.uploadFileToFileSystem(file);
			return ResponseEntity.status(HttpStatus.OK).body(uploadFileResponse);
			
	}
	
	@GetMapping("/fileDownloading/{uuid}")
		public ResponseEntity<?> downloadFileFromFilSystem(@PathVariable String uuid) throws  IOException, InvalidUuidException {
		
		if(uuid.isBlank()) {
			throw new InvalidUuidException("Please provide UUID it can not blank or null");
		}
		byte [] filedate = null;
		    filedate = fileService.downloadFileFromFileSystem(uuid);
		
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.IMAGE_PNG)
				.contentType(MediaType.IMAGE_JPEG) 
				.body(filedate);
		
	}
}
