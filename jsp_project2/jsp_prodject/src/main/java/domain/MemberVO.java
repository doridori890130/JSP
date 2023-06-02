package domain;

public class MemberVO {
//		Id varchar(100) not null,
//		password varchar(100) not null,
//		email varchar(100) not null,
//		age int,
//		reg_date datetime default now(),
//		last_login datetime default null,
//		Primary key(id)); 
	
	private String id;
	private String password;
	private String email;
	private int age;
	private String reg_date;
	private String last_login;
	
	public MemberVO() {}
	
	//login
	public MemberVO(String id ,String password) {
		this.id=id;
		this.password=password;
	}
	//join
	public MemberVO(String id, String password, String email, int age) {
		this.id=id;
		this.password=password;
		this.email=email;
		this.age=age;
	}
	
	//list : id, email, age, regdate,lastlogin
	public MemberVO(String id, int age, String email, String reg_date, String last_login) {
		this.id=id;
		this.age=age;
		this.email=email;
		this.reg_date=reg_date;
		this.last_login=last_login;
	}
	
	
	//modify : pw,email, age
	public MemberVO(String password, String email, int age) {
		this.password=password;
		this.email=email;
		this.age=age;
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
	
	
	
}
