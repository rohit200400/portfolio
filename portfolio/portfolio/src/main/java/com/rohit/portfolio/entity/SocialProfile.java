package com.rohit.portfolio.entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "social_profiles")
public class SocialProfile {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private UserDetail userDetail;

    @Id
    @ManyToOne
    @JoinColumn(name = "media_key", referencedColumnName = "media_key", nullable = false)
    private SocialMedia socialMedia;

    @Column(name = "profile_link", nullable = false)
    private String profileLink;
}
