package com.teamalpha.teamalphapipergames.model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "staff")
public class Staff {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "staff_id")
  private int id;
  @Column(name = "staff_name", length = 40)
  private String name;

  // Empty constructor
  public Staff() {
  }

  // Constructor w/o id
  public Staff(String name) {
    this.name = name;
  }

  // Full constructor
  public Staff(int id, String name) {
    this.id = id;
    this.name = name;
  }

  // methods
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "game")
  private List<Team> allStaff = new ArrayList<>();

  // Setters and getters
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Team> getAllStaff() {
    return allStaff;
  }

  public void setAllStaff(List<Team> allStaff) {
    this.allStaff = allStaff;
  }
}