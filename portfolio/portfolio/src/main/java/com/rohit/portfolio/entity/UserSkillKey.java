package com.rohit.portfolio.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserSkillKey implements Serializable {
    private Skill skill;
    private UserDetail userDetail;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSkillKey that = (UserSkillKey) o;
        return Objects.equals(skill, that.skill) && Objects.equals(userDetail, that.userDetail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skill, userDetail);
    }
}
