package com.javaex.vo;

public class UserVo {

	// 필드
	private int no;
	private String name;
	private String id;
	private String password;
	private String address;
	private String hp;
	private int roll;
	
	
	public UserVo() {
		super();
	}


	public UserVo(int no, String name, String id, String password, String address, String hp, int roll) {
		super();
		this.no = no;
		this.name = name;
		this.id = id;
		this.password = password;
		this.address = address;
		this.hp = hp;
		this.roll = roll;
	}


	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
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


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getHp() {
		return hp;
	}


	public void setHp(String hp) {
		this.hp = hp;
	}


	public int getRoll() {
		return roll;
	}


	public void setRoll(int roll) {
		this.roll = roll;
	}


	@Override
	public String toString() {
		return "UserVo [no=" + no + ", name=" + name + ", id=" + id + ", password=" + password + ", address=" + address
				+ ", hp=" + hp + ", roll=" + roll + "]";
	}


	
}
	