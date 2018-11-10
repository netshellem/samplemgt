package com.design.samplemgt.endpoint;

import com.design.samplemgt.dto.QueryParamDTO;
import com.design.samplemgt.dto.SampleClothDTO;
import com.design.samplemgt.pojo.Cloth;
import com.design.samplemgt.service.ClothService;
import com.design.samplemgt.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@RestController
public class SampleEndPoint {

    @Autowired
    ClothService clothService;

    @ApiOperation(value = "Find all enabled clothes ")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 500, message = "InternalServerError")})
    @RequestMapping(value = "/GetSamples", method = RequestMethod.POST)
    @ResponseBody
    public List<SampleClothDTO> GetSamples(@RequestBody QueryParamDTO query){
       //System.out.println("========================="+ query.condition);

        if(!query.year.equals("")){
            List<Cloth> clothes = clothService.findClothByYear(query.year);
            return DataUtil.ConvertToDTO(clothes);
        }
        List<Cloth> clothes = clothService.findClothByQuery(query);
        List<SampleClothDTO> result = DataUtil.ConvertToDTO(clothes);
        return result;
    }

}
