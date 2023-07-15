package com.rohit.portfolio.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "social_profiles")
@IdClass(SocialProfile.SocialProfileIdPK.class)
public class SocialProfile {

    @JsonIgnore
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

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    public static class SocialProfileIdPK implements Serializable {
        private Integer userDetail;
        private Integer socialMedia;
    }
}
