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
	    //�õ���ǰ���û�
	    User user=(User) request.getSession().getAttribute("user");
	    int weizhi=user.weizhi;
	    //�õ���ǰ������
	    int deskNo=user.deskNo;
	    //�õ���ǰ�������������ҵ�������
	    ArrayList<int[]> paiShuList=(ArrayList<int[]>) request.getServletContext().getAttribute("paiShuList");
		int[] paiShu=paiShuList.get(deskNo);
		int zuoPaiShu=paiShu[(weizhi+3-1)%3];
		int youPaiShu=paiShu[(weizhi+1)%3];

		ArrayList<HashMap<String,String>> messageList =(ArrayList<HashMap<String, String>>) request.getServletContext().getAttribute("messageList");
		HashMap<String, String> messageMap = messageList.get(deskNo);
		if(messageMap.size()==0){
			return null;
		}
		//�����жϵ�ǰλ�õ�����Ƿ��Ѿ�����ȡ��
		if("1".equals(messageMap.get(weizhi+""))){
			//֤��������Ѿ������˿�����
			//�жϸ��������λ�õ�����Ƿ������˿�����ȡ��
//			int left=(weizhi+3-1)%3;
//			int right=(weizhi+1)%3;
//			if("1".equals(messageMap.get(left+""))&& "1".equals(messageMap.get(right+""))){
//				֤��������Ҷ��Լ�ȡ��������,���ʱ������ո�messageMap
//				messageMap.clear();
//			}
			return null;
		}
		
		
		String paiPath = messageMap.get("paiPath");
		String count=messageMap.get("count");
		String buyao=messageMap.get("buyao");
		//�õ����Ƶ���ҵ�λ��
		String paiWeiZhi=messageMap.get("paiWeiZhi");
		if(paiPath==""){
			//ֻ����ȥ������
			String str=count;
			out.println(str);
			out.close();
			return null;
		}
		if(paiPath.contains("label")){
			//paiPath����label֤��û���ƴ�����
			String str=count+"link"+paiPath+"link"+paiWeiZhi+"link"+buyao;
			out.println(str);
			out.close();
			return null;
		}
		//��������ƴ�����(paiPath������Ѿ���һ��link��)
		
		String strpath=paiPath+count+"link"+zuoPaiShu+"link"+youPaiShu+"link"+paiWeiZhi+"link"+buyao;
	    out.println(strpath);
	    out.close();
	    //��ʾ��ǰλ�õ�����Ѿ�ȡ����
	    messageMap.put(user.weizhi+"", "1");
		return null;
	}
}