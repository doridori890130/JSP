package com.myproject.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserVO {


	private String id;
	private String pw;
	private String name;
	private String email;
	private String adress;
	private String reg_date;
	private int age;
}
