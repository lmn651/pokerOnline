package com.han.domain;

public class User {
	private String name;
	private String password;
	// aΪ��¼״̬,bΪ׼��״̬,cΪ��Ϸ״̬
	private String flag = "a";
	// ��¼�û���Ԥλ��,��List<User[]>�е�λ��
	public int preweizhi = -1;
	// ��¼���û��ڵڼ���������Ϸ(��gameList�е����),Ĭ��Ϊ-1
	public int deskNo = -1;
	// ��¼�û�����������ϵ�λ��(���ڷ���ʱ,�������)(����0,1,2����λ��)
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
