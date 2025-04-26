package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "samples")
public class Sample {

  @Id
  @GeneratedValue
  private Long id;

  @NotBlank
  @Size(max = 50)
  private String username;

  @NotBlank
  @Size(max = 100)
  private String email;

  @NotBlank
  @Size(max = 255)
  private String password_hash;

  @NotNull
  private LocalDateTime created_at;

  @NotNull
  private LocalDateTime updated_at;
}
