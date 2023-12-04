package com.rgs.service;

import java.util.List;

import com.rgs.service.dto.UserDTO;

public interface UserService {

	public Integer saveUser(UserDTO userDTO);
	
	public List<UserDTO> loadUsers();
	
	public UserDTO loadUser(Integer userId);
	
	public void updateUser(UserDTO userDTO);
	
	public void deleteUser(Integer userId);
	
}
