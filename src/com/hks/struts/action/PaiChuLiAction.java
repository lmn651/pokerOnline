/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.hks.struts.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.han.domain.User;

/** 
 * MyEclipse Struts
 * Creation date: 02-16-2017
 * 
 * XDoclet definition:
 * @struts.action
 */
public class PaiChuLiAction extends Action {
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
		//�õ���ǰ���û�
		User user=(User) request.getSession().getAttribute("user");
		//�õ���ǰ������
		int deskNo=user.deskNo;
		ArrayList<HashMap<String,String>> messageList = (ArrayList<HashMap<String,String>>) request.getServletContext().getAttribute("messageList");
		//ÿ�ν���ʱ��֮ǰ����Ϣ���
		HashMap<String, String> messageMap = messageList.get(deskNo);
		messageMap.clear();
		
		//�õ���ǰ��ҵ�λ��
		String weizhi2=request.getParameter("weizhi");
		//�õ���ǰ��Ϸ������
		String count=request.getParameter("count");
		//�õ���ǰ��Ҫ�ĸ���
		String buyao=request.getParameter("buyao");
		//�õ���·��(��֮����link�ָ�)
		String paiPath=request.getParameter("paiPath");
		//System.out.println(paiPath+"**********");
		int weizhi=Integer.parseInt(weizhi2);
		
//		int zuoPaiShu=paiShu[(weizhi+3-1)%3];
//		int youPaiShu=paiShu[(weizhi+1)%3];
//		messageMap.put("zuoPaiShu", zuoPaiShu+"");
//		messageMap.put("youPaiShu", youPaiShu+"");
		messageMap.put("count", count);
		//�ѳ��Ƶ���ҵ�λ�ô�����
		messageMap.put("paiWeiZhi", weizhi+"");
		//���û��Ĳ�����Ǵ�����
		messageMap.put("paiPath", paiPath);
		messageMap.put("buyao", buyao);
		messageList.add(deskNo, messageMap);
		if(paiPath.contains("label")||paiPath==""){
			//˵��û����
			//request.getServletContext().setAttribute("messageList", messageList);
			//System.out.println(paiPath+"�жϳɹ�");
			return null;
		}
		//�õ�����ҵ�ǰ������	
		LinkedList<String> paiList=(LinkedList<String>) request.getSession().getAttribute("paisList");	
		
		String[] paiPaths = paiPath.split("link");
		String path="";
		for(int i=0;i<paiPaths.length;i++){
			String chu=paiPaths[i];
			//System.out.println("���Ƶ�·��"+chu);
			for (int j = 0; j < paiList.size(); j++) {
				String yuan=paiList.get(j);
				//System.out.println("ԭ�Ƶ�·��"+yuan);
				//��·���ĺ�8Ϊ��ͬ��Ϊͬһ����
				if(yuan.substring(yuan.lastIndexOf("/")).equals(chu.substring(chu.lastIndexOf("/")))){
					//System.out.println(weizhi+"����ͬ");
					paiList.remove(j);
				}
			}
			path+=chu+"link";
		}
		messageMap.put("paiPath",path);
		//���µ�ǰ��ҵ�����
		//�õ�������ҵ�����
		ArrayList<int[]> paiShuList=(ArrayList<int[]>) request.getServletContext().getAttribute("paiShuList");
		int[] paiShu=paiShuList.get(user.deskNo);
		paiShu[weizhi]=paiShu[weizhi]-paiPaths.length;
		return null;
	}
}