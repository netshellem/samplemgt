package com.design.samplemgt.dto;

public enum ClothTypeEnum {
    COAT("风衣",1), FEATHER("羽绒服",2), COTTON("棉服",3);
    private String name;
    private int index;
    public static String getName(int index) {
        for (ClothTypeEnum c : ClothTypeEnum.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    private ClothTypeEnum(String name, int index) {
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
