package com.Timecapsule.timecapsule.models;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "images")
public class Image {
  
  @Id
  @GeneratedValue
  private UUID id;

  @Column(nullable = false)
  private String url;

  @Column(nullable = false)
  private String publicId;

  @ManyToOne
  @JoinColumn(name = "capsule_id")
  private Capsule capsule;
}
