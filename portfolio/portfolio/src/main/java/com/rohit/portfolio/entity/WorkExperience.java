package com.rohit.portfolio.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "work_experience")
public class WorkExperience {

    @Id
    @Column(name = "exp_key")
    private Integer expKey;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private UserDetail userDetail;

    @Column(name = "company", nullable = false)
    private String company;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @Column(name = "designation", nullable = false)
    private String designation;

    @Column(name = "description", nullable = false)
    private String description;

}
