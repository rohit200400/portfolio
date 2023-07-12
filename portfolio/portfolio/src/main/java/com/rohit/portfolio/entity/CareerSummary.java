package com.rohit.portfolio.entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "career_summary")
public class CareerSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "summary_id")
    private Integer summaryId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private UserDetail userDetail;

    @Column(name = "objective", nullable = false)
    private String objective;
}
