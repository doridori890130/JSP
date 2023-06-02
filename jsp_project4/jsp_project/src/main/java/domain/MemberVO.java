package domain;

public class MemberVO {
//	create table meminfo(
//			mno int auto_increment,
//			id varchar(100),
//			password varchar(100),
//			email varchar(100),
//			age int,
//			reg_date datetime default now(),
//			last_login datetime default null,
//			primary key(mno));
	private int mno;
	private String id;
	private String password;
	private String email;
	private int age;
	private String reg_date;
	private String last_login;
	
	public MemberVO() {}
	//resister (id, passwor, email, age)
	public MemberVO(String id ,String password, String email, int age) {
		this.id=id;
		this.password=password;
		this.email=email;
		this.age=age;
	}
	
	// login
	public MemberVO(String id, String password) {
		this.id=id;
		this.password=password;
	}
	
	//list(id, email, age, reg_date, last_login)
	public MemberVO(String id, String email,int age,String reg_date , String last_login) {
		this.id=id;
		this.email=email;
		this.age=age;
		this.reg_date=reg_date;
		this.last_login=last_login;	
	}
	//modify (password, age, email)
	public MemberVO(String password, int age, String email) {
		this.password=password;
		this.age=age;
		this.email=email;
	}
	public MemberVO(String id, String password, String email, int age, String reg_date, String last_login) {
		
		this.id = id;
		this.password = password;
		this.email = email;
		this.age = age;
		this.reg_date = reg_date;
		this.last_login = last_login;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getLast_login() {
		return last_login;
	}
	public void setLast_login(String last_login) {
		this.last_login = last_login;
	}
	@Override
	public String toString() {
		return "MemberVo [mno=" + mno + ", id=" + id + ", password=" + password + ", email=" + email + ", age=" + age
				+ ", reg_date=" + reg_date + ", last_login=" + last_login + "]";
	}
	
}
