package eda.dto;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class UploadedFile {
	CommonsMultipartFile file;

	public CommonsMultipartFile getFile() {
		return file;
	}

	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}
	
}
