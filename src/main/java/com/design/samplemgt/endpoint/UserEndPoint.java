package com.design.samplemgt.endpoint;

import com.design.samplemgt.dto.AddSampleDTO;
import com.design.samplemgt.dto.UserDTO;
import com.design.samplemgt.pojo.AppUser;
import com.design.samplemgt.pojo.Cloth;
import com.design.samplemgt.service.UserRoleService;
import com.design.samplemgt.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserEndPoint {
    @Autowired
    UserService userService;
    @Autowired
    UserRoleService userRoleService;

    @ApiOperation(value = "Update User password")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 500, message = "InternalServerError")})
    @RequestMapping(value = "/ChangePassword", method = RequestMethod.POST)
    public void save(@RequestParam(value="password", required = true)  String password){
        //System.out.println("======"+ password);
        userService.UpdatePassword(password);
    }

    @ApiOperation(value = "Update User password")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 500, message = "InternalServerError")})
    @RequestMapping(value = "/GetAllUser", method = RequestMethod.GET)
    public List<UserDTO> GetAllUser(){
        List<UserDTO> res = new ArrayList<UserDTO>();
        UserDTO dto;
        List<AppUser>  users = userService.findAll();
        for(AppUser u : users){
            dto = new UserDTO();
            dto.id = u.getUserId();
            dto.userName = u.getUserName();
            dto.enabled = u.isEnabled();
            dto.iadmin = userRoleService.isAdmin(u);
            res.add(dto);
        }
        return res;

    }
}
