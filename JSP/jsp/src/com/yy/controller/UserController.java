package com.yy.controller;

import java.io.PrintWriter;

import com.opensymphony.xwork2.ActionSupport;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.cxf.transport.Session;
import org.apache.struts2.ServletActionContext;

//import org.springframework.stereotype.Controller;





//import javax.servlet.http.HttpServletRequest;
import com.yy.dto.UserDTO;

//import javax.annotation.Resource;

import com.yy.model.TbUser;
import com.yy.logic.IUserLogic;

public class UserController extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	@Resource
	private IUserLogic userLogic;
	private UserDTO userDTO;
	private TbUser userBean;
	private String newPwd1;
	
	public UserDTO getUserDTO() {
		return userDTO;
	}
	
	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	
	public TbUser getUserBean() {
		return userBean;
	}
	
	public void setUserBean(TbUser userBean) {
		this.userBean = userBean;
	}
	
	public String getNewPwd1() {
		return newPwd1;
	}
	
	public void setNewPwd1(String newPwd1) {
		this.newPwd1 = newPwd1;
	}
	
	public void loginCheck()throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();//取得reponse实例
		response.setCharacterEncoding("utf-8");//设置输出的编码
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();//得到printwriter实例
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaa");
		try{
			System.out.println("LoginName------->"+userDTO.getUserLoginName());
			System.out.println("LoginPwd-------->"+userDTO.getUserPwd());
			String result = this.userLogic.loginCheck(userDTO.getUserLoginName(), userDTO.getUserPwd());
			//out.println("yes");
			System.out.println("result---->" + result);
			out.println(result);
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}
	
	public void addUser() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();//取得reponse实例
		response.setCharacterEncoding("utf-8");//设置输出的编码
		response.setContentType("text/html;charset=utf-8");
		HttpSession sessions = request.getSession();
		PrintWriter out = response.getWriter();
		int nRet=0;
		System.out.println("********This is addUser controller********");
		try{
		
		System.out.println(userDTO.getUserSex());
		String resultadd=userLogic.addUser(userDTO);
		if(resultadd.equals("对不起，用户名已经被注册")){
			out.print("<script>alert('用户名已经被注册')</script>");
			
			sessions.setAttribute("Registeradd","失败");
		}
		else if(resultadd.equals("成功")){
			
			out.print("<script>alert('注册成功')</script>");
			sessions.setAttribute("Registeradd","成功");
			//request.getRequestDispatcher("../system/UserLogin.html").forward(request, response);
		}
		
		
		
	}
	catch (Exception e) {
	e.printStackTrace();
	}
	 finally {
		 out.flush();
		 out.close();
	 }
		
	}
}
