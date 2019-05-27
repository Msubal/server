package kr.ac.hansung.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Otp {
	String name;
	int number;
	int day;
	int start_time;
	int end_time;

}
