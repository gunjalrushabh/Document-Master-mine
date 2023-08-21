package com.alphaware.documentmaster.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.alphaware.documentmaster.entity.FileEntity;
import com.alphaware.documentmaster.exception.InvalidUuidException;
import com.alphaware.documentmaster.response.FileUploadingResponse;

public interface FileService {

	FileUploadingResponse uploadFileToFileSystem(MultipartFile file) throws IOException;
	
	FileEntity downloadFileFromFileSystem(String uuid) throws IOException, InvalidUuidException ;
}
