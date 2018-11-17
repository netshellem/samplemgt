package com.design.samplemgt.service;

import com.design.samplemgt.dto.QueryParamDTO;
import com.design.samplemgt.pojo.Cloth;

import java.util.List;

public interface ClothService {
    public List<Cloth> findClothByQuery(QueryParamDTO query);
    public List<Cloth> findClothByYear(String year);
    public Cloth Save(Cloth cloth);
    Boolean existsByCid(String cid);
    public Cloth findOneByCid(String cid);
    public void delete(Cloth c);
}
