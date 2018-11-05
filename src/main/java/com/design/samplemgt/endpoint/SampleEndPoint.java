package com.design.samplemgt.endpoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.design.samplemgt.dto.QueryParamDTO;
import com.design.samplemgt.dto.SampleClothDTO;
import com.design.samplemgt.repository.ClothRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class SampleEndPoint {

    @Autowired
    ClothRepository clothRepository;

/*    @ApiOperation(value = "get all cloth")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 500, message = "InternalServerError")})
    @PostMapping(value =  "/GetAllCloth",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<SampleClothDTO> GetAllCloth(@RequestBody(required = false) QueryParamDTO query){
        if(query == null) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = null;
            Date endDate = null;
            try {
                startDate = formatter.parse(year + "-01-01");
                endDate = formatter.parse(year + "-12-31");
                return clothRepository.findAllClothByYear(startDate, endDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return clothRepository.findAllCloth();
    }*/

    @ApiOperation(value = "Get all cloth")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 500, message = "InternalServerError")})
    @RequestMapping(value = "/GetAllCloth/{year}", method = RequestMethod.POST)
    @ResponseBody
    public List<SampleClothDTO> GetAllClothes(@PathVariable("year") int year){
       // System.out.println("========================="+ year);
        return clothRepository.findAllCloth(year);
    }

}
