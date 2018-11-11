package com.design.samplemgt.dto;

public enum WorkerStatusEnum {
    EMPLOYED("在职",1), LAYOFF("离职",0);
    private String name;
    private int index;
    public static String getName(int index) {
        for (WorkerStatusEnum c : WorkerStatusEnum.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    private WorkerStatusEnum(String name, int index) {
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
