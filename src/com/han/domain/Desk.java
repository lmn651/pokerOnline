package com.han.domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Desk {
	//����ΪUser[]��gameList�е�λ��
	private int deskNo;
	public int getDeskNo() {
		return deskNo;
	}
	public void setDeskNo(int deskNo) {
		this.deskNo = deskNo;
	}
	//��ǰ���ӵ�״̬
	public boolean isPai=false;
	//��ŵ�ǰ�����ϵ��û�
	public User[] userOnDesk=new User[3];
	
	//���������ϵ�λ�÷�����(����0,1,2Ϊ��ҵ���,diPaiListΪʣ��������)
	public LinkedList<String> diPaiList=null;
	public LinkedList<String>[] pais=new LinkedList[3];
}
