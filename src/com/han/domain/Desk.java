package com.han.domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Desk {
	//桌号为User[]在gameList中的位置
	private int deskNo;
	public int getDeskNo() {
		return deskNo;
	}
	public void setDeskNo(int deskNo) {
		this.deskNo = deskNo;
	}
	//当前桌子的状态
	public boolean isPai=false;
	//存放当前桌子上的用户
	public User[] userOnDesk=new User[3];
	
	//根据桌子上的位置分配牌(其中0,1,2为玩家的牌,diPaiList为剩的三张牌)
	public LinkedList<String> diPaiList=null;
	public LinkedList<String>[] pais=new LinkedList[3];
}
