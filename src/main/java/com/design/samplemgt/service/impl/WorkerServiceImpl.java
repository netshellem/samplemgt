package com.design.samplemgt.service.impl;

import com.design.samplemgt.pojo.Worker;
import com.design.samplemgt.repository.WorkerRepository;
import com.design.samplemgt.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("WorkerServiceImpl")
public class WorkerServiceImpl implements WorkerService {
    @Autowired
    WorkerRepository workerRepository;

    @Override
    public Worker findByWorkerName(String name) {
        List<Worker> workers = workerRepository.findByWorkerName(name);
        if((workers != null) && (workers.size() > 0))
            return workers.get(0);
        return null;
    }
}
