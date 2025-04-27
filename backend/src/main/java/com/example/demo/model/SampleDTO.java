package com.example.demo.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SampleDTO {

  @NotBlank
  @Size(max = 50)
  private String username;

  @NotBlank
  @Size(max = 100)
  private String email;

  @NotBlank
  @Size(max = 255)
  private String password;

  public SampleDTO() {
  }

  public SampleDTO(Sample sample) {
    this.username = sample.getUsername();
    this.email = sample.getEmail();
    this.password = "*********";
  }

}
