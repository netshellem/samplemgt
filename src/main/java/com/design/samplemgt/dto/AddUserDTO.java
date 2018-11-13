package com.design.samplemgt.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddUserDTO {

    @JsonProperty("userName")
    public String userName;

    @JsonProperty("userType")
    public String  userType;

    @JsonProperty("password1")
    public String password;

    @JsonProperty("password2")
    public String confirm;
}
