package com.han.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.han.domain.Pai;

public class PaiService {

	//׼����һ�����˿���
	public static LinkedList<Pai> getPoke(){
		LinkedList<Pai> paiList=new LinkedList<Pai>();
		for (int huaSe = 1; huaSe < 5; huaSe++) {
			for (int daXiao = 3; daXiao < 16; daXiao++) {
				Pai pai=new Pai(huaSe+"", daXiao+"",daXiao);			
				paiList.add(pai);
			}
		}
		//����
		Pai da=new Pai(5+"", 17+"",17);
		//С��
		Pai xiao=new Pai(5+"", 16+"",16);
		paiList.add(da);
		paiList.add(xiao);
		return paiList;
	}	
	//����һ������,����Ϊ�ķ�
	public static LinkedList<LinkedList<String>> daLuan(LinkedList<Pai> paiList){
		LinkedList<LinkedList<String>> zongPai=new LinkedList<LinkedList<String>>();
		Collections.shuffle(paiList);
		//���0����
		LinkedList<Pai> list0=new LinkedList<Pai>();
		
		for (int i = 0; i < 17; i++) {
			list0.add(paiList.pop());
		}
		//�����0���ƽ�������
		paiSort(list0);
		String path=null;
		
		//���1����
		LinkedList<Pai> list1=new LinkedList<Pai>();
		
		for (int i = 0; i < 17; i++) {
			list1.add(paiList.pop());
		}
		//�����1���ƽ�������
		paiSort(list1);
		
		//���2����
		LinkedList<Pai> list2=new LinkedList<Pai>();	
		for (int i = 0; i < 17; i++) {		
			list2.add(paiList.pop());
		}
		//�����2���ƽ�������
		paiSort(list2);
		
		//����
		LinkedList<Pai> list3=new LinkedList<Pai>();
		for(int i=0;i<3;i++){
			list3.add(paiList.pop());
		}
		//�����0����
		LinkedList<String> list00=new LinkedList<String>();
		for (int i = 0; i < 17; i++) {
			Pai pai=list0.pop();
			path="images/poke/"+pai.getDaXiao()+"-"+pai.getHuaSe()+".gif";
			list00.add(path);
		}
		zongPai.add(list00);
		//�����1����
		LinkedList<String> list11=new LinkedList<String>();
		for (int i = 0; i < 17; i++) {
			Pai pai=list1.pop();
			path="images/poke/"+pai.getDaXiao()+"-"+pai.getHuaSe()+".gif";
			list11.add(path);
		}
		zongPai.add(list11);
		//�����2����
		LinkedList<String> list22=new LinkedList<String>();
		for (int i = 0; i < 17; i++) {
			Pai pai=list2.pop();
			path="images/poke/"+pai.getDaXiao()+"-"+pai.getHuaSe()+".gif";
			list22.add(path);
		}
		zongPai.add(list22);
		//�����Ʒ���
		LinkedList<String> list33=new LinkedList<String>();
		for(int i=0;i<3;i++){
			Pai pai=list3.pop();
			path="images/poke/"+pai.getDaXiao()+"-"+pai.getHuaSe()+".gif";
			list33.add(path);
		}
		zongPai.add(list33);
		return zongPai;		
	}
	//����ҵ��ƽ�������
	public static void paiSort(List<Pai> list){
		Collections.sort(list);
	}
	
	
}
