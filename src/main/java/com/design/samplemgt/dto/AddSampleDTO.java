package com.design.samplemgt.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddSampleDTO {
    @JsonProperty("cid")
    public String cid;

    @JsonProperty("clothtype")
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

    @JsonProperty("create_date")
    public Date create_date;

}
