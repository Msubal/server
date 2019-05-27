package kr.ac.hansung.model;

//import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Student {
	
	private int num;
	
//	@NotEmpty(message = "Please write a id!")
	private int id;
	
//	@NotEmpty(message = "Please write a password!")
	private int password;
	
	private String name;
	
	private String check;
}
