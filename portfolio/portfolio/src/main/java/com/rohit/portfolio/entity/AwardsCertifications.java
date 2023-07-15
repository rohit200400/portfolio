package com.rohit.portfolio.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "awards_certifications")
public class AwardsCertifications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private UserDetail userDetail;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "related_link")
    private String relatedLink;

    @Column(name = "type", nullable = false)
    private String type;

}
