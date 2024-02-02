package com.example.promhk.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Table(name="DrawingData")
@NoArgsConstructor
@Entity
@Getter
public class DrawingData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "photo", length = 1000000)
    private byte[] photo;

    @Column(name = "name")
    private String name;

    @Column(name = "content")
    private String content;

    @Column(name = "createdAt")
    private String createdAt;


    @Builder
    public DrawingData(String name, byte[] photo, String content, String createdAt) {
        this.name = name;
        this.photo = photo;
        this.content = content;
        this.createdAt = createdAt;
    }
}
