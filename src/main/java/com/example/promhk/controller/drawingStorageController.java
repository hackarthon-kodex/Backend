package com.example.promhk.controller;

import com.example.promhk.repository.DrawingStorageRepository;
import com.example.promhk.service.DrawingStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/drawingNote")
@Slf4j
public class drawingStorageController {

    final private DrawingStorageService drawingStorageService;

    // 업로드
    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("photo") MultipartFile file) throws IOException {
        log.info("here! {}", file);
        String uploadImage = drawingStorageService.uploadPhoto(file);
        return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
    }

    // 다운로드
    @GetMapping("/{createdAt}")
    public ResponseEntity<?> downloadImage(@PathVariable("createdAt") String createdAt) {
        byte[] downloadImage = drawingStorageService.downloadImage(createdAt);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(downloadImage);
    }

}
