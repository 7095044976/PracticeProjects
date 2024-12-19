package com.example.Practice.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.Practice.Entity.User;

public interface UserService {
public void createUser(User user);
public  List<User> getAllUsers();
public User getUserById(long id);
public boolean deleteUserById(long id);
public User updateUserById(long id,User user);
public void createBatchOfUsers(List<User> user);


//custom query methods

//Fetch all the users details using custom query
public List<User> fetchAllUsers();

//Fetch particular using details by passing name as a parameter
public User fetchUserByName(String name);

//Fetch particular using details by passing name and gmail as a parameters
public User fetchUserByNameAndGmail(String name,String gmail);

//To fetch only name and salary from database
public List<Object[]> fetchUserNameAndGmail();

//To fetch data from database order by name
public List<User> fetchUsersOrderByNameCustom();

//To upload .csv file to database
public void uploadCsvFileToDB(MultipartFile file) throws Exception, IOException;
}

