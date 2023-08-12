package com.alphaware.documentmaster.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.alphaware.documentmaster.entity.FileEntity;
import com.alphaware.documentmaster.exception.InvalidUuidException;
import com.alphaware.documentmaster.repository.FileRepository;
import com.alphaware.documentmaster.response.FileUploadingResponse;


@Service
@Transactional
public class FileServiceImpl implements FileService{

	@Value("${file.upload-dir}")
	public  String UPLOAD_DIR ;
	//	public final String UPLOAD_DIR = "C:\\Rushabh 19\\Document Master\\Document-Master\\target\\data";
//	public final String UPLOAD_DIR = new ClassPathResource("static").getFile().getAbsolutePath(); // for dynamic file
																									// path
																									// target/static/
	
//	public FileServiceImpl() throws IOException {
//
//	}

	@Autowired
	private FileRepository repo;

   
	public FileUploadingResponse uploadFileToFileSystem(MultipartFile file) throws IOException {

		String originalName = file.getOriginalFilename(); // modification
		String filePath = UPLOAD_DIR + "/";

		filePath = filePath 
				+ LocalDate.now().getYear() + "/" 
				+ LocalDate.now().getMonthValue() + "/"
				+ LocalDate.now().getDayOfMonth();


		File newfolder = new File(filePath);
		if (!newfolder.exists()) {
			newfolder.mkdirs();
		}
		
		String fileUniqueUuid = UUID.randomUUID().toString();  // modification
		String renamedFile = fileUniqueUuid+"_"+file.getOriginalFilename(); //modification
		
	//	filePath = filePath + "/" + file.getOriginalFilename();
		filePath = filePath + "/" + renamedFile;  //modification

		FileEntity fileobj = repo.save(FileEntity.builder()
				.file_name(renamedFile)
				.file_type(file.getContentType()) 
				.created_at(LocalDate.now()).modified_at(LocalDate.now()).fileUuid(fileUniqueUuid)
				.filepath(filePath).status("file uploaded  at: " + LocalDate.now()).data(file.getBytes()).build());
		
		file.transferTo(new File(filePath)); 

		String fileResponseStatus = "File :"+fileobj.getFile_name()+" uploaded Successfully: ";
		
		if (fileobj != null) {
			FileUploadingResponse fileResponse = new FileUploadingResponse(fileobj.getFilepath(), fileobj.getFileUuid(), fileResponseStatus);
			return fileResponse;
		}
		
		return null;
	}

	// final modification---------------------------
	public byte[] downloadFileFromFileSystem(String uuid) throws IOException, InvalidUuidException  {
		
		Optional<FileEntity> optionalFile = repo.findByFileUuid(uuid);
		
		if(optionalFile.isEmpty()) {
			throw new InvalidUuidException("This UUID is not present in the database");
		}
			String filePath = optionalFile.get().getFilepath();
			optionalFile.get().setStatus("File is downloade on " + LocalDate.now());
			optionalFile.get().setModified_at(LocalDate.now());
			byte[] filedata = Files.readAllBytes(new File(filePath).toPath());
			return filedata;
		
	}

}
