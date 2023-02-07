package com.kuni.studentmanagement.service;

import com.kuni.studentmanagement.dto.request.CreateEnrollingStudentRequest;
import com.kuni.studentmanagement.dto.response.StudentResponse;

public interface EnrollingStudentService {
    StudentResponse createEnrollingStudent(CreateEnrollingStudentRequest request);

    StudentResponse getListEnrolling();

    StudentResponse changeEnrollingStatus(Long id, String status);

    StudentResponse getEnrollingStudentById(Long id);
}
