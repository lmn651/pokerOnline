package com.han.domain;

public class Pai implements Comparable<Pai>{

	private String huaSe;
	private String daXiao;
	public int xuhao=0;   //用来排序
	public String pathHead;
	public String pathEnd=".gif";
	public Pai(String huaSe,String daXiao,int xuhao) {
		// TODO Auto-generated constructor stub
		this.huaSe=huaSe;
		this.daXiao=daXiao;	
		this.xuhao=xuhao;
	}
	//该构造方法为从客户端传入一个牌路径就生成一个Pai对象
	public Pai(String path){
		int n=path.lastIndexOf("/");
		this.pathHead=path.substring(0, n+1);
		if(path.length()-n==8){
			this.daXiao=path.substring(n+1, n+2);
			this.huaSe=path.substring(n+3, n+4);
		}else if(path.length()-n==9){
			this.daXiao=path.substring(n+1, n+3);
			this.huaSe=path.substring(n+4, n+5);
		}
		this.xuhao=Integer.parseInt(this.daXiao);
	}
	public Pai() {
		// TODO Auto-generated constructor stub
	}
	public String getHuaSe() {
		return huaSe;
	}
	public void setHuaSe(String huaSe) {
		this.huaSe = huaSe;
	}
	public String getDaXiao() {
		return daXiao;
	}
	public void setDaXiao(String daXiao) {
		this.daXiao = daXiao;
	}
	@Override
	public int compareTo(Pai o) {
		// TODO Auto-generated method stub
		if (this.xuhao<o.xuhao) {
			return 1;
		}else if (this.xuhao>o.xuhao) {
			return -1;
		}else{		
			return 0;
		}
	}	
}
