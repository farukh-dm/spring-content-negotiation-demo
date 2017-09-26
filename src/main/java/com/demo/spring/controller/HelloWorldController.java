/**
 * 
 */
package com.demo.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.demo.spring.Dto.Employee;
import com.demo.spring.Dto.Person;
import com.demo.spring.Dto.User;
import com.demo.spring.view.excel.UserExcelView;
import com.demo.spring.view.pdf.UserPdfView;

/**
 * @author d_farukh
 *
 */
@Controller
@RequestMapping(value="/")
public class HelloWorldController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String sayHello() {
		return "welcome";
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
	
	@RequestMapping(method = RequestMethod.GET, value="/employee", 
		produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
	public @ResponseBody Employee getEmp() {
		
		Employee emp = new Employee("P2586", "Farukh");
		return emp;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/person", 
		produces={"application/xml", "application/json"})
	public ResponseEntity<Person>getPerson() {
		
		Person p = new Person("P2586", "Farukh");
		return new ResponseEntity<Person>(p, HttpStatus.OK);
		
	}
	
	@ExceptionHandler(value=Exception.class)
	public @ResponseBody ModelAndView handleException(Exception exception, HttpSession session) {
		ModelAndView model = new ModelAndView("error");
		model.addObject("exception", exception);
		return model;
	}
	
}
