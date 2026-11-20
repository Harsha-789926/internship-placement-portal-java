
package com.careerbridge.careerbridge_backend.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StudentSkillId implements Serializable {

    private Integer studentId;

    private Integer skillId;

    public StudentSkillId() {
    }

    public StudentSkillId(Integer studentId, Integer skillId) {
        this.studentId = studentId;
        this.skillId = skillId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
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

        if (!(object instanceof StudentSkillId)) {
            return false;
        }

        StudentSkillId that = (StudentSkillId) object;

        return Objects.equals(studentId, that.studentId)
                && Objects.equals(skillId, that.skillId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, skillId);
    }
}
