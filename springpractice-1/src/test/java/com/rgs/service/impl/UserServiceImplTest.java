package com.rgs.service.impl;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rgs.exceptions.MyCustomException;
import com.rgs.repository.UserEntityRepository;
import com.rgs.repository.model.UserEntity;
import com.rgs.service.dto.UserDTO;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {

	@InjectMocks
	private UserServiceImpl userServiceImpl;
	
	@Mock
	private UserEntityRepository userRepository ;
	
	@Test
	public void saveUserTest() throws Exception{
		
		UserDTO userDto = new UserDTO();
		UserEntity userEntity = new UserEntity();
		userEntity.setUserId(1);
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(userEntity);
		Integer userId = userServiceImpl.saveUser(userDto) ;
		assertNotNull(userId);
	}
	@Test(expected = MyCustomException.class)
	
	public void saveUserTestException()throws Exception{
		
		UserDTO userDto =  new UserDTO();
		
		Mockito.when(userRepository.save(Mockito.any())).thenThrow(NullPointerException.class);
		
		userServiceImpl.saveUser(userDto);
		
		
		
	}
}
