
package com.careerbridge.careerbridge_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "JOB_BRANCH")
public class JobBranch {

    @EmbeddedId
    private JobBranchId id;

    public JobBranch() {
    }

    public JobBranch(JobBranchId id) {
        this.id = id;
    }

    public JobBranchId getId() {
        return id;
    }

    public void setId(JobBranchId id) {
        this.id = id;
    }
}
