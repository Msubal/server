package kr.ac.hansung.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Student {
	
	private int num;
	
	//@Min(value=0, message="The Student id must not be less than zero")
	private int id;
	
	//@Min(value=0, message="The Student password must not be less than zero")
	private int password;
	
	//@NotEmpty(message="The Student name must not be null")
	private String name;
	
	//@NotEmpty(message="The Student check must not be null")
	private int check;
	
}
