/**
 * 
 */
package com.demo.spring.view.excel;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.demo.spring.Dto.User;

/**
 * @author d_farukh
 *
 */
public class UserExcelViewOld extends AbstractExcelView {
	
	public final HSSFCellStyle NO_STYLE = null;
	private static final Logger LOGGER = LoggerFactory.getLogger(UserExcelViewOld.class);

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
		HSSFWorkbook workbook, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		
        User user = (User) model.get("user");
        
        // create sheet
        Sheet sheet = workbook.createSheet("User Xlsx");

        // create header
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("No");
        header.createCell(1).setCellValue("Email");
        header.createCell(2).setCellValue("Name");
        
        int rowCount = 1;
        Row userRow = sheet.createRow(rowCount++);
        userRow.createCell(0).setCellValue(1);
        userRow.createCell(1).setCellValue(user.getEmail());
        userRow.createCell(2).setCellValue(user.getName());
        
        response.setContentLength(workbook.getBytes().length);
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"user.xls\""));
        response.setContentType("application/octet-stream");
        
	}

}
