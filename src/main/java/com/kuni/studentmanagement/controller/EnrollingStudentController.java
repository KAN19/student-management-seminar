package com.kuni.studentmanagement.controller;

import com.kuni.studentmanagement.dto.request.CreateEnrollingStudentRequest;
import com.kuni.studentmanagement.dto.response.BaseResponse;
import com.kuni.studentmanagement.dto.response.StudentResponse;
import com.kuni.studentmanagement.service.EnrollingStudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("enrolling")
public class EnrollingStudentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EnrollingStudentController.class);

    private final EnrollingStudentService enrollingStudentService;

    @Autowired
    public EnrollingStudentController(EnrollingStudentService enrollingStudentService) {
        this.enrollingStudentService = enrollingStudentService;
    }

    @PostMapping
    public ResponseEntity<?> enrolling(CreateEnrollingStudentRequest request) {
        try {
            return ResponseEntity.ok().body(enrollingStudentService.createEnrollingStudent(request));
        } catch (Exception exception) {
            LOGGER.error("Failed to enroll!");
            StudentResponse response = new StudentResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(response);
        }
    }

}
