package com.example.demo.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.example.demo.model.Sample;
import com.example.demo.model.SampleDTO;
import com.example.demo.repository.SampleRepository;

@Service
@RequiredArgsConstructor
public class SampleService {

  private final SampleRepository sampleRepository;

  public SampleDTO getSampleById(Long id) {
    Sample sample = sampleRepository.findById(id).orElseThrow(() -> new RuntimeException("Sample not found"));
    return new SampleDTO(sample);
  }

  public Sample createSample(SampleDTO sampleRequest) {
    Sample sample = new Sample();
    sample.setUsername(sampleRequest.getUsername());
    sample.setEmail(sampleRequest.getEmail());
    sample.setPassword_hash(sampleRequest.getPassword()); // 🔴 ここで暗号化するのがベスト
    sample.setCreated_at(java.time.LocalDateTime.now());
    sample.setUpdated_at(java.time.LocalDateTime.now());
    return sampleRepository.save(sample);
  }

  public Sample updateSample(SampleDTO sampleRequest, Long id) {
    return sampleRepository.findById(id).map(sample -> {
      sample.setUsername(sampleRequest.getUsername());
      sample.setEmail(sampleRequest.getEmail());
      sample.setUpdated_at(java.time.LocalDateTime.now());
      return sampleRepository.save(sample);
    }).orElseThrow(() -> new RuntimeException("Sample not found"));
  }

  public void deleteSample(Long id) {
    sampleRepository.deleteById(id);
  }
}