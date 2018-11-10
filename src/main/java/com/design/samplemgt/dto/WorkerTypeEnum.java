package com.design.samplemgt.dto;

public enum WorkerTypeEnum {
    SAMPLE("样衣师", 1), MODEL("版师", 2), DESIGN("设计师", 3);
    private String name;
    private int index;

    public static String getName(int index) {
        for (WorkerTypeEnum c : WorkerTypeEnum.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    private WorkerTypeEnum(String name, int index) {
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
