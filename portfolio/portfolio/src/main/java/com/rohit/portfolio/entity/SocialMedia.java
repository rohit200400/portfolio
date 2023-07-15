package com.rohit.portfolio.entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "social_media")
public class SocialMedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "media_key")
    private Integer mediaKey;

    @Column(name = "social_media_type", nullable = false)
    private String socialMediaType;
}
