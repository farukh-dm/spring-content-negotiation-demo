/**
 * 
 */
package com.demo.spring.Dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author d_farukh
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee {
	
	@XmlElement
	private String code;
	
	@XmlElement
	private String name;
	
	public Employee() {
	}
	
	public Employee(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
