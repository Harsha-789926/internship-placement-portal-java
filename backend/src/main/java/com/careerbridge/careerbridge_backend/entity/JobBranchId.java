
package com.careerbridge.careerbridge_backend.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class JobBranchId implements Serializable {

    private Integer jobId;
    private Integer branchId;

    public JobBranchId() {
    }

    public JobBranchId(Integer jobId, Integer branchId) {
        this.jobId = jobId;
        this.branchId = branchId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof JobBranchId)) {
            return false;
        }

        JobBranchId that = (JobBranchId) object;

        return Objects.equals(jobId, that.jobId)
                && Objects.equals(branchId, that.branchId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobId, branchId);
    }
}
