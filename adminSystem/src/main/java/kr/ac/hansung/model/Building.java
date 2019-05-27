package kr.ac.hansung.model;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Building {
	
	private int num;
	
//	@NotEmpty(message = "The building name must not be null")
	private String name;
	
//	@NotEmpty(message = "The building number must not be null")
	private int number;

}
