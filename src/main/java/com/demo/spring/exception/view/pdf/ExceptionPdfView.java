package com.demo.spring.exception.view.pdf;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.spring.common.AppConstant;
import com.demo.spring.exception.beans.ExceptionMessageDto;
import com.demo.spring.view.pdf.abstractView.AbstractCustomPdfView;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfWriter;

public class ExceptionPdfView extends AbstractCustomPdfView {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionPdfView.class);
	
	private ExceptionMessageDto exceptionMessageDto;
	
	public ExceptionPdfView() {
		super();
	}

	public ExceptionPdfView(ExceptionMessageDto exceptionMessageDto) {
		super();
		this.exceptionMessageDto = exceptionMessageDto;
	}

	@SuppressWarnings("unused")
	@Override
	protected void buildPdfDocument(Map<String, Object> model,
		Document document, PdfWriter writer, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		
		//User user = (User) model.get("user");
		
		if(null == exceptionMessageDto) {
			exceptionMessageDto = new ExceptionMessageDto();
			exceptionMessageDto.setMessage(AppConstant.GENERIC_ERROR_MESSAGE);
		}
		
		// Fonts
        Font fontTitle = new Font(FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.BLACK);
        Font fontTag = new Font(FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.WHITE);
        
        // 1.Name
        document.add(new Chunk("Error: "));
        Chunk name = new Chunk(exceptionMessageDto.getMessage(), fontTitle);
        document.add(name);
        document.add(new Chunk(" "));
		
        // -- newline
        document.add(Chunk.NEWLINE);
              
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"Error.pdf\""));
        
	}

}
