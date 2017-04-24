package com.han.service;

import java.util.ArrayList;

import com.han.domain.User;

public class UserService {
	//�����Ϸ��û�������
	private static ArrayList<User> userList=new ArrayList<User>();
	private static User user1=new User(1+"",1+"");
	private static User user2=new User(2+"",2+"");
	private static User user3=new User(3+"",3+"");
	//����û�
	private static void add(){
		userList.add(user1);
		userList.add(user2);
		userList.add(user3);
	}
	
	//����û��Ƿ�Ϸ�
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
	
	//�ж��Ƿ���������ͬʱ׼��,��Ϊ�������˷���λ��,������������,���򷵻�null
	public static User[] checkDesk(ArrayList<User> list, int preweizhi){
		User user = null;
		int count = 0;
		User[] users=new User[3];
		for (int i = 0; i < list.size(); i++) {
			user = list.get(i);
//			user.setFlag("b");
			if("b".equals(user.getFlag())){
				user.preweizhi = preweizhi;
				users[count] = user;
				count++;
				if(count==3){
					return users;
				}
			}
		}
		return null;
	}
	//�ж�����������Ƿ����˵����Ų�����-1,���Ѿ���������
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
	
	
	//�жϵ�ǰ�����Ƿ��д��û�
	public static boolean checkUserOnPredesk(User[] users,User user){
		for (int i = 0; i < users.length; i++) {
			User u=users[i];
			if(user.getName().equals(u.getName())){
				return true;
			}
		}
		return false;
	}
	
	//����ǰ�����е������û���״̬��Ϊc
	public static void setFlag(User[] users){
		for (int i = 0; i < users.length; i++) {
			users[i].setFlag("c");
		}
	}
	//������һ�����û������Ÿ�ֵ���������û�
	public static void setDeskNo(User[] users,int deskNo){
		for (int i = 0; i < users.length; i++) {
			users[i].deskNo=deskNo;
		}
	}
	
	//�õ���ҵ���������
	public static String[] getNames(User[] preUser){
		String[] names = new String[3];
		for (int i = 0; i < preUser.length; i++) {
			User u = preUser[i];
			names[i] = u.getName();
		}
		return names;
	}
	
}
