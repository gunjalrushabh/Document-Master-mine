package com.alphaware.documentmaster.entity;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "filetable")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class FileEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long file_id;

    @Column
    private String file_name;

    @Column
    private String file_type;
    
    @Column
    private String filepath;
    
    @Column
    private LocalDate created_at;
    
    @Column
    private LocalDate modified_at;
    
    @Column
    private String status;

  // which will not to be saved at database
    @Transient
    private byte []data;
    
    @Transient
    private MultipartFile file;  //modification
    
    
    @Column
    private String fileUuid;
    
    public FileEntity(){
    	this.fileUuid= UUID.randomUUID().toString();
    }
    
	@Override
	public String toString() {
		return "FileEntity [file_id=" + file_id +
				",\n file_uuid=" + fileUuid+
				",\n file_name=" + file_name + 
				",\n file_type=" + file_type+ 
				",\n file_path=" + filepath + 
				",\n created_at=" + created_at + 
				",\n modified_at=" + modified_at
				+ ",\n status=" + status +" data: "+(data!= null ? data.length:0)+ "]";
	}
	
	public FileEntity(Long file_id, String file_name, String file_type, String filepath, LocalDate created_at,
			LocalDate modified_at, String status, byte[] data, String fileUuid) {
		
		this.file_id = file_id;
		this.file_name = file_name;
		this.file_type = file_type;
		this.filepath = filepath;
		this.created_at = created_at;
		this.modified_at = modified_at;
		this.status = status;
		this.data = data;
		this.fileUuid = fileUuid;
	}
	
	
	
	

	public FileEntity(String file_name, String file_type, String filepath, LocalDate created_at, LocalDate modified_at,
			String status, byte[] data, String fileUuid) {
		
		this.file_name = file_name;
		this.file_type = file_type;
		this.filepath = filepath;
		this.created_at = created_at;
		this.modified_at = modified_at;
		this.status = status;
		this.data = data;
		this.fileUuid = fileUuid;
	}

	public Long getFile_id() {
		return file_id;
	}

	public void setFile_id(Long file_id) {
		this.file_id = file_id;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_type() {
		return file_type;
	}

	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public LocalDate getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDate created_at) {
		this.created_at = created_at;
	}

	public LocalDate getModified_at() {
		return modified_at;
	}

	public void setModified_at(LocalDate modified_at) {
		this.modified_at = modified_at;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getFileUuid() {
		return fileUuid;
	}

	public void setFileUuid(String fileUuid) {
		this.fileUuid = fileUuid;
	}

	public MultipartFile getFile() {   // modification
		return file;
	}

	public void setFile(MultipartFile file) {   //modification
		this.file = file;
	}

	
	public void uploadFile() throws IOException {
		if(file != null && !file.isEmpty()) {
			this.file_name = file.getOriginalFilename();
			this.file_type = file.getContentType();
			this.data = file.getBytes();
			
			
		}
	}
	
	
	
    
}
