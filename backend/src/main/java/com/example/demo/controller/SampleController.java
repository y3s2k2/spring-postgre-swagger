package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Sample;
import com.example.demo.model.SampleDTO;
import com.example.demo.repository.SampleRepository;
import com.example.demo.service.SampleService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RequestMapping("api/v1/samples")
@RequiredArgsConstructor
@RestController
public class SampleController {

  private final SampleRepository repository;
  private final SampleService service;

  @Operation(summary = "タスクをページネーションで取得します")
  @GetMapping("/")
  ResponseEntity<List<Sample>> findAll(@RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<Sample> samplePage = repository.findAll(pageable);
    return ResponseEntity.ok(samplePage.getContent());
  }

  @Operation(summary = "タスクを登録します")
  @PostMapping("/")
  ResponseEntity<?> save(@RequestBody SampleDTO sampleDTO) {
    try {
      // created_atとupdated_atが設定されていない可能性があります
      Sample savedSample = service.createSample(sampleDTO);
      return ResponseEntity.ok(savedSample);
    } catch (DataIntegrityViolationException e) {
      String errorMessage = "データの一意性制約に違反しました: " + e.getMessage();
      return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
    } catch (org.springframework.transaction.TransactionSystemException e) {
      StringBuilder errorMessage = new StringBuilder("トランザクションエラーが発生しました: " + e.getMessage());
      Optional.ofNullable(e.getRootCause())
          .ifPresent(cause -> errorMessage.append(" 根本原因: ").append(cause.getMessage()));
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage.toString());
    } catch (Exception e) {
      String errorMessage = "サンプルの保存中にエラーが発生しました: " + e.getMessage();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }
  }

  @Operation(summary = "タスクを1件取得します")
  @GetMapping("/{id}")
  SampleDTO findById(@PathVariable Long id) {
    return service.getSampleById(id);
  }

  @Operation(summary = "タスクを更新します")
  @PutMapping("/{id}")
  Sample save(@RequestBody SampleDTO newSample, @PathVariable Long id) {
    return service.updateSample(newSample, id);
  }

  @Operation(summary = "タスクを削除します")
  @DeleteMapping("/{id}")
  void deleteById(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
