package com.design.samplemgt.repository;

import com.design.samplemgt.pojo.Cloth;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ClothRepository  extends CrudRepository<Cloth, Integer>,
        JpaSpecificationExecutor<Cloth> {

    List<Cloth> findByEnabledTrueAndCdateBetween(Date start, Date end);


    Boolean existsByCid(String cid);
    /*
    @Query("select new com.design.samplemgt.dto.SampleClothDTO(cid,clothType,design,model," +
            "sample,status,level,cdate,sdate,customer,comment) from Cloth c where c.enabled = true")
    public List<SampleClothDTO> findCloth();

    @Query("select new com.design.samplemgt.dto.SampleClothDTO(cid,clothType,design,model," +
            "sample,status,level,cdate,sdate,customer,comment) from Cloth c where c.enabled = true and YEAR(c.cdate)= :year")
    public List<SampleClothDTO> findAllCloth(int year);
*/



}
