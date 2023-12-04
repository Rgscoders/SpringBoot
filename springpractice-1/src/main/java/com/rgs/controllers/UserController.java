package com.rgs.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rgs.service.UserService;
import com.rgs.service.dto.UserDTO;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	@PostMapping("/api/saveuser")
	public ResponseEntity<Integer> insertUser(@RequestBody UserDTO userDTO)
	{
		try {
			Integer userId=userService.saveUser(userDTO);
			return new ResponseEntity<>(userId,HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/api/users")
	public ResponseEntity<List<UserDTO>>getUsers(){
		try {
			List<UserDTO> users=userService.loadUsers();
			return new ResponseEntity<List<UserDTO>>(users,HttpStatus.OK);
			
		}catch (Exception e) {
			return new ResponseEntity<List<UserDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/api/user/{userId}")
	public ResponseEntity<UserDTO> getUser(@PathVariable Integer userId){
		try {
			UserDTO user=userService.loadUser(userId);
			return new ResponseEntity<UserDTO>(HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<UserDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@PutMapping("/api/updateuser")
	public ResponseEntity<Void> updateUser(@RequestBody UserDTO userDTO){
		try {
			userService.updateUser(userDTO);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
	}
	@DeleteMapping("/api/deleteuser/{userId}")
	public ResponseEntity<Void>deleteUser(@PathVariable Integer userId){
		try {
			userService.deleteUser(userId);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

