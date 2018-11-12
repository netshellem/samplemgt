package com.design.samplemgt.utils;

import com.design.samplemgt.dto.SampleClothDTO;
import com.design.samplemgt.pojo.Cloth;
import com.google.common.collect.Lists;


import java.util.ArrayList;
import java.util.List;


public class DataUtil {

    public static List<SampleClothDTO> ConvertToDTO(List<Cloth> clothes){

        List<SampleClothDTO> sampleDTOLists = Lists.transform(Lists.newArrayList(clothes), c -> {
            SampleClothDTO sample =  new SampleClothDTO();
            sample.cid = c.getCid();
            sample.cdate = c.getCdate();
            sample.clothType = c.getClothType();
            sample.comment = c.getComment();
            sample.design = c.getDesign();
            sample.customer = c.getCustomer();
            sample.model = c.getModel();
            sample.sample = c.getSample();
            sample.origin = c.getOrigin();
            sample.status = c.getStatus();
            sample.sdate = c.getSdate();
            return sample;
        });
        return sampleDTOLists;
    }

    public static List<Cloth> MergeList(@org.jetbrains.annotations.NotNull List<Cloth> ... lists){
        List<Cloth> tmp = new ArrayList<Cloth>();
        for (List<Cloth> list : lists) {
            tmp.addAll(list);
        }
        return tmp;
    }
}
