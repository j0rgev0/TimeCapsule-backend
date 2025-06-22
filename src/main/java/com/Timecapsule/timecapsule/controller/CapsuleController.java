package com.Timecapsule.timecapsule.controller;

import java.util.List;

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

import com.Timecapsule.timecapsule.models.Capsule;
import com.Timecapsule.timecapsule.repository.CapsuleRepository;

@RestController
@RequestMapping("/api/capsules")
@CrossOrigin(origins = "*") 
public class CapsuleController {

  @Autowired
  private CapsuleRepository CapsuleRepo;

  @GetMapping
  public List<Capsule> getCapsules() {
    return CapsuleRepo.findAll();
  }

  @PostMapping
  public Capsule create(@RequestBody Capsule c) {
    return CapsuleRepo.save(c);
  }

  @PutMapping("/{id}")
  public Capsule updateCapsule(@PathVariable Long id, @RequestBody Capsule c) {
    c.setId(id);
    return CapsuleRepo.save(c);
  }

  @DeleteMapping("/{id}")
  public void deleteCapsule(@PathVariable Long id) {
    CapsuleRepo.deleteById(id);
  }
}

  

