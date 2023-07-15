package com.rohit.portfolio.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user_phone")
public class UserPhone {

    @Id
    @Column(name = "contact_number", nullable = false)
    private String contactNumber;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private UserDetail userDetail;

    // this is ENUM ('primary' , 'secondary')
    @Column(name = "type", nullable = false)
    private String type;

}
