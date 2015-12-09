package com.yy.logic.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.yy.dao.IUserDAO;
import com.yy.dto.UserDTO;
import com.yy.logic.IUserLogic;
import com.yy.model.TbUser;

@Component
public class UserLogic implements IUserLogic{
	private IUserDAO userDAO;
	
	
	public IUserDAO getUserDAO() {
		return userDAO;
	}
	
	@Resource
	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	@Override
	@Transactional
	public String loginCheck(String userLoginName, String userPwd)
			throws Exception {
		System.out.println("Logic---------------------------");
		System.out.println("name--------------->"+userLoginName+"---------------"+userPwd);
		
		String result = "对不起，系统错误！";
		try {
			List<TbUser> userlist = userDAO.loginCheck(userLoginName);
			System.out.println(userlist.get(0).getUserLoginName());
			if (userlist == null) {
				result = "对不起，数据库错误！";
			} else if (userlist.size() == 0) {
				result = "对不起，用户名错误！";
			} else {
				TbUser userbean = (TbUser) userlist.get(0);
				if (!userPwd.equals(userbean.getUserPwd())) {
					result = "对不起，密码错误！";
				} else {
					
					System.out.println("loginSuccess");
					HttpServletRequest request = ServletActionContext
							.getRequest();
					HttpSession loginusersession = request.getSession();
					//System.out.println("userList---->"
					//		+ userlist.get(0).getTbCompany().getCompanyName());
					loginusersession.setAttribute("loginusersession",
							userlist.get(0));// 设置登录成功用户Session
					result = "PASS";
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "对不起，系统错误！";
		}
		return result;
	}

	@Override
	@Transactional
	public String addUser(UserDTO userDTO)throws Exception{
		String result = null ;
		
		try {
			System.out.println(userDTO.getUserLoginName());
			List<TbUser> userlist = userDAO.addCheck(userDTO.getUserLoginName());
			System.out.println("bbbbbbbbbbb");
			if(userlist==null)
			{
				result="对不起，数据库错误";
				System.out.println("对不起，数据库错误");
				
			}else if(userlist.size()!=0){
				result="对不起，用户名已经被注册";
				System.out.println("对不起，用户名已经被注册");
			}
			else {
				System.out.println("logic  addUser ssssss");
				int resultadd=userDAO.addUser(userDTO.toDAO());
				if(resultadd==0){
					
					result="sql语句错误";
					
				}else{
					result="成功";
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			result="对不起，系统错误";
		}
		return result;
	}
}
	