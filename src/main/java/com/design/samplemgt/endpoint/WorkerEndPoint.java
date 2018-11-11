package com.design.samplemgt.endpoint;

import com.design.samplemgt.dto.WorkerDTO;
import com.design.samplemgt.pojo.Worker;
import com.design.samplemgt.service.WorkerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
        worker.setWorkerName(workerDTO.workerName);
        if(workerDTO.enabled.equals("1"))
            worker.setEnabled(true);
        else
            worker.setEnabled(false);
        workerService.UpdateWorker(worker);
        return;
    }
}
