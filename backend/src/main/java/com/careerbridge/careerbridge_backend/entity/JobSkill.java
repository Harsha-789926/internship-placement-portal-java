
package com.careerbridge.careerbridge_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "JOB_SKILL")
public class JobSkill {

    @EmbeddedId
    private JobSkillId id;

    public JobSkill() {
    }

    public JobSkill(JobSkillId id) {
        this.id = id;
    }

    public JobSkillId getId() {
        return id;
    }

    public void setId(JobSkillId id) {
        this.id = id;
    }
}
