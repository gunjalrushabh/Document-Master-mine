package com.alphaware.documentmaster.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;




public class FileUploadingResponse {

	private String responseFilePath;
	private String responseUuid;
	private String status;
	
	
	public FileUploadingResponse() {
		
	}
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

	public String getResponseFilePath() {
		return responseFilePath;
	}

	public void setResponseFilePath(String responseFilePath) {
		this.responseFilePath = responseFilePath;
	}

	public String getResponseUuid() {
		return responseUuid;
	}

	public void setResponseUuid(String responseUuid) {
		this.responseUuid = responseUuid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
