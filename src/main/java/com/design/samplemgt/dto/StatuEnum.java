package com.design.samplemgt.dto;

public enum StatuEnum {
    INSTOCK("库存",1), SOLED("售出",2), RETURN("退货",3),DONATE("赠送",4),
    REWORK("复色",5),OEM("贴牌",6),COWORK("合作",7),DESTORY("处理",8);
    private String name;
    private int index;
    public static String getName(int index) {
        for (StatuEnum c : StatuEnum.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    private StatuEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;

    }
}
