package com.design.samplemgt.repository;

import com.design.samplemgt.pojo.Worker;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WorkerRepository extends CrudRepository<Worker, Integer> {

    public List<Worker> findByWorkTypeAndEnabledTrue(String workType);

    public List<Worker> findByWorkerName(String workerName);

    @Query("SELECT CASE WHEN COUNT(w) > 0 THEN 'true' ELSE 'false' END FROM Worker w WHERE w.workerName = ?1")
    Boolean existsByWorkerName(String workerName);

    public List<Worker> findAll();


}
