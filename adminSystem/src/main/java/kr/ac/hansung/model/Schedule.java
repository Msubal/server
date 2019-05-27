package kr.ac.hansung.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Schedule {

	private int num;
	
//	@NotEmpty(message = "The schedule name must not be null")
	private String name;
	
	private int number;
	
	@Min(value=1,message = "1~7 1 is sunday")
	@Max(value=7,message = "1~7 1 is sunday")
	private int day;
	
	@Min(value=1, message = "Time must be more than 1")
	@Max(value=10, message = "Time must be more than 10")
	private int time;
	
}
