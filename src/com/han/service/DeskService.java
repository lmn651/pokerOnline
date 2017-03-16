package com.han.service;

import com.han.domain.Desk;
import com.han.domain.User;

public class DeskService {
	//将玩家数组录入到桌子中
	public static void setUserOnDesk(User[] user,Desk desk){
		for (int i = 0; i < user.length; i++) {
			user[i].deskNo=desk.getDeskNo();
			//desk.userOnDesk[i]=user[i];
		}
	}

	public static void setUserPosition(User[] preUser) {
		// TODO Auto-generated method stub
		for (int i = 0; i < preUser.length; i++) {
			preUser[i].weizhi=i;
		}
	}
	

	
	
}