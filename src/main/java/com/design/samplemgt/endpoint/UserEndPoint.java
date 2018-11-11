package com.design.samplemgt.endpoint;

import com.design.samplemgt.dto.AddSampleDTO;
import com.design.samplemgt.pojo.Cloth;
import com.design.samplemgt.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserEndPoint {
    @Autowired
    UserService userService;

    @ApiOperation(value = "Update User password")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 500, message = "InternalServerError")})
    @RequestMapping(value = "/ChangePassword", method = RequestMethod.POST)
    public void save(@RequestParam(value="password", required = true)  String password){
        //System.out.println("======"+ password);
        userService.UpdatePassword(password);
    }
}
