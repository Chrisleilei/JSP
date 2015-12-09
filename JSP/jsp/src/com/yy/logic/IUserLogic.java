package com.yy.logic;

import com.yy.dto.UserDTO;


public interface IUserLogic {
	public String loginCheck(String userLoginName,String userPwd)throws Exception;

	public String addUser (UserDTO userDTO)throws Exception;
}
