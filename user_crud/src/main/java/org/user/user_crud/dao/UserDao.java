package org.user.user_crud.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.user.user_crud.dto.User;
import org.user.user_crud.repository.UserRepository;

@Repository
public class UserDao {
	
	@Autowired
	private UserRepository repo;
	
	public void saveUser(User user) {
		repo.save(user);
	}
	
	public void saveAllUser(Iterable<User> users) {
		repo.saveAll(users);
	}
	
	public Optional<User> findByIdUser(int id) {
		return repo.findById(id);
	}
	
	public List<User> findAllUser(){
		return repo.findAll();
	}
	
	public long countUser() {
		return repo.count();
	}
	
	public void deleteByIdUser(int id) {
		repo.deleteById(id);
	}
	
	public void deleteAllUsers() {
		repo.deleteAll();
	}
	
	public void updateUser(int id, User user) {
	    if(repo.existsById(id)) {
	    	User existingUser=repo.findById(id).orElse(null);

	    if (user.getUserName() != null) {
	        existingUser.setUserName(user.getUserName());
	    }
	    if (user.getEmail() != null) {
	        existingUser.setEmail(user.getEmail());
	    }
	    if (user.getPwd() != null) {
	        existingUser.setPwd(user.getPwd());
	    }
	    if (user.getPhNo() != 0) {
	        existingUser.setPhNo(user.getPhNo());
	    }

	    repo.save(existingUser); 
	   }
	}
}
