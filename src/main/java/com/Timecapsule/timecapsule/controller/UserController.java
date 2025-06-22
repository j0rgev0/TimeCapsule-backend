package com.Timecapsule.timecapsule.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Timecapsule.timecapsule.models.User;
import com.Timecapsule.timecapsule.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*") // permite que se acceda desde cualquier origen
public class UserController {

  @Autowired
  private UserRepository UserRepo;

  @GetMapping()
  public List<User> getUsers() {
    return UserRepo.findAll();
  }

  @PostMapping()
  public User create(@RequestBody User u) {
    return UserRepo.save(u);
  }

  @PutMapping("/{id}")
  public User updateUser(@PathVariable UUID id, @RequestBody User u) {
    u.setId(id);
    return UserRepo.save(u);
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable Long id) {
    UserRepo.deleteById(id);
  }

}
