package com.design.samplemgt.endpoint;

import com.design.samplemgt.dto.ValidateDTO;
import com.design.samplemgt.dto.ValidateWorkerDTO;
import com.design.samplemgt.dto.WorkerDTO;
import com.design.samplemgt.pojo.Worker;
import com.design.samplemgt.service.WorkerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class WorkerEndPoint {
    @Autowired
    WorkerService workerService;

    @ApiOperation(value = "Get All Workers")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 500, message = "InternalServerError")})
    @RequestMapping(value = "/GetAllWorkers", method = RequestMethod.GET)
    public List<Worker> GetAllWorkers(){
        return workerService.FindAllWorkers();
    }

    @ApiOperation(value = "Update Worker Status")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 500, message = "InternalServerError")})
    @RequestMapping(value = "/UpdateWorker", method = RequestMethod.POST)
    public void UpdateWorker(@RequestBody WorkerDTO workerDTO){
        Worker worker = new Worker();
        worker.setCdate(workerDTO.cdate);
        worker.setId(workerDTO.id);
        worker.setWorkType(workerDTO.workType);
        worker.setWorkerName(workerDTO.workerName.replaceAll(" ",""));
        if(workerDTO.enabled.equals("1"))
            worker.setEnabled(true);
        else
            worker.setEnabled(false);
        workerService.UpdateWorker(worker);
        return;
    }

    @ApiOperation(value = "Add worker")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 500, message = "InternalServerError")})
    @RequestMapping(value = "/AddWorker", method = RequestMethod.POST)
    public boolean SaveWorker(@RequestBody Worker worker){
       // System.out.println("========="+ worker.toString());
        Date d = new Date();
        worker.setCdate(d);
        worker.setEnabled(true);
        if(null == workerService.Save(worker))
            return false;
        return true;
    }

    @ApiOperation(value = "ValidateWorkerName")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 500, message = "InternalServerError")})
    @RequestMapping(value = "/ValidateWorkerName", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = "application/json;charset=UTF-8")
    public ValidateDTO ValidateWorkerName(ValidateWorkerDTO worker){
            ValidateDTO v = new ValidateDTO();
            v.valid = false;
           if( workerService.existByWorkerName(worker.userName.replaceAll(" ",""))){
               return v;
           }
           v.valid = true;
           return v;
    }
}
