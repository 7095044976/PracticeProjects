package com.example.Practice.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.Practice.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

//To fetch all the data from the data base using custom queries
	@Query(value="select u from User u")
	public List<User> fetchAllUsersCustom();
	
	
//To fetch  particular user  data from the database by passing name using custom queries
	@Query(value="select u from User u where name=:val")
	public User fetchUserByNamesCustom(@Param("val") String name);
	
//To fetch  particular user  data from the database by passing name using custom queries
		@Query(value="select u from User u where name=:val1 and gmail=:val2")
		public User fetchUserByNamesAndGmailCustom(@Param("val1") String name,@Param("val2") String gmail);
	
//To fetch only name and salary from database
	   @Query(value="select u.name,u.gmail from User u")
	   public List<Object[]> fetchUserNameAndGmailCustom();
	   
	   //To fetch data from database order by name
	   
	   @Query(value="select u from User u order by name")
	   public List<User> fetchUsersOrderByNameCustom();
}



