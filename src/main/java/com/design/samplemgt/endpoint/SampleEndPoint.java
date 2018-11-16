package com.design.samplemgt.endpoint;

import com.design.samplemgt.dto.*;
import com.design.samplemgt.pojo.Cloth;
import com.design.samplemgt.service.ClothService;
import com.design.samplemgt.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;
import java.util.StringTokenizer;

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
        if(!query.year.equals("")){
            //System.out.println("======"+ SecurityContextHolder.getContext().getAuthentication().getName());
            List<Cloth> clothes = clothService.findClothByYear(query.year);
            return DataUtil.ConvertToDTO(clothes);
        }
        List<Cloth> clothes = clothService.findClothByQuery(query);
        List<SampleClothDTO> result = DataUtil.ConvertToDTO(clothes);
        return result;
    }

    @ApiOperation(value = "Add one Sample Cloth")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 500, message = "InternalServerError")})
    @RequestMapping(value = "/AddSample", method = RequestMethod.POST)
    @ResponseBody
    public Cloth save(@RequestBody AddSampleDTO sample){
        Cloth c = new Cloth();
        c.setCid(sample.cid.toUpperCase().replaceAll(" ",""));
        c.setClothType(sample.clothType);
        c.setCdate(sample.create_date);
        c.setDesign(sample.design);
        c.setModel(sample.model);
        c.setSample(sample.sample);
        c.setStatus(sample.status);
        return clothService.Save(c);
    }


    @ApiOperation(value = "ValidateCid")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 500, message = "InternalServerError")})
    @RequestMapping(value = "/ValidateCid", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = "application/json;charset=UTF-8")
    public ValidateDTO ValidateCid(ValidateSampleDTO sample){
        ValidateDTO v = new ValidateDTO();
        v.valid = false;
        if( clothService.existsByCid(sample.cid.toUpperCase().replace(" ",""))){
            return v;
        }
        v.valid = true;
        return v;
    }

    @RequestMapping(value = "/admin/DeleteSample", method = RequestMethod.GET)
    public boolean DeleteSample(@RequestParam(value="cid", required = true)  String cid){
        String[] sourceStrArray = cid.split(",");
        for (int i = 0; i < sourceStrArray.length; i++) {
            Cloth c = clothService.findOneByCid(sourceStrArray[i]);
            c.setEnabled(false);
            if (null == clothService.Save(c))
                return false;
        }
        return true;
    }

}
