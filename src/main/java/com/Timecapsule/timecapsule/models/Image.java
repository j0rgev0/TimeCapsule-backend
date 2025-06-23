package com.Timecapsule.timecapsule.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "images")
public class Image {
  
  @Id
  @GeneratedValue
  private UUID id;
  
  private String url;
  private String publicId;

  @ManyToOne
  @JoinColumn(name = "capsule_id")
  private Capsule capsule;
}
