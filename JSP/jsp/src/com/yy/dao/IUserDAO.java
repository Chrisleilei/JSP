package com.yy.dao;

import java.util.List;

import com.yy.dto.UserDTO;
import com.yy.model.TbUser;

public interface IUserDAO {
	public List<TbUser> loginCheck(String userLoginName1) throws Exception;

	public int addUser(TbUser userDTO)throws Exception;

	public List<TbUser> addCheck(String userLoginName)throws Exception;
}
