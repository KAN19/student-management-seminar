package com.kuni.studentmanagement.enumeration;

import lombok.Getter;

@Getter
public enum EnrollingStatusEnum {
    PENDING("pending"),
    APPROVED("approved"),
    REJECTED("rejected");

    private final String value;

    EnrollingStatusEnum(String value) {
        this.value = value;
    }
}
