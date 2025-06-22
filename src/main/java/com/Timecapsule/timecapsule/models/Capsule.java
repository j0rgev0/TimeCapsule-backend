package com.Timecapsule.timecapsule.models;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Capsule {
  @Id @GeneratedValue
  private UUID id;
  // private image CapsuleAvatar;
  private String title;
  private Boolean isShared;
  private LocalDate closeDate;
  private LocalDate createAt = LocalDate.now();

  @OneToMany(mappedBy = "capsule", cascade = CascadeType.ALL)
  private List<User> users;

  @OneToMany(mappedBy = "capsule", cascade = CascadeType.ALL)
  private List<Video> videos;

  @ManyToMany(mappedBy = "capsulesSharedWith")
  private List<User> usersSharedWith;
}
