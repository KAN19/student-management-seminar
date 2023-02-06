package com.kuni.studentmanagement.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kuni.studentmanagement.dto.request.CreateEnrollingStudentRequest;
import com.kuni.studentmanagement.dto.response.BaseResponse;
import com.kuni.studentmanagement.dto.response.StudentResponse;
import com.kuni.studentmanagement.service.EnrollingStudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> enrolling(@RequestBody CreateEnrollingStudentRequest request) {
        try {
            return ResponseEntity.ok().body(enrollingStudentService.createEnrollingStudent(request));
        } catch (Exception exception) {
            LOGGER.error("Failed to enroll!");
            StudentResponse response = new StudentResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public ResponseEntity<?> getListEnrolling() {
        try {
            return ResponseEntity.ok().body(enrollingStudentService.getListEnrolling());
        } catch (Exception exception) {
            LOGGER.error("Failed to get list enrolling students!");
            StudentResponse response = new StudentResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/update-status/{id}")
    public ResponseEntity<?> changeEnrollingStatus(@RequestBody ObjectNode objectNode, @PathVariable("id") Long id) {
        try {
            String status = objectNode.get("status").asText();
            return ResponseEntity.ok().body(enrollingStudentService.changeEnrollingStatus(id, status));
        } catch (Exception exception) {
            LOGGER.error("Failed to get list enrolling students!");
            StudentResponse response = new StudentResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(response);
        }
    }


}
