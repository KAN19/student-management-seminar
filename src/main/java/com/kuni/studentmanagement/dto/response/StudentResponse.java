package com.kuni.studentmanagement.dto.response;

public class StudentResponse extends BaseResponse{
    public StudentResponse(Object data, String message, Boolean error, Integer errorCode) {
        super(data, message, error, errorCode);
    }

    public StudentResponse(String message, Integer errorCode) {
        super(message, errorCode);
    }

    public StudentResponse(Object data, String message) {
        super(data, message);
    }

    public StudentResponse(Object data, String message, Integer errorCode) {
        super(data, message, errorCode);
    }
}
