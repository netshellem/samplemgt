package com.design.samplemgt.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryParamDTO {

    @JsonProperty("start")
    public String start;

    @JsonProperty("end")
    public String end;

    @JsonProperty("status")
    public String status;

    @JsonProperty("keyword")
    public String keyword;

    @JsonProperty("condition")
    public String condition;

    @JsonProperty("year")
    public String year;

}
