package com.rohit.portfolio.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "user_skill")
@IdClass(UserSkillKey.class)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserSkill {

    @Id
    @ManyToOne
    @JoinColumn(name = "skill_id", referencedColumnName = "skill_id", nullable = false)
    private Skill skill;

    @JsonIgnore
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private UserDetail userDetail;

}
