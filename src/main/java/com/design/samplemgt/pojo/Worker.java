package com.design.samplemgt.pojo;

import javax.persistence.*;

@Entity
@Table(name = "worker", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "workername_UNIQUE", columnNames = "workername") })
public class Worker {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "workername",  nullable = false)
    private String workerName;

    @Column(name = "worktype",  nullable = true)
    private String workType;

    @Column(name = "enabled",  nullable = false)
    private boolean enabled;
}
