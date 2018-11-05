package com.design.samplemgt.repository;

import com.design.samplemgt.dto.SampleClothDTO;
import com.design.samplemgt.pojo.AppRole;
import com.design.samplemgt.pojo.Cloth;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ClothRepository  extends CrudRepository<Cloth, Integer> {

    @Query("select new com.design.samplemgt.dto.SampleClothDTO(cid,clothType,design,model," +
            "sample,status,level,cdate,sdate,customer,comment) from Cloth c where c.enabled = true")
    public List<SampleClothDTO> findAllCloth();

    @Query("select new com.design.samplemgt.dto.SampleClothDTO(cid,clothType,design,model," +
            "sample,status,level,cdate,sdate,customer,comment) from Cloth c where c.enabled = true and YEAR(c.cdate)= :year")
    public List<SampleClothDTO> findAllCloth(int year);


    @Query("select new com.design.samplemgt.dto.SampleClothDTO(cid,clothType,design,model," +
            "sample,status,level,cdate,sdate,customer,comment) from Cloth c where c.enabled = true " +
            "and c.cdate > :startDate and c.cdate < :endDate ")
    public List<SampleClothDTO> findAllClothByCdate(@Param("startDate") final Date startDate,
                                                   @Param("endDate")final Date endDate);
}
