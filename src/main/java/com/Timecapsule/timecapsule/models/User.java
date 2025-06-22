package com.Timecapsule.timecapsule.models;

import java.util.List;
import java.util.UUID;

// necesitas JPA mvn
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
// te ahorras los getters y setters
import lombok.Data;

@Entity
@Data
@Table(name = "usuarios") // Nombre de la tabla, es importante no utilizar palabras reservadas de PostgreSQL
// user, select, table, where, group, order, insert, limit, etc.
public class User {
  @Id
  @GeneratedValue
  private UUID id;
  private String name;
  private String email;
  private String password;
  private String avatar;

  @OneToMany(mappedBy = "owner")
  private List<Capsule> capsulesOwned;
  // private image avatar;

  @OneToMany
  @JoinTable(
    name = "user_capsule",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "capsule_id")
  )
  private List<Capsule> capsulesShared;

}
