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

    @JsonProperty("querystatus")
    public String querystatus;

    @JsonProperty("searchname")
    public String searchname;

    @JsonProperty("direction")
    public String direction;

    @JsonProperty("year")
    public String year;
}
