package com.design.samplemgt.endpoint;

import com.design.samplemgt.dto.*;
import com.design.samplemgt.pojo.Cloth;
import com.design.samplemgt.service.ClothService;
import com.design.samplemgt.utils.DataUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.io.IOException;
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
        //System.out.println("+++++++++++++++++"+sample.origin);
        Cloth c = new Cloth();
        c.setCid(sample.cid.toUpperCase().replaceAll(" ",""));
        c.setClothType(sample.clothType);
        c.setCdate(sample.create_date);
        c.setDesign(sample.design);
        c.setModel(sample.model);
        c.setSample(sample.sample);
        c.setStatus(StatusEnum.getName(1));
        c.setOrigin(sample.origin);
        c.setEnabled(true);
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
            clothService.delete(c);
        }
        return true;
    }

    @ApiOperation(value = "Update Sample Cloth")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 500, message = "InternalServerError")})
    @RequestMapping(value = "/admin/UpdateSample", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public boolean UpdateSample(@RequestBody String sample){
        //System.out.println("%%%%%%%%%%%%%%%%%%%%%"+sample);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            UpdateSampleDTO s = objectMapper.readValue(sample,UpdateSampleDTO.class);
            Cloth c = clothService.findOneByCid(s.cid);
            //c.setCid(s.cid.toUpperCase().replaceAll(" ",""));
            //c.setClothType(s.clothType);
            //c.setCdate(s.cdate);
            //c.setDesign(s.design);
           // c.setModel(s.model);
            //c.setSample(s.sample);
            c.setStatus(s.status);
            c.setOrigin(s.origin);
            c.setCopy(s.copy);
            c.setComment(s.comment);
            c.setCustomer(s.customer);
            c.setSdate(s.sdate);
            //c.setEnabled(true);
            clothService.Save(c);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

}
