package com.mycompany.constant;

public enum TrangThaiEnum {

    ACTIVE(1), INACTIVE(0);

    TrangThaiEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    private final Integer value;
}
