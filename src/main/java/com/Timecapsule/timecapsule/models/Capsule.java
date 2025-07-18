package com.Timecapsule.timecapsule.models;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Capsule {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(unique = true, nullable = false)
  private String title;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private Boolean isShared;

  @Column(nullable = false)
  private Boolean isPrivate;

  @ManyToOne
  @JoinColumn(name = "owner_id")
  private User owner;

  @Column(nullable = false)
  private LocalDate closeDate;

  private String CapsuleAvatar;

  private LocalDate createAt = LocalDate.now();

  @OneToMany(mappedBy = "capsule", cascade = CascadeType.ALL)
  private List<Image> images;

  @OneToMany(mappedBy = "capsule", cascade = CascadeType.ALL)
  private List<Video> videos;

  @OneToMany(mappedBy = "capsule", cascade = CascadeType.ALL)
  private List<UserCapsule> userCapsules;

}