/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.hks.struts.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.han.domain.Desk;
import com.han.domain.Pai;
import com.han.domain.User;
import com.han.service.DeskService;
import com.han.service.PaiService;
import com.han.service.UserService;

/**
 * MyEclipse Struts Creation date: 02-10-2017
 * 
 * XDoclet definition:
 * 
 * @struts.action parameter="flag"
 */
public class GameAction extends DispatchAction {
	/*
	 * Generated Methods
	 */

	/**
	 * Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		// �õ���ǰ�û�
		User user = (User) request.getSession().getAttribute("user");
		//���ж��û��Ƿ�������Ϸ�е����ˢ��
		if("c".equals(user.getFlag())){
			System.out.println(user.weizhi+"����ˢ����");
			return mapping.findForward("fapai");
		}
		
		System.out.println(user.getName()+"����gameAction"+user.getFlag());
		// �õ���ǰ��׼�����ҽ���˿�������������
		User[] preUser = (User[]) request.getAttribute("preUser");
		// �����ж�����������Ƿ�������Ѿ����䵽������
		if (user.deskNo!=-1) {
			user.setFlag("c");
			LinkedList<Desk> deskList = (LinkedList<Desk>) request.getServletContext()
					.getAttribute("deskList");
			Desk desk=deskList.get(user.deskNo);
			int weizhi=user.weizhi;
			LinkedList<String> diPaiList = desk.diPaiList;
			LinkedList<String> paisList = desk.pais[user.weizhi];
			request.getSession().setAttribute("weizhi", weizhi);
			request.getSession().setAttribute("diPaiList", diPaiList);
			request.getSession().setAttribute("paisList", paisList);
			return mapping.findForward("fapai");
		}
		LinkedList<Desk> deskList = (LinkedList<Desk>) request.getServletContext()
				.getAttribute("deskList");
		if (deskList == null) {
			deskList = new LinkedList<Desk>();
		}
		Desk desk = new Desk();
		LinkedList<Pai> paiList = PaiService.getPoke();
		LinkedList<LinkedList<String>> zongList = PaiService.daLuan(paiList);
		desk.pais[0] = zongList.pop();
		desk.pais[1] = zongList.pop();
		desk.pais[2] = zongList.pop();
		desk.diPaiList = zongList.pop();
		// �����ƺ�������е�isPai������Ϊtrue,��ʾ�Ѿ�������
		desk.isPai = true;
		// ����ǰdeskList�Ĵ�С��desk��λ��,����Ϊ����������
		desk.setDeskNo(deskList.size());
		// �������е���ҷ���λ��,�������Ÿ��������е����
		DeskService.setUserOnDesk(preUser, desk);
		//Ϊ�����е�ÿ���û�����λ��
		DeskService.setUserPosition(preUser);
		// �Ѹ��û���״̬��Ϊc,��ʾ����Ϸ��
		user.setFlag("c");
		deskList.add(desk);
		request.getServletContext().setAttribute("deskList", deskList);
		//Ϊ������Ӵ���һ����Ϣ�ռ�
		List<HashMap<String, String>> messageList=(List<HashMap<String, String>>) request.getServletContext().getAttribute("messageList");
		if(messageList==null){		
			messageList=new ArrayList<HashMap<String,String>>();
		}
		HashMap<String,String> map=new HashMap<String, String>();
		//���ż�Ϊ���map���ڵ�λ��
		messageList.add(desk.getDeskNo(), map);
		request.getServletContext().setAttribute("messageList", messageList);
		
		//Ϊ��ǰ���׼�������Լ�����
		int weizhi=user.weizhi;
		LinkedList<String> diPaiList = desk.diPaiList;
		LinkedList<String> paisList = desk.pais[user.weizhi];
		request.getSession().setAttribute("weizhi", weizhi);
		request.getSession().setAttribute("diPaiList", diPaiList);
		request.getSession().setAttribute("paisList", paisList);
		int[] paiShu=new int[]{17,17,17};
		ArrayList<int[]> paiShuList=new ArrayList<int[]>();
		paiShuList.add(user.deskNo, paiShu);
		request.getServletContext().setAttribute("paiShuList", paiShuList);
		
		return mapping.findForward("fapai");
	}
}