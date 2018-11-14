package com.design.samplemgt.service;


import com.design.samplemgt.pojo.Worker;

import java.util.List;

public interface WorkerService {
    public Worker findByWorkerName(String name);
    public Boolean existByWorkerName(String name);
    public List<Worker> FindAllWorkers();
    public void UpdateWorker(Worker worker);
    public Worker Save(Worker worker);
    public List<Worker> findByWorkTypeAndEnabledTrue(String workType);
}
