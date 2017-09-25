/**
 * 
 */
package com.demo.spring.view.excel;

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

import com.demo.spring.Dto.User;
import com.demo.spring.view.excel.abstractView.AbstractCustomExcelView;

/**
 * @author d_farukh
 *
 */
public class UserExcelView extends AbstractCustomExcelView {
	
	public final HSSFCellStyle NO_STYLE = null;
	private static final Logger LOGGER = LoggerFactory.getLogger(UserExcelView.class);

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
		Workbook workbook, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

		User user = (User) model.get("user");
        
        // create sheet
        Sheet sheet = workbook.createSheet("User");
        
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
        cell.setCellValue("Email");
 
        cell = row.createCell(c++);
        cell.setCellStyle(style);
        cell.setCellValue("Name");
        
        row = sheet.createRow(r++);
        row.createCell(0).setCellValue(1);
        row.createCell(1).setCellValue(user.getEmail());
        row.createCell(2).setCellValue(user.getName());
 
        //response.setContentLength(workbook.getBytes().length);
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"user.xlsx\""));
        //response.setContentType("application/octet-stream");
		
	}

}
