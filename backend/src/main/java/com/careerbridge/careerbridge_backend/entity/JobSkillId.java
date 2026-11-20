
package com.careerbridge.careerbridge_backend.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class JobSkillId implements Serializable {

    private Integer jobId;

    private Integer skillId;

    public JobSkillId() {
    }

    public JobSkillId(Integer jobId, Integer skillId) {
        this.jobId = jobId;
        this.skillId = skillId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof JobSkillId)) {
            return false;
        }

        JobSkillId that = (JobSkillId) object;

        return Objects.equals(jobId, that.jobId)
                && Objects.equals(skillId, that.skillId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobId, skillId);
    }
}
