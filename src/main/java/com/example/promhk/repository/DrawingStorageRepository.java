package com.example.promhk.repository;

import com.example.promhk.entity.DrawingData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public interface DrawingStorageRepository extends JpaRepository<DrawingData, Long> {

    Optional<DrawingData> findByCreatedAt(String createdAt);

    @Override
    ArrayList<DrawingData> findAll();


}
