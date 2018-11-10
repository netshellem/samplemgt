package com.design.samplemgt.dto;

public enum OriginEnum {
    BEIJING("北京",1), HANGZHOU("杭州",2), KEQIAO("柯桥",3);
    private String name;
    private int index;
    public static String getName(int index) {
        for (OriginEnum c : OriginEnum.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    private OriginEnum(String name, int index) {
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
