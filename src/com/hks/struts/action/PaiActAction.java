/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.hks.struts.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.han.domain.Desk;
import com.han.domain.Pai;
import com.han.domain.User;
import com.han.service.PaiService;

/** 
 * MyEclipse Struts
 * Creation date: 03-02-2017
 * 
 * XDoclet definition:
 * @struts.action
 */
public class PaiActAction extends Action {
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
		//得到地主的手牌及底牌
		//PrintWriter out = response.getWriter();
		User user=(User) request.getSession().getAttribute("user");
		String path=request.getParameter("path");
		//path的首位是玩家的位置
		String[] paiPath=path.split("link");
		int weizhi=Integer.parseInt(paiPath[0]);
		ArrayList<Pai> diZhuPai=new ArrayList<Pai>();
		for (int i = 1; i < paiPath.length; i++) {
			Pai pai=new Pai(paiPath[i]);
			diZhuPai.add(pai);
		}
		PaiService.paiSort(diZhuPai);
		
		LinkedList<String> paisList=new LinkedList<String>();
		for (int i = 0; i < diZhuPai.size(); i++) {
			Pai pai=diZhuPai.get(i);
			String paiSrc=pai.pathHead+pai.getDaXiao()+"-"+pai.getHuaSe()+pai.pathEnd;
			paisList.add(paiSrc);
		}
		//把牌放到地主的session中,并增加其他两个玩家的背景牌数
		//request.getSession().setAttribute("paisList", paisList);
		List<Desk> deskList = (List<Desk>) request.getServletContext().getAttribute("deskList");
		Desk desk = deskList.get(user.deskNo); 
		desk.pais[weizhi] = paisList;
		//取出服务器中存储的当前玩家的手牌数
		
		ArrayList<int[]> paiShuList=(ArrayList<int[]>) request.getServletContext().getAttribute("paiShuList");
		int[] paiShu=paiShuList.get(user.deskNo);
		paiShu[weizhi]=paisList.size();
		//request.getServletContext().setAttribute("paiShu",paiShu);
		
		//out.println(paiSrc);
		//out.close();
		return null;
	}
}