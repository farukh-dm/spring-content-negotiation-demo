package com.demo.spring.exception.handler;

import com.demo.spring.exception.beans.ExceptionMessageDto;

public interface AppExceptionHandler {
	
	public Object defaultExceptionHandler();
	
	public Object htmlRequestExceptionHandler(ExceptionMessageDto exceptionMsgDto);
	
	public Object jsonRequestExceptionHandler(ExceptionMessageDto exceptionMsgDto);
	
	public Object xmlRequestExceptionHandler(ExceptionMessageDto exceptionMsgDto);
	
	public Object xlsxRequestExceptionHandler(ExceptionMessageDto exceptionMsgDto);
	
	public Object pdfRequestExceptionHandler(ExceptionMessageDto exceptionMsgDto);
	
	public Object handle(Exception exception, Object producibleMediaTypesObj);

	public ExceptionMessageDto buildExceptionMessageDto(Exception exception);

}
