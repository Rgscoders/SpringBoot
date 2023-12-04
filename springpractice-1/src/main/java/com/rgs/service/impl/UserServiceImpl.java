package com.rgs.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rgs.exception.MyCustomException;
import com.rgs.repository.UserEntityRepository;
import com.rgs.repository.model.UserEntity;
import com.rgs.service.UserService;
import com.rgs.service.dto.UserDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserEntityRepository userRepository;
	
	@Override
	public Integer saveUser(UserDTO userDTO) {
		
		
		Integer userId = 0;
		
		try {
			
			log.info("In UserServiceImpl saveUser() Started");
			
			if(userDTO != null) {
				UserEntity userEntity = new UserEntity();
				
				userEntity.setFirstName(userDTO.getFirstName());
				userEntity.setLastName(userDTO.getLastName());
				userEntity.setPassword(userDTO.getPassword());
				userEntity.setEmailId(userDTO.getEmailId());
				userEntity.setMobileNumber(userDTO.getMobileNumber());
				UserEntity user = (UserEntity)userRepository.save(userEntity);
				
				if(user != null) {
					userId = user.getUserId();
					
				}
				
			}
		}catch (Exception e) {
			
			log.error("In UserServiceImpl saveUser() Exception occured "+e.getMessage());
			
			throw new MyCustomException("Exception Occured while inserting userData"+ e);
		}
		return userId;
	}

	@Override
	public List<UserDTO> loadUsers() {
		List<UserDTO> users=null;
		try {
			List<UserEntity> usersList=userRepository.findAll();
			if(usersList!=null) {
				users=new ArrayList<>();
				ListIterator<UserEntity> li=usersList.listIterator();
				while (li.hasNext()) {
					UserEntity userEntity=li.next();
					UserDTO user=new UserDTO();
					user.setFirstName(userEntity.getFirstName());
					user.setLastName(userEntity.getLastName());
					user.setEmailId(userEntity.getEmailId());
					user.setPassword(userEntity.getPassword());
					user.setMobileNumber(userEntity.getMobileNumber());
					user.setUserId(userEntity.getUserId());
					users.add(user);
				}
			}
			
		}
		catch(Exception e) {
			throw new MyCustomException("Exception occured while retreving user data");
			
		}
		return users;
	}

	@Override
	public UserDTO loadUser(Integer userId) {
		UserDTO user=null;
		try {
			Optional<UserEntity> optional=userRepository.findById(userId);
			if(optional.isPresent()) {
				user=new UserDTO();
				UserEntity userEntity=optional.get();
				user.setUserId(userEntity.getUserId());
				user.setFirstName(userEntity.getFirstName());
				user.setLastName(userEntity.getLastName());
				user.setEmailId(userEntity.getEmailId());
				user.setPassword(userEntity.getPassword());
				user.setMobileNumber(userEntity.getMobileNumber());
				
			}
				
		}catch(Exception e){
			throw new MyCustomException("Exception occured while retreving user data");
			
		}
		return user;
	}

	@Override
	public void updateUser(UserDTO userDTO) {
		try {
			if(userDTO!=null) {
				UserEntity userEntity=new UserEntity();
				userEntity.setUserId(userDTO.getUserId());
				userEntity.setFirstName(userDTO.getFirstName());
				userEntity.setLastName(userDTO.getLastName());
				userEntity.setEmailId(userDTO.getEmailId());
				userEntity.setPassword(userDTO.getPassword());
				userEntity.setMobileNumber(userDTO.getMobileNumber());
				UserEntity user = (UserEntity)userRepository.save(userEntity);
			}
		}catch(Exception e) {
			throw new MyCustomException("Exception occured while updating user details");
		}
		
	}

	@Override
	public void deleteUser(Integer userId) {
		try {
			UserEntity user=userRepository.findById(userId).get();
			userRepository.delete(user);
			
		}catch (Exception e) {
			throw new MyCustomException("Exception occured while deleting user");
		}
		
	}

}
