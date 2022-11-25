package com.example.jpa270test.model;

import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TestModel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private UUID first;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public UUID getFirst() {
    return first;
  }

  public void setFirst(UUID first) {
    this.first = first;
  }

}
