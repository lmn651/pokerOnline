package com.han.service;

import java.util.ArrayList;

import com.han.domain.User;

public class UserService {
	//创建合法用户的容器
	private static ArrayList<User> userList=new ArrayList<User>();
	private static User user1=new User(1+"",1+"");
	private static User user2=new User(2+"",2+"");
	private static User user3=new User(3+"",3+"");
	//添加用户
	private static void add(){
		userList.add(user1);
		userList.add(user2);
		userList.add(user3);
	}
	
	//检查用户是否合法
	public static boolean checkUser(User user){
		add();
		for (int i = 0; i < userList.size(); i++) {
			User u=userList.get(i);
			if((u.getName().equals(user.getName()))&&(u.getPassword().equals(user.getPassword()))){
				return true;
			}
		}
		return false;
	}
	
	//判断是否有三个人同时准备,并为这三个人分配位置,返回这三个人,否则返回null
	public static User[] checkDesk(ArrayList<User> list){
		User user=null;
		int count=0;
		User[] users=new User[3];
		for (int i = 0; i < list.size(); i++) {
			user=list.get(i);
			if(user.getFlag().equals("b")){
				users[count]=user;
				count++;
				if(count==3){
					return users;
				}
			}
		}
		return null;
	}
	//判断玩家数组中是否有人的桌号不等于-1,即已经有了桌号
	public static boolean checkDeskNo(User[] users){
		for (int i = 0; i < users.length; i++) {
			if(users[i].deskNo!=-1){
				users[0].deskNo=users[i].deskNo;
				users[1].deskNo=users[i].deskNo;
				users[2].deskNo=users[i].deskNo;
				return true;
			}
		}
		
		return false;
	}
	
	
	//判断当前桌上是否有此用户
	public static boolean checkUserOnPredesk(User[] users,User user){
		for (int i = 0; i < users.length; i++) {
			User u=users[i];
			if(user.getName().equals(u.getName())){
				return true;
			}
		}
		return false;
	}
	
	//将当前数组中的三个用户的状态改为c
	public static void setFlag(User[] users){
		for (int i = 0; i < users.length; i++) {
			users[i].setFlag("c");
		}
	}
	//将坐在一桌的用户的桌号赋值给这三个用户
	public static void setDeskNo(User[] users,int deskNo){
		for (int i = 0; i < users.length; i++) {
			users[i].deskNo=deskNo;
		}
	}
	
}
