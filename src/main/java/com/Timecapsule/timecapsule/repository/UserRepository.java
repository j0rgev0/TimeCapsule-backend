package com.Timecapsule.timecapsule.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Timecapsule.timecapsule.models.User;

// Repositorios: Es una interfaz que nos permite acceder a los datos de la base de datos sin SQL
public interface UserRepository extends JpaRepository<User, UUID> {
  // aqui agregar metodos 
} 
  
