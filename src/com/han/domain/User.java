package com.han.domain;

public class User {
	private String name;
	private String password;
	// a为登录状态,b为准备状态,c为游戏状态
	private String flag = "a";
	// 记录用户的预位置,在List<User[]>中的位置
	public int preweizhi = -1;
	// 记录该用户在第几桌进行游戏(即gameList中的序号),默认为-1
	public int deskNo = -1;
	// 记录用户在这个桌子上的位置(用于发牌时,方便分配)(共有0,1,2三个位置)
	public int weizhi = -1;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
