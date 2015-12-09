package com.yy.dao.impl;

import java.util.List;

import javax.annotation.Resource;


//import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.yy.dao.IUserDAO;
import com.yy.dto.UserDTO;
import com.yy.model.TbUser;



@Component
public class UserDAO implements IUserDAO{
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<TbUser> loginCheck(String userLoginName1) throws Exception {
		System.out.println("This is logincheck dao!!!!!!!!!!!!");
		List<TbUser> result = null;
		Session session = sessionFactory.getCurrentSession();
		String hql = "select tbuser from TbUser as tbuser where userLoginName= '" + userLoginName1+"'";
		//String hql = "from TbUser where userLoginName='" + userLoginName
		//		+ "' ";
		System.out.println(hql);
				//+ "' and userIsDeleted = 0";
		Query query = session.createQuery(hql);
		result = query.list();                                    
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<TbUser> addCheck(String userLoginName1) throws Exception {
		System.out.println("This is addcheck dao!!!!!!!!!!!!");
		List<TbUser> result = null;
		Session session = sessionFactory.getCurrentSession();
		String hql = "select tbuser from TbUser as tbuser where userLoginName= '" + userLoginName1+"'";
		//String hql = "from TbUser where userLoginName='" + userLoginName
		//		+ "' ";
		System.out.println(hql);
				//+ "' and userIsDeleted = 0";
		Query query = session.createQuery(hql);
		result = query.list();                                    
		return result;
	}

	@Override
	public int  addUser (TbUser tbuser)throws Exception{
		System.out.println("addUser dao******************");
		int result=0;
		Session session = sessionFactory.getCurrentSession();
		
		/*String sql = "INSERT into TbUser"
				+ "(userName,userLoginName,userPwd,userSex,userPhone,userMailBox)"
				+ "VALUES('"+userDTO.getUserName()+"','"+userDTO.getUserLoginName()+"','"+userDTO.getUserPwd()+"',"+userDTO.getUserSex()+",'"+userDTO.getUserPhone()+"','"+userDTO.getUserMailBox()+"')";
		*/
		
		result = (int) session.save(tbuser);
		return result;
		
	}
}
