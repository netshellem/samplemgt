package com.design.samplemgt.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSampleDTO {
    @JsonProperty("cid")
    public String cid;

    @JsonProperty("type")
    public String clothType;

    @JsonProperty("designer")
    public String design;

    @JsonProperty("model")
    public String model;

    @JsonProperty("sample")
    public String sample;

    @JsonProperty("status")
    public String status;

    @JsonProperty("origin")
    public String origin;

    @JsonProperty("start_date")
    public Date cdate;

    @JsonProperty("end_date")
    public Date sdate;

    @JsonProperty("customer")
    public String customer;

    @JsonProperty("comment")
    public String comment;

    @JsonProperty("copy")
    public String copy;

    @JsonProperty("state")
    public String state;
}
