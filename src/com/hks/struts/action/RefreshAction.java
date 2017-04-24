/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.hks.struts.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.han.domain.User;

/** 
 * MyEclipse Struts
 * Creation date: 02-19-2017
 * 
 * XDoclet definition:
 * @struts.action
 */
public class RefreshAction extends Action {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws IOException 
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
	    PrintWriter out = response.getWriter();
	    //得到当前的用户
	    User user = (User) request.getSession().getAttribute("user");
	    int weizhi = user.weizhi;
	    //得到当前的桌号
	    int deskNo = user.deskNo;

		ArrayList<HashMap<String,String>> messageList = (ArrayList<HashMap<String, String>>) request.getServletContext().getAttribute("messageList");
		HashMap<String, String> messageMap = messageList.get(deskNo);
		if(messageMap.size()==0){
			return null;
		}
		//得到当前玩家左右两侧玩家的手牌数
		ArrayList<int[]> paiShuList = (ArrayList<int[]>) request.getServletContext().getAttribute("paiShuList");
		int[] paiShu = paiShuList.get(deskNo);
		int zuoPaiShu = paiShu[(weizhi+3-1)%3];
		int youPaiShu = paiShu[(weizhi+1)%3];
		//首先判断当前位置的玩家是否已经来过取牌
		if("1".equals(messageMap.get(weizhi+""))){
			return null;
		}
		//得到地主玩家位置
	    String dizhuweizhi = messageMap.get("dizhuweizhi");
		//得到当前游戏状态
		String state = messageMap.get("state");
		if("2".equals(state)){
			//说明游戏结束,只返回游戏状态
			//得到获胜玩家的位置
			String winner = messageMap.get("winner");
			out.print(state);
			out.close();
			return null;
		}
		
		String paiPath = messageMap.get("paiPath");
		String count = messageMap.get("count");
		String buyao = messageMap.get("buyao");
		//得到出牌的玩家的位置
		String paiWeiZhi = messageMap.get("paiWeiZhi");
		if(paiPath == ""){
			//只传回去轮盘数
			String str = count+"link"+dizhuweizhi;
			out.println(str);
			out.close();
			return null;
		}
		if(paiPath.contains("label")){
			//paiPath含有label证明没有牌传进来
			String str=count+"link"+dizhuweizhi+"link"+paiPath+"link"+paiWeiZhi+"link"+buyao;
			out.println(str);
			out.close();
			return null;
		}
		//只有出牌的情况,牌局的状态才有可能发生变化
		//把轮盘数拼在最后(paiPath的最后已经有一个link了)
		
		String strpath=paiPath+count+"link"+zuoPaiShu+"link"+youPaiShu+"link"+paiWeiZhi+"link"+buyao+"link"+state;
	    out.println(strpath);
	    out.close();
	    //表示当前位置的玩家已经取过牌
	    messageMap.put(user.weizhi+"", "1");
		return null;
	}
}