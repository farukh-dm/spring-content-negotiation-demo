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
public class User {
	
	@XmlElement
	private String email;
	
	@XmlElement
	private String name;
	
	public User() {
	}
	
	public User(String email, String name) {
		super();
		this.email = email;
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
