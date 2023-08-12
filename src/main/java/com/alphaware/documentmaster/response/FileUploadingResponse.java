package com.alphaware.documentmaster.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@Getter
@Setter

public class FileUploadingResponse {

	private String responseFilePath;
	private String responseUuid;
	private String status;
	
	public FileUploadingResponse(String responseFilePath, String responseUuid, String status) {
		
		this.responseFilePath = responseFilePath;
		this.responseUuid = responseUuid;
		this.status = status;
	}

	public FileUploadingResponse(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "FileResponse [responseFilePath=" + responseFilePath + ", responseUuid=" + responseUuid + ", status="
				+ status + "]";
	}
	
	

}
