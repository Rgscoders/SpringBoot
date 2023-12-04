package com.rgs.repository.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name= "USER_TL")
@Data
public class UserEntity {

	@Id
	@Column(name="USER_ID")
	@GeneratedValue
	private Integer userId;
	
	@Column(name = "FIRST_NAME",length = 50,nullable = false)
	private String firstName;
	
	@Column(name = "LAST_NAME",length = 50,nullable = false)
	private String lastName;
	
	@Column(name = "EMAIL_ID")
	private String emailId;
	
	@Column(name ="PASSWORD")
	private String password;
	
	@Column(name ="MOBILE_NUMBER")
	private String mobileNumber;
	
	
}
