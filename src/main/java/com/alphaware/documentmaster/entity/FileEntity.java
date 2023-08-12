package com.alphaware.documentmaster.entity;

import java.time.LocalDate;
import java.util.UUID;

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
    
}
