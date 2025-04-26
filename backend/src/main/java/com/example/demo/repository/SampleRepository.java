package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Sample;

public interface SampleRepository extends JpaRepository<Sample, Long> {
}
