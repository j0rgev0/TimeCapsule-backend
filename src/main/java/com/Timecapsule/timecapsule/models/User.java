package com.Timecapsule.timecapsule.models;
// necesitas JPA mvn
import jakarta.persistence.Entity;
// Auto increment ID
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
// te ahorras los getters y setters
import lombok.Data;

@Entity
@Data
@Table(name = "usuarios") //Nombre de la tabla, es importante no utilizar palabras reservadas de PostgreSQL
// user, select, table, where, group, order, insert, limit, etc.
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String email;
  private String password;
  // private image avatar;
}
