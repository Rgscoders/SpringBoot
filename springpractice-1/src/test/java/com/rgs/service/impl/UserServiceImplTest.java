package com.rgs.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	
	@Test
	public void loadUsersTest() throws Exception{
		List<UserEntity> users=new ArrayList<>();
		UserEntity user1=new UserEntity();
		UserEntity user2=new UserEntity();
		users.add(user1);
		users.add(user2);
		Mockito.when(userRepository.findAll()).thenReturn(users);
		List<UserDTO> usersList=userServiceImpl.loadUsers();
		assertNotNull(usersList);
		assertEquals(2, usersList.size());
		
	}
	@Test(expected=MyCustomException.class)
	public void loadUsersTestException() throws Exception{
		Mockito.when(userRepository.findAll()).thenThrow(NullPointerException.class);
		userServiceImpl.loadUsers();
		
	}
	@Test
	public void loadUserTest() throws Exception{
		Integer userId=1;
		UserEntity userEntity=new UserEntity();
		Optional<UserEntity> optional=Optional.of(userEntity);
		Mockito.when(userRepository.findById(userId)).thenReturn(optional);
		UserDTO user=userServiceImpl.loadUser(userId);
		assertNotNull(user);
		
	}
	@Test(expected=MyCustomException.class)
	public void loadUserTextException() throws Exception{
		Integer userId=1;
		Mockito.when(userRepository.findById(userId)).thenThrow(NullPointerException.class);
		userServiceImpl.loadUser(userId);
		
	}
	@Test
	public void updateUserTest() throws Exception{
		UserDTO userDTO=new UserDTO();
		userDTO.setUserId(1);
		UserEntity userEntity=new UserEntity();
		userEntity.setUserId(1);
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(userEntity);
		userServiceImpl.updateUser(userDTO);
		assertTrue(true);
		
	}
	@Test(expected=MyCustomException.class)
	public void updateUserTestException() throws Exception{
		Mockito.when(userRepository.save(Mockito.any())).thenThrow(NullPointerException.class);
		userServiceImpl.updateUser(new UserDTO());
		
		
	}
	@Test
	public void deleteUserTest() throws Exception{
		Integer userId=1;
		UserEntity userEntity=new UserEntity();
		Optional<UserEntity> optional=Optional.of(userEntity);
		Mockito.when(userRepository.findById(userId)).thenReturn(optional);
		doNothing().when(userRepository).delete(Mockito.any());
		userServiceImpl.deleteUser(userId);
		assertTrue(true);
		
	}
	@Test(expected=MyCustomException.class)
	public void deleteUserTestException() throws Exception{
		Integer userId=1;
		Mockito.when(userRepository.findById(userId)).thenThrow(NullPointerException.class);
		userServiceImpl.deleteUser(userId);
		
	}

}
