/**
 * 
 */
package com.demo.spring.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import com.demo.spring.Dto.Employee;
import com.demo.spring.Dto.User;
import com.demo.spring.exception.handler.AppExceptionHandler;
import com.demo.spring.view.excel.UserExcelView;
import com.demo.spring.view.pdf.UserPdfView;

/**
 * @author d_farukh
 *
 */
@Controller
@SuppressWarnings("unused")
public class HelloWorldController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldController.class);
	
	@Autowired
	AppExceptionHandler genericAppExceptionHandler;
	
	// Welcome page / Home page / Index / Landing page.
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String sayHello() {
		return "hello";
	}
	
	@RequestMapping(
		method = RequestMethod.GET, 
		value="/user/1", 
		produces={"application/xml", "application/json"})
	public @ResponseBody ResponseEntity<User>getUser(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
		
		User user = new User("demo@demo.com", "Demo123");
		return new ResponseEntity<User>(user, HttpStatus.OK);
		
	}
	
	@RequestMapping(
		method = RequestMethod.GET, 
		value="/user/1")
	public ModelAndView getUserWithView(HttpServletRequest request, HttpSession session) {
		
		User user = new User("demo@demo.com", "Demo123");
		ModelAndView mv = new ModelAndView("user/_user");
		mv.addObject("user", user);
		return mv;
		
	}
	
	// For type .xlsx
	@RequestMapping(
		method = RequestMethod.GET, 
		value="/user/1", 
		produces = {"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"})
	public UserExcelView getUserExcel(HttpServletRequest request, 
		HttpSession session, Model model) {
		
		User user = new User("demo@demo.com", "Demo123");
		model.addAttribute("user", user);
		return new UserExcelView();
		
	}
	
	// For type .pdf
	@RequestMapping(
		method = RequestMethod.GET, 
		value="/user/1", 
		produces = {"application/pdf"})
	public UserPdfView getUserPdf(HttpServletRequest request, 
		HttpSession session, Model model) {
		
		User user = new User("demo@demo.com", "Demo123");
		model.addAttribute("user", user);
		return new UserPdfView();
		
	}
	
	// In case of error the, the error response will be in the requested media format & status 500
	@RequestMapping(
		method = RequestMethod.GET, 
		value="/error", 
		produces={"application/xml", "application/json"})
	public @ResponseBody ResponseEntity<User>getError(
		HttpServletRequest request, 
		HttpSession session, 
		HttpServletResponse response) throws Exception {
		
		User user = new User("demo@demo.com", "Demo123");
		if(true) {
			throw new Exception("Generated exception in the execution");
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
		
	}
	
	@RequestMapping(
		method = RequestMethod.GET, 
		value="error")
	public ModelAndView getErrorWithView(
		HttpServletRequest request, 
		HttpSession session) throws Exception {
		
		User user = new User("demo@demo.com", "Demo123");
		ModelAndView mv = new ModelAndView("user/_user");
		mv.addObject("user", user);
		
		if(true) {
			throw new Exception("Generated exception in the execution");
		}
		
		return mv;
		
	}
	
	// For type .xlsx
	@RequestMapping(
		method = RequestMethod.GET, 
		value="/error", 
		produces = {"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"})
	public UserExcelView getErrorExcel(
		HttpServletRequest request, 
		HttpSession session, Model model) throws Exception {
		
		User user = new User("demo@demo.com", "Demo123");
		model.addAttribute("user", user);
		
		if(true) {
			throw new Exception("Generated exception in the excel execution");
		}
		
		return new UserExcelView();
		
	}
	
	// For type .pdf
	@RequestMapping(
		method = RequestMethod.GET, 
		value="/error", 
		produces = {"application/pdf"})
	public UserPdfView getErrorPdf(HttpServletRequest request, 
		HttpSession session, Model model) throws Exception {
		
		User user = new User("demo@demo.com", "Demo123");
		model.addAttribute("user", user);
		
		if(true) {
			throw new Exception("Generated exception in the pdf execution");
		}
		
		return new UserPdfView();
		
	}
	
	// Some other demonstration.
	
	@RequestMapping(method = RequestMethod.GET, value="hi")
	public String sayHi(ModelMap model) {
		model.addAttribute("greeting", "HI");
		return "hi";
	}
	
	@RequestMapping(method = RequestMethod.GET, value="hey")
	public ModelAndView sayHey(ModelAndView model) {
		model.addObject("greeting", "Hey");
		model.setViewName("hi");
		return model;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="bye")
	public ModelAndView sayBye(ModelAndView model) {
		model.addObject("greeting", "bye");
		model.setViewName("exitPage");
		return model;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="welcome")
	public ModelAndView sayWelcome(ModelAndView model) {
		model.addObject("greeting", "Welcome");
		model.setViewName("welcomePage");
		return model;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="employee/{code}/{name}")
	public ModelAndView getEmployee(
		@PathVariable("code") String code, 
		@PathVariable("name") String name) {
		
		ModelAndView model = new ModelAndView();
		Employee emp = new Employee(code, name);
		model.addObject(emp);
		model.setViewName("jsonView");
		return model;
		
	}
	
	@ExceptionHandler(value=Exception.class)
	public Object handleException(
		HttpServletRequest request, 
		HttpServletResponse response, 
		Exception exception, 
		HttpSession session) throws IOException {
		
		return genericAppExceptionHandler.handle(exception, request.getAttribute(HandlerMapping.PRODUCIBLE_MEDIA_TYPES_ATTRIBUTE));
		
	}
	
}
