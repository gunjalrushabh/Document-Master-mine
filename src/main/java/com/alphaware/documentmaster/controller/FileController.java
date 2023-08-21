package com.alphaware.documentmaster.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
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

import com.alphaware.documentmaster.entity.FileEntity;
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
		
//		if(!file.getContentType().equals(MediaType.IMAGE_PNG_VALUE) && !file.getContentType().equals(MediaType.IMAGE_JPEG_VALUE)) {
//			throw new FileIsInvalidException("Please select Image PNG or JPEG file only");
//		}
			FileUploadingResponse uploadFileResponse = fileService.uploadFileToFileSystem(file);
			System.out.println(uploadFileResponse);
			return ResponseEntity.status(HttpStatus.OK).body(uploadFileResponse);
			
	}
	
	@GetMapping("/fileDownloading/{uuid}")
		public ResponseEntity<?> downloadFileFromFilSystem(@PathVariable String uuid) throws  IOException, InvalidUuidException {
		
		if(uuid.isBlank()) {
			throw new InvalidUuidException("Please provide UUID it can not blank or null");
		}
//		byte [] filedate = null;
//		    filedate = fileService.downloadFileFromFileSystem(uuid);
//		
//		return ResponseEntity.status(HttpStatus.OK)
//				.contentType(MediaType.IMAGE_PNG)
//				.contentType(MediaType.IMAGE_JPEG)
//				//.contentType(MediaType.APPLICATION_PDF)
//				.body(filedate);
		
	   FileEntity uploadedFile = fileService.downloadFileFromFileSystem(uuid);

	    if (uploadedFile != null) {
	        ByteArrayResource resource = new ByteArrayResource(uploadedFile.getData());

	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentDispositionFormData("attachment", uploadedFile.getFile_name());

	        String fileType = uploadedFile.getFile_type();
	        MediaType mediaType = null;

	        if ("image/png".equals(fileType)) {
	            mediaType = MediaType.IMAGE_PNG;
	        } else if ("image/jpeg".equals(fileType)) {
	            mediaType = MediaType.IMAGE_JPEG;
	        } else if ("application/pdf".equals(fileType)) {
	            mediaType = MediaType.APPLICATION_PDF;
	        } else if ("application/vnd.openxmlformats-officedocument.wordprocessingml.document".equals(fileType)) {
	            mediaType = MediaType.APPLICATION_OCTET_STREAM; // DOCX is a binary format
	        } else if ("text/plain".equals(fileType)) {
	            mediaType = MediaType.TEXT_PLAIN;
	        } else if ("video/mp4".equals(fileType)) {
	            mediaType = MediaType.valueOf("video/mp4");
	        } else {
	           
	            mediaType = MediaType.APPLICATION_OCTET_STREAM;
	        }

	        headers.setContentType(mediaType);

	        return ResponseEntity.ok()
	            .headers(headers)
	            .body(resource);
	    } else {
	        throw new FileIsInvalidException("downloading file type is not pdf, png, jpeg, text");
	    }
		
		
		
	}
}
