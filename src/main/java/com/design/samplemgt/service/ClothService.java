package com.design.samplemgt.service;

import com.design.samplemgt.dto.QueryParamDTO;
import com.design.samplemgt.pojo.Cloth;

import java.util.List;

public interface ClothService {
    public List<Cloth> findClothByQuery(QueryParamDTO query);
    public List<Cloth> findClothByYear(String year);
}
