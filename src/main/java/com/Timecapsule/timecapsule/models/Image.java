package com.Timecapsule.timecapsule.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "images")
public class Image {
  
  @Id
  @GeneratedValue
  private UUID id;
  
  private String url;
  
}
