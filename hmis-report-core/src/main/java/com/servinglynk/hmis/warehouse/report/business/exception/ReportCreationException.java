package com.servinglynk.hmis.warehouse.report.business.exception;

public class ReportCreationException  extends RuntimeException{
	
	public static final String DEFAULT_MESSAGE = "Report Creation Exception";
	
	public ReportCreationException() {
		super(DEFAULT_MESSAGE);
	}
	
	public ReportCreationException(String message) {
		super(message);
	}
	
	public ReportCreationException(Throwable cause) {
		super(DEFAULT_MESSAGE, cause);
	}

	public ReportCreationException(String message, Throwable cause) {
		super(message, cause);
	}
	
	String errorCode;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	
	String errorGroup;

	public String getErrorGroup() {
		return errorGroup;
	}

	public void setErrorGroup(String errorGroup) {
		this.errorGroup = errorGroup;
	}
		
		

}
