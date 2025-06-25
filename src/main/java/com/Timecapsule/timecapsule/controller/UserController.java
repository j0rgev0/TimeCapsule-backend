package com.Timecapsule.timecapsule.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Timecapsule.timecapsule.models.User;
import com.Timecapsule.timecapsule.repository.UserRepository;
import com.Timecapsule.timecapsule.services.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

  @Autowired
  private UserRepository UserRepo;

  @GetMapping()
  public List<User> getUsers() {
    return UserRepo.findAll();
  }

  // @GetMapping("/{id}")
  // public UserDto getUser(@PathVariable UUID id) {
   // return UserService.getUser(id);
  //}

  // @PutMapping("/{id}")
  // public User updateUser(@PathVariable UUID id, @RequestBody User u) {
  //   u.setId(id);
  //   return UserRepo.save(u);
  // }

  // @DeleteMapping("/{id}")
  // public void deleteUser(@PathVariable UUID id) {
  //   UserRepo.deleteById(id);
  // }

}
