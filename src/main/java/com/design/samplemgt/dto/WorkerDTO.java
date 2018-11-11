package com.design.samplemgt.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkerDTO {
    @JsonProperty("id")
    public int id;
    @JsonProperty("workerName")
    public String workerName;
    @JsonProperty("workType")
    public String workType;
    @JsonProperty("enabled")
    public String enabled;
    @JsonProperty("cdate")
    public Date cdate;
}
