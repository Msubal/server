package kr.ac.hansung.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Otp {
	private int num;
	private String student_id;
	private int building_num;
	private int day;
	private int start_time;
	private int end_time;
	private int otp_pw;
	private String current;
	

}
