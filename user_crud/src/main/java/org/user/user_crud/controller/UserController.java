package org.user.user_crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.user.user_crud.dao.UserDao;
import org.user.user_crud.dto.User;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserDao userDao;
	
	@PostMapping("/saveAll")
    public String saveAllUser(@RequestBody List<User> users) {
        userDao.saveAllUser(users);
        return "Users saved";
    }
	
	@PostMapping("/save")
    public String saveUser(@RequestBody User user) {
        userDao.saveUser(user);
        return "User saved";
    }
	
    @GetMapping("/find/{id}")
    public Optional<Object> findById(@PathVariable int id) {
        Optional<User> user= userDao.findByIdUser(id);
        return user.map(ResponseEntity::ok);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userDao.findAllUser();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/count")
    public ResponseEntity<String> getEmployeeCount() {
        long count = userDao.countUser();
        return ResponseEntity.ok("Total number of employees: " + count);
    }
    
 // Delete Employee by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable int id) {
        try {
            userDao.deleteByIdUser(id);
            return ResponseEntity.ok("Employee with ID " + id + " deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Employee with ID " + id + " not found.");
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllEmployees() {
        userDao.deleteAllUsers();
        return ResponseEntity.ok("All employees deleted successfully.");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable int id, @RequestBody User user) {
        try {
            userDao.updateUser(id, user);
            return ResponseEntity.ok("Employee with ID " + id + " updated successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(e.getMessage());
        }
    }
}
