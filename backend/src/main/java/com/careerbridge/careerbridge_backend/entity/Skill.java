
package com.careerbridge.careerbridge_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "SKILL")
public class Skill {

    @Id
    @Column(name = "skill_id")
    private Integer skillId;

    @Column(name = "skill_name", nullable = false, unique = true, length = 100)
    private String skillName;

    public Skill() {
    }

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
}
