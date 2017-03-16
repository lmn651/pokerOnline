package com.han.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.han.domain.Pai;

public class PaiService {

	//准备好一整副扑克牌
	public static LinkedList<Pai> getPoke(){
		LinkedList<Pai> paiList=new LinkedList<Pai>();
		for (int huaSe = 1; huaSe < 5; huaSe++) {
			for (int daXiao = 3; daXiao < 16; daXiao++) {
				Pai pai=new Pai(huaSe+"", daXiao+"",daXiao);			
				paiList.add(pai);
			}
		}
		//大王
		Pai da=new Pai(5+"", 17+"",17);
		//小王
		Pai xiao=new Pai(5+"", 16+"",16);
		paiList.add(da);
		paiList.add(xiao);
		return paiList;
	}	
	//打乱一整副牌,并分为四份
	public static LinkedList<LinkedList<String>> daLuan(LinkedList<Pai> paiList){
		LinkedList<LinkedList<String>> zongPai=new LinkedList<LinkedList<String>>();
		Collections.shuffle(paiList);
		//玩家0的牌
		LinkedList<Pai> list0=new LinkedList<Pai>();
		
		for (int i = 0; i < 17; i++) {
			list0.add(paiList.pop());
		}
		//对玩家0的牌进行排序
		paiSort(list0);
		String path=null;
		
		//玩家1的牌
		LinkedList<Pai> list1=new LinkedList<Pai>();
		
		for (int i = 0; i < 17; i++) {
			list1.add(paiList.pop());
		}
		//对玩家1的牌进行排序
		paiSort(list1);
		
		//玩家2的牌
		LinkedList<Pai> list2=new LinkedList<Pai>();	
		for (int i = 0; i < 17; i++) {		
			list2.add(paiList.pop());
		}
		//对玩家2的牌进行排序
		paiSort(list2);
		
		//底牌
		LinkedList<Pai> list3=new LinkedList<Pai>();
		for(int i=0;i<3;i++){
			list3.add(paiList.pop());
		}
		//给玩家0发牌
		LinkedList<String> list00=new LinkedList<String>();
		for (int i = 0; i < 17; i++) {
			Pai pai=list0.pop();
			path="images/poke/"+pai.getDaXiao()+"-"+pai.getHuaSe()+".gif";
			list00.add(path);
		}
		zongPai.add(list00);
		//给玩家1发牌
		LinkedList<String> list11=new LinkedList<String>();
		for (int i = 0; i < 17; i++) {
			Pai pai=list1.pop();
			path="images/poke/"+pai.getDaXiao()+"-"+pai.getHuaSe()+".gif";
			list11.add(path);
		}
		zongPai.add(list11);
		//给玩家2发牌
		LinkedList<String> list22=new LinkedList<String>();
		for (int i = 0; i < 17; i++) {
			Pai pai=list2.pop();
			path="images/poke/"+pai.getDaXiao()+"-"+pai.getHuaSe()+".gif";
			list22.add(path);
		}
		zongPai.add(list22);
		//给底牌发牌
		LinkedList<String> list33=new LinkedList<String>();
		for(int i=0;i<3;i++){
			Pai pai=list3.pop();
			path="images/poke/"+pai.getDaXiao()+"-"+pai.getHuaSe()+".gif";
			list33.add(path);
		}
		zongPai.add(list33);
		return zongPai;		
	}
	//给玩家的牌进行排序
	public static void paiSort(List<Pai> list){
		Collections.sort(list);
	}
	
	
}
