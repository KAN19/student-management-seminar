package com.kuni.studentmanagement.service;

import com.kuni.studentmanagement.dto.request.CreateEnrollingStudentRequest;
import com.kuni.studentmanagement.dto.response.StudentResponse;

public interface EnrollingStudentService {
    StudentResponse createEnrollingStudent(CreateEnrollingStudentRequest request);
}
