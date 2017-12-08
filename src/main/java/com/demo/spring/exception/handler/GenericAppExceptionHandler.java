package com.demo.spring.exception.handler;

import java.util.Iterator;
import java.util.LinkedHashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.demo.spring.common.AppConstant;
import com.demo.spring.exception.beans.ExceptionMessageDto;
import com.demo.spring.exception.view.excel.ExceptionExcelView;
import com.demo.spring.exception.view.pdf.ExceptionPdfView;

@Component(value="genericAppExceptionHandler")
public class GenericAppExceptionHandler implements AppExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GenericAppExceptionHandler.class);
	
	@Override
	public Object defaultExceptionHandler() {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(AppConstant.GENERIC_ERROR_MESSAGE);
	}

	@Override
	public Object jsonRequestExceptionHandler(ExceptionMessageDto exceptionMsgDto) {
		return new ResponseEntity<ExceptionMessageDto>(
			exceptionMsgDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public Object xmlRequestExceptionHandler(ExceptionMessageDto exceptionMsgDto) {
		return new ResponseEntity<ExceptionMessageDto>(
			exceptionMsgDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public Object xlsxRequestExceptionHandler(ExceptionMessageDto exceptionMsgDto) {
		
		return new ExceptionExcelView(exceptionMsgDto);
		
	}

	@Override
	public Object pdfRequestExceptionHandler(ExceptionMessageDto exceptionMsgDto) {
		
		return new ExceptionPdfView(exceptionMsgDto);
		
	}
	
	@Override
	public Object htmlRequestExceptionHandler(
		ExceptionMessageDto exceptionMsgDto) {
		
		ModelAndView model = new ModelAndView("error");
		model.addObject("exceptionMsgDto", exceptionMsgDto);
		return model;
		
	}

	@Override
	public Object handle(Exception exception, Object producibleMediaTypesObj) {
		
		String producibleMediaType = null;
		ExceptionMessageDto exceptionMsgDto = null;
		
		try {
			
			LOGGER.debug("handle: start");
			
			LOGGER.error("Exception: ", exception);
			
			// Build exception messagge dto.
			exceptionMsgDto = buildExceptionMessageDto(exception);
			
			producibleMediaType = getProducibleMediaType(producibleMediaTypesObj);
				
			if(producibleMediaType.equals(AppConstant.MEDIA_TYPE_JSON)) {
				
				// Media Type: JSON
				return jsonRequestExceptionHandler(exceptionMsgDto);
				
			} else if(producibleMediaType.equals(AppConstant.MEDIA_TYPE_XML)) {
				
				// Media Type: XML
				return xmlRequestExceptionHandler(exceptionMsgDto);
				
			} else if(producibleMediaType.equals(AppConstant.MEDIA_TYPE_XLS)) {
				
				// Media Type: XLSX
				return xlsxRequestExceptionHandler(exceptionMsgDto);
				
			} else if(producibleMediaType.equals(AppConstant.MEDIA_TYPE_PDF)) {
				
				// Media Type: PDF
				return pdfRequestExceptionHandler(exceptionMsgDto);
				
			} else {
				
				// (Assuming/Default) Media Type as: HTML
				return htmlRequestExceptionHandler(exceptionMsgDto);
			}
			
		} catch(Exception e) {
			
			// This should never happen. But in case it happens it will send something.
			LOGGER.error("Exception in handle " , e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(AppConstant.GENERIC_ERROR_MESSAGE);
		}

	}

	@Override
	public ExceptionMessageDto buildExceptionMessageDto(Exception exception) {
		
		ExceptionMessageDto exceptionMsgDto = new ExceptionMessageDto();
		// Build your exception DTO. We can do more here.
		exceptionMsgDto.setMessage(exception.getMessage());
		return exceptionMsgDto;
	}

	@SuppressWarnings("unchecked")
	private String getProducibleMediaType(
		Object producibleMediaTypesObj) {
		
		String producibleMediaType = null;
		
		if(null != producibleMediaTypesObj) {
			
			LinkedHashSet<Object> producibleMediaTypesSet = (LinkedHashSet<Object>) producibleMediaTypesObj;
			Iterator<Object> pmtIt = producibleMediaTypesSet.iterator();
			while(pmtIt.hasNext()) {
				Object obj = pmtIt.next();
				producibleMediaType = obj.toString();
				// break; // it's only one value in the set anyways :)
			}
		} else {
			
			// Else set html template by default, if media type is null
			producibleMediaType = AppConstant.MEDIA_TYPE_HTML;
		}
		
		return producibleMediaType;
		
	}


}
