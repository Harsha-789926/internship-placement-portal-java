
package com.careerbridge.careerbridge_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "STUDENT_SKILL")
public class StudentSkill {

    @EmbeddedId
    private StudentSkillId id;

    public StudentSkill() {
    }

    public StudentSkill(StudentSkillId id) {
        this.id = id;
    }

    public StudentSkillId getId() {
        return id;
    }

    public void setId(StudentSkillId id) {
        this.id = id;
    }
}
