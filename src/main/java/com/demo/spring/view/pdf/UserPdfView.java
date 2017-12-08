/**
 * 
 */
package com.demo.spring.view.pdf;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.spring.Dto.User;
import com.demo.spring.view.pdf.abstractView.AbstractCustomPdfView;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @author d_farukh
 *
 */
public class UserPdfView extends AbstractCustomPdfView {

	@SuppressWarnings("unused")
	@Override
	protected void buildPdfDocument(Map<String, Object> model,
		Document document, PdfWriter writer, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		
		User user = (User) model.get("user");
		
		// Fonts
        Font fontTitle = new Font(FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.BLACK);
        Font fontTag = new Font(FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.WHITE);
        
        // 1.Name
        document.add(new Chunk("Name: "));
        Chunk name = new Chunk(user.getName(), fontTitle);
        document.add(name);
        document.add(new Chunk(" "));
		
        // -- newline
        document.add(Chunk.NEWLINE);
        
        // 1.Email
        document.add(new Chunk("Email: "));
        Chunk email = new Chunk(user.getEmail(), fontTitle);
        document.add(email);
        document.add(new Chunk(" "));
        
        // -- newline
        document.add(Chunk.NEWLINE);
        
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"user.pdf\""));
        
	}

}
