package kr.ac.hansung.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Student {
	
	private int num;
	
	//@Min(value=0, message="The Student id must not be less than zero")
	@JsonProperty("id")
	private int id;
	
	//@Min(value=0, message="The Student password must not be less than zero")
	@JsonProperty("password")
	private int password;
	
	//@NotEmpty(message="The Student name must not be null")
	@JsonProperty("name")
	private String name;
	
	//@NotEmpty(message="The Student check must not be null")
	@JsonProperty("check")
	private int check;
	
}
