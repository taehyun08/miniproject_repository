package com.model2.mvc.service.user;

import java.util.HashMap;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;


public interface UserService {
	
	public void addUser(User userVO) throws Exception;
	
	public User loginUser(User userVO) throws Exception;
	
	public User getUser(String userId) throws Exception;
	
	public Map<String, Object> getUserList(Search searchVO) throws Exception;
	
	public void updateUser(User userVO) throws Exception;
	
	public boolean checkDuplication(String userId) throws Exception;
	
}