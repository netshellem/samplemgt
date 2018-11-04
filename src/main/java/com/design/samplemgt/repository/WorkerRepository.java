package com.design.samplemgt.repository;

import com.design.samplemgt.pojo.Worker;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WorkerRepository extends CrudRepository<Worker, Integer> {

    public List<Worker> findByWorkType(String workType);

}
