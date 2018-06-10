package com.theam.CRMService.crmrestapi.exceptions.internal;

public class UploadPhotoException extends RuntimeException {

	public UploadPhotoException(String fileName,long id,Exception original) {
		super(String.format("Failed to upload image %s for ID %d..Reason:%s", fileName,id,original.getMessage()));
	}
}
