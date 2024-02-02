package com.example.promhk.service;


import com.example.promhk.entity.DrawingData;
import com.example.promhk.repository.DrawingStorageRepository;
import com.example.promhk.utils.DrawingUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DrawingStorageService {

    private final DrawingStorageRepository drawingStorageRepository;

    public String uploadPhoto(MultipartFile file) throws IOException {
        log.info("upload file: {}", file.getOriginalFilename());
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String createdTime = formatter.format(now);
        DrawingData drawingData = drawingStorageRepository.save(
                DrawingData.builder()
                        .name(file.getOriginalFilename())
                        .photo(DrawingUtils.compressImage(file.getBytes()))
                        .createdAt(createdTime)
                        .build());
        log.info("?null? {} ", drawingData.getId());
        log.info("drawingData: {}", drawingData.getName());
        log.info("created AT: ", drawingData.getCreatedAt());
        if (drawingData != null) {
            log.info("drawingData: {}", drawingData.getName());
            return "file uploaded successfully (id) : " + drawingData.getId();
        }
        return null;
    }

    // 이미지 파일로 압축하기
    public byte[] downloadImage(Long id) {
        log.info("here name {} ", id);
        DrawingData drawingData = drawingStorageRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        log.info("download drawingData: {}", drawingData);

        return DrawingUtils.decompressImage(drawingData.getPhoto());
    }

    public long getDrawingCount() {
        return drawingStorageRepository.count();
    }
}
