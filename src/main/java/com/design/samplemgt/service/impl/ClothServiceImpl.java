package com.design.samplemgt.service.impl;

import com.design.samplemgt.dto.QueryParamDTO;
import com.design.samplemgt.dto.SampleClothDTO;
import com.design.samplemgt.dto.WorkerTypeEnum;
import com.design.samplemgt.pojo.Cloth;
import com.design.samplemgt.pojo.Worker;
import com.design.samplemgt.repository.ClothRepository;
import com.design.samplemgt.repository.WorkerRepository;
import com.design.samplemgt.service.ClothService;
import com.design.samplemgt.service.WorkerService;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("ClothServiceImpl")
public class ClothServiceImpl implements ClothService {

    @Autowired
    private ClothRepository clothRepository;

    @Autowired
    private WorkerService workerService;

    @Override
    public Cloth Save(Cloth cloth){
        return clothRepository.save(cloth);
    }

    @Override
    public Boolean existsByCid(String cid) {
        return clothRepository.existsByCid(cid);
    }

    @Override
    public void delete(Cloth c){
        clothRepository.delete(c);
    }
    public Cloth findOneByCid(String cid){
        Optional<Cloth> c= clothRepository.findByCid(cid);
        if(c.isPresent()){
            return c.get();
        }
        return null;
    }

    @Override
    public List<Cloth> findClothByQuery(QueryParamDTO param){
        return clothRepository.findAll((Specification<Cloth>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            Path<Date> datePath = null;
            Path<String> path = null;

            predicates.add(cb.isTrue(root.get("enabled")));
            if(!param.status.isEmpty())
                predicates.add(cb.equal(root.get("status"), param.status));

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            if(Integer.parseInt(param.condition) == 0){
                datePath =  root.<Date>get("cdate");
            }
            else{
                datePath =  root.<Date>get("sdate");
            }
            if(!param.start.isEmpty()){
                Date start = null;
                try {
                    start = format.parse(param.start);
                    predicates.add(cb.greaterThanOrEqualTo(datePath, start));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
            if(!param.end.isEmpty()){
                Date end = null;
                try {
                    end = format.parse(param.end);
                    predicates.add(cb.lessThanOrEqualTo(datePath, end));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            if(!param.keyword.isEmpty()){
                Worker worker = workerService.findByWorkerName(param.keyword);
                if(worker != null){
                    if(worker.getWorkType().equals(WorkerTypeEnum.getName(1))) path = root.get("sample");
                    if(worker.getWorkType().equals(WorkerTypeEnum.getName(2))) path = root.get("model");
                    if(worker.getWorkType().equals(WorkerTypeEnum.getName(3))) path = root.get("design");
                    predicates.add(cb.equal(path, param.keyword));
                }else{
                    predicates.add(cb.like(root.get("customer"),"%" + param.keyword + "%"));
                }

            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));

        });
    }

    @Override
    public List<Cloth> findClothByYear(String year){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date start = format.parse(year + "-01-01");
            Date end = format.parse(year + "-12-31");
            return clothRepository.findByEnabledTrueAndCdateBetween(start,end);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
