package com.design.samplemgt.pojo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cloth", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "cid_UNIQUE", columnNames = "cid")  })
public class Cloth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
    }

    public String getClothType() {
        return clothType;
    }

    public void setClothType(String clothType) {
        this.clothType = clothType;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public AppUser getAppuser() {
        return appuser;
    }

    public void setAppuser(AppUser appuser) {
        this.appuser = appuser;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Column(name = "cid", nullable = false)
    private String cid;

    @Column(name = "cdate", nullable = true)
    private Date cdate;

    @Column(name = "sdate", nullable = true)
    private Date sdate;

    @Column(name = "design", nullable = true)
    private String design;

    @Column(name = "model", nullable = true)
    private String model;

    @Column(name = "sample", nullable = true)
    private String sample;

    @Column(name = "clothtype", nullable = true)
    private String clothType;

    @Column(name = "customer", nullable = true)
    private String customer;

    @Column(name = "comment", nullable = true)
    private String comment;

    @Column(name = "origin", nullable = true)
    private String origin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appuser", nullable = false)
    private AppUser appuser;

    @Column(name = "enabled", length = 1, nullable = false)
    private boolean enabled;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "status",  nullable = true)
    private String status;
}
