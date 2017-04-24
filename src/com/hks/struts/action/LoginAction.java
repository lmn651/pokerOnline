/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.hks.struts.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.han.domain.Pai;
import com.han.domain.User;
import com.han.service.UserService;
import com.hks.struts.form.UserForm;

/**
 * MyEclipse Struts Creation date: 02-08-2017
 * 
 * XDoclet definition:
 * 
 * @struts.action path="/login" name="userForm" scope="request"
 */
public class LoginAction extends Action {
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
		User u = (User) request.getSession().getAttribute("user");
		if(u != null){
			//清除该用户的游戏信息
			u.setFlag("a");
			u.deskNo = -1;
			u.weizhi = -1;
			return mapping.findForward("loginok");
		}
		UserForm userForm = (UserForm) form;// TODO Auto-generated method stub
			
		String name = userForm.getName();
		String pwd = userForm.getPassword();
		User user = new User();
		user.setName(name);
		user.setPassword(pwd);		
//		if ((name.equals("1") && pwd.equals("1"))
//				|| ((name.equals("2") && pwd.equals("2")) || ((name.equals("3") && pwd
//						.equals("3"))))) {
		if(UserService.checkUser(user) ){
			request.getSession().setAttribute("user", user);
			
			return mapping.findForward("loginok");
		} else {
			return mapping.findForward("err");
		}
	}
}