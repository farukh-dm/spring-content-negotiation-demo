package com.demo.spring.exception.view.excel;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.spring.common.AppConstant;
import com.demo.spring.exception.beans.ExceptionMessageDto;
import com.demo.spring.view.excel.abstractView.AbstractCustomExcelView;

public class ExceptionExcelView extends AbstractCustomExcelView {
	
	public final HSSFCellStyle NO_STYLE = null;
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionExcelView.class);
	
	private ExceptionMessageDto exceptionMessageDto;
	
	public ExceptionExcelView() {
		super();
	}

	public ExceptionExcelView(ExceptionMessageDto exceptionMessageDto) {
		super();
		this.exceptionMessageDto = exceptionMessageDto;
	}

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
		Workbook workbook, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

		//ExceptionMessageDto exceptionMsgDto = (ExceptionMessageDto) model.get("exceptionMessageDto");
		
		if(null == exceptionMessageDto) {
			exceptionMessageDto = new ExceptionMessageDto();
			exceptionMessageDto.setMessage(AppConstant.GENERIC_ERROR_MESSAGE);
		}
        
        // create sheet
        Sheet sheet = workbook.createSheet("ExceptionMessage");
        
        Row row = null;
        Cell cell = null;
        int r = 0;
        int c = 0;
 
        //Style for header cell
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.index);
        
        // Create header cells
        row = sheet.createRow(r++);
        
        cell = row.createCell(c++);
        cell.setCellStyle(style);
        cell.setCellValue("No.");
 
        cell = row.createCell(c++);
        cell.setCellStyle(style);
        cell.setCellValue("Error");
        
        row = sheet.createRow(r++);
        row.createCell(0).setCellValue(1);
        row.createCell(1).setCellValue(exceptionMessageDto.getMessage());
 
        //response.setContentLength(workbook.getBytes().length);
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"Error.xlsx\""));
        //response.setContentType("application/octet-stream");
		
	}

}

