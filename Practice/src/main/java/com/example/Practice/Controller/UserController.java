package com.example.Practice.Controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Practice.Entity.User;
import com.example.Practice.Service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	//Save the user details into database
	@PostMapping("/createUsers")
	ResponseEntity<String> createUser(@RequestBody User user)
	{
		userService.createUser(user);
		return new ResponseEntity<>("User created successfully",HttpStatus.OK);
	}
	
	
	//To fetch all the users from the database
	@GetMapping("/getUsers")
	ResponseEntity<List<User>> getAllUsers()
	{
		return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.CREATED);
				
 	}
	
	//To fetch user by id 
	@GetMapping("/getUserById/{id}")
	ResponseEntity<User> getUserById(@PathVariable Long id )
	{
		return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
				
 	}
	
	//To delete user by id 
	@DeleteMapping("/deleteUserById/{id}")
	ResponseEntity<String> deleteUserById(@PathVariable Long id )
	{
       boolean status=userService.deleteUserById(id) ;
       if(status)
         {
 	   	 	 return new ResponseEntity<>("Uer Deleted successfully",HttpStatus.OK);
         }
       return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);		
 	}
	
	//To update user by id
	@PutMapping("/updateUserById/{id}")
	ResponseEntity<User> updateUserById(@PathVariable Long id, @RequestBody User user )
	{
       
 	   	 	 return new ResponseEntity<>(userService.updateUserById(id, user),HttpStatus.OK);
	}
        
	
	//To create multiple users at a time
	@PostMapping("/createBatchOfUsers")
	ResponseEntity<String> createBatchOfUsers(@RequestBody List<User> user)
	{
		userService.createBatchOfUsers(user);
		return new ResponseEntity<>("User created successfully",HttpStatus.CREATED);
	}
	
	
	
	
	
	//Custom query Examles
	
	@GetMapping("/fetchAllUsersCustom")
	ResponseEntity<List<User>> fetchAllUsersCustom()
	{
		return new ResponseEntity<>(userService.fetchAllUsers(),HttpStatus.CREATED);
				
 	}
	
	@GetMapping("/fetchUserByNameCustom/{name}")
	ResponseEntity<User> fetchUserByNameCustom(@PathVariable String name)
	{
		return new ResponseEntity<>(userService.fetchUserByName(name),HttpStatus.OK);
				
 	}
	
	@GetMapping("/fetchUserByNameAndGmailCustom/{name}/{gmail}")
	ResponseEntity<User> fetchUserByNameAndGmailCustom(@PathVariable String name,@PathVariable String gmail)
	{
		return new ResponseEntity<>(userService.fetchUserByNameAndGmail(name, gmail),HttpStatus.OK);
				
 	}
	
	@GetMapping("/fetchOnlyUserByNameAndGmailCustom")
	ResponseEntity<List<Object[]>> fetchOnlyUserByNameAndGmailCustom()
	{
		return new ResponseEntity<>(userService.fetchUserNameAndGmail(), HttpStatus.OK);
	}
	
	@GetMapping("/fetchUsersOrderByName")
	ResponseEntity<List<User>> fetchUsersOrderByName()
	{
		return new ResponseEntity<>(userService.fetchUsersOrderByNameCustom(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Upload a CSV file", notes = "Upload a CSV file and save data to database")
	@PostMapping("/uploadFile")
	ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile multipartFile)
	{
		try
		{
			if(multipartFile.isEmpty())
			{
				return ResponseEntity.status(400).body("No file selected for upload");
			}
			if(!multipartFile.getOriginalFilename().equals(".csv"))
			{
				return ResponseEntity.status(400).body("please upload csv file");
			}
		     userService.uploadCsvFileToDB(multipartFile);
		     return ResponseEntity.ok("File uploaded and saved successfully");
		}
		catch(Exception e)
		{
			return ResponseEntity.status(500).body("Failed to upload file");
		}
		
	}
	
}


