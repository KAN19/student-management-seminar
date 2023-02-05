package com.kuni.studentmanagement.service.impl;

import com.kuni.studentmanagement.dto.request.CreateEnrollingStudentRequest;
import com.kuni.studentmanagement.dto.response.StudentResponse;
import com.kuni.studentmanagement.repository.EnrollingStudentRepository;
import com.kuni.studentmanagement.service.EnrollingStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollingStudentServiceImpl implements EnrollingStudentService {

    private final EnrollingStudentRepository enrollingStudentRepository;

    @Autowired
    public EnrollingStudentServiceImpl(EnrollingStudentRepository enrollingStudentRepository) {
        this.enrollingStudentRepository = enrollingStudentRepository;
    }

    @Override
    public StudentResponse createEnrollingStudent(CreateEnrollingStudentRequest request) {
        return null;
    }
}
