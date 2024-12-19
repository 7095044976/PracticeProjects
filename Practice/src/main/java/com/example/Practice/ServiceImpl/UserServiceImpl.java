package com.example.Practice.ServiceImpl;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.Practice.Entity.User;
import com.example.Practice.Repository.UserRepository;
import com.example.Practice.Service.UserService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void createUser(User user) {
		userRepository.save(user);
		
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User getUserById(long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public boolean deleteUserById(long id) {
		// TODO Auto-generated method stub
		Optional<User> user=userRepository.findById(id);
		if(user.isPresent())
		{
			userRepository.deleteById(id);
			return true;
		}
		else
		{
			throw new RuntimeException("User not found");
		}

	}

	@Override
	public User updateUserById(long id, User user) {
		// TODO Auto-generated method stub
		Optional<User> user1=userRepository.findById(id);
		if(!user1.isPresent())
		 {
			throw new RuntimeException("User not found");
		 }
		User existingUser=user1.get();
		existingUser.setName(user.getName());
		existingUser.setGmail(user.getGmail());
		return userRepository.save(existingUser);
	}

	@Override
	public void createBatchOfUsers(List<User> user) {
		userRepository.saveAll(user);
		
	}
	
	//custom query methods

	@Override
	public List<User> fetchAllUsers() {
		return userRepository.fetchAllUsersCustom();
	}

	@Override
	public User fetchUserByName(String name) {
		// TODO Auto-generated method stub
		return userRepository.fetchUserByNamesCustom(name);
	}

	@Override
	public User fetchUserByNameAndGmail(String name, String gmail) {
		// TODO Auto-generated method stub
		return userRepository.fetchUserByNamesAndGmailCustom(name, gmail);
	}

	@Override
	public List<Object[]> fetchUserNameAndGmail() {
		// TODO Auto-generated method stub
		return userRepository.fetchUserNameAndGmailCustom();
	}

	@Override
	public List<User> fetchUsersOrderByNameCustom() {
		// TODO Auto-generated method stub
		return userRepository.fetchUsersOrderByNameCustom();
	}

	@Override
	public void uploadCsvFileToDB(MultipartFile file) throws Exception, IOException {
		
		CSVReader csvReader=new CSVReader(new InputStreamReader(file.getInputStream()));
		csvReader.readNext();
		String[] nextLine;
	    List<User> to_saveUser=new ArrayList<>();
		while((nextLine=csvReader.readNext())!=null)
				{
			         User user =new User();
			         user.setId(Long.parseLong(nextLine[0]));
			         user.setName(nextLine[1]);
			         user.setGmail(nextLine[2]);
			         user.setSalary(Double.parseDouble(nextLine[3]));
			         to_saveUser.add(user);
				}
		
		userRepository.saveAll(to_saveUser);
	}

}
