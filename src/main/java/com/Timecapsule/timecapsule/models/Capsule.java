package com.Timecapsule.timecapsule.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Capsule {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  // private image CapsuleAvatar;
  private String title;
  private Boolean isShared;
  private Date closeDate;
  private Date createAt;
  
}
