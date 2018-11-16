package com.design.samplemgt.endpoint;

import com.design.samplemgt.dto.*;
import com.design.samplemgt.pojo.AppUser;
import com.design.samplemgt.pojo.Cloth;
import com.design.samplemgt.service.UserRoleService;
import com.design.samplemgt.service.UserService;
import com.design.samplemgt.utils.EncrytedPasswordUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @ApiOperation(value = "Get User list")
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
            if (userRoleService.isAdmin(u))
                dto.iadmin = "是";
            else
                dto.iadmin = "否";
            res.add(dto);
        }
        return res;

    }

    @ApiOperation(value = "Add User")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 500, message = "InternalServerError")})
    @RequestMapping(value = "/AddUser", method = RequestMethod.POST)
    public boolean AddUser(@RequestBody AddUserDTO userDTO){
        //System.out.println("=============="+userDTO.toString());
        AppUser user = new AppUser();
        user.setEnabled(true);
        user.setUserName(userDTO.userName.replaceAll(" ", ""));
        user.setEncrytedPassword(EncrytedPasswordUtils.encrytePassword(userDTO.password));
        if(userService.save(user) == null)
            return false;
        if(userDTO.userType.equals("0"))
            return userRoleService.addRole(false, user);
        if(userDTO.userType.equals("1"))
            return userRoleService.addRole(true, user);
        return true;
    }


    @ApiOperation(value = "Update User")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 500, message = "InternalServerError")})
    @RequestMapping(value = "/UpdateUser", method = RequestMethod.POST)
    public boolean UpdateUser(@RequestBody UserDTO userDTO){
        AppUser appUser = userService.findByUserId(userDTO.id);
        //System.out.println("=================="+userDTO.enabled);
        appUser.setEnabled(userDTO.enabled);
        if(null == userService.save(appUser)) return false;

        return true;
    }

    @ApiOperation(value = "ValidateUserName")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 500, message = "InternalServerError")})
    @RequestMapping(value = "/ValidateUserName", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = "application/json;charset=UTF-8")
    public ValidateDTO ValidateUserName(ValidateWorkerDTO user){
        ValidateDTO v = new ValidateDTO();
        v.valid = false;
        if( userService.existByUserName(user.userName.replaceAll(" ",""))){
            return v;
        }
        v.valid = true;
        return v;
    }
}
