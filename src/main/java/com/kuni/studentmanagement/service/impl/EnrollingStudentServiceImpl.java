package com.kuni.studentmanagement.service.impl;

import com.kuni.studentmanagement.dto.request.CreateEnrollingStudentRequest;
import com.kuni.studentmanagement.dto.response.StudentResponse;
import com.kuni.studentmanagement.entity.EnrollingStudent;
import com.kuni.studentmanagement.entity.Student;
import com.kuni.studentmanagement.enumeration.EnrollingStatusEnum;
import com.kuni.studentmanagement.helper.DateTimeConverter;
import com.kuni.studentmanagement.repository.EnrollingStudentRepository;
import com.kuni.studentmanagement.repository.StudentRepository;
import com.kuni.studentmanagement.service.EnrollingStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class EnrollingStudentServiceImpl implements EnrollingStudentService {

    private final EnrollingStudentRepository enrollingStudentRepository;

    private final StudentRepository studentRepository;

    @Autowired
    public EnrollingStudentServiceImpl(EnrollingStudentRepository enrollingStudentRepository, StudentRepository studentRepository) {
        this.enrollingStudentRepository = enrollingStudentRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentResponse createEnrollingStudent(CreateEnrollingStudentRequest request) {
        EnrollingStudent enrollingStudent = EnrollingStudent.builder()
                .enrollingStatus(EnrollingStatusEnum.PENDING)
                .fullName(request.getFullName())
                .highSchoolCert(request.getHighSchoolCert())
                .highSchoolResult(request.getHighSchoolResult())
                .identityNumber(request.getIdentityNumber())
                .major(request.getMajor())
                .startingSeason(request.getStartingSeason())
                .studentDob(request.getStudentDob())
                .build();
        EnrollingStudent save = enrollingStudentRepository.save(enrollingStudent);
        return new StudentResponse(save, "Create enrolling student successfully!");
    }

    @Override
    public StudentResponse getListEnrolling() {
        List<EnrollingStudent> enrollingStudents = enrollingStudentRepository.findAll();
        return new StudentResponse(enrollingStudents, "Get list enrolling students successfully!");
    }

    @Override
    public StudentResponse changeEnrollingStatus(Long id, String status) {
        EnrollingStudent enrollingStudent = enrollingStudentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrolling student not found!"));
        EnrollingStatusEnum enrollingStatusEnum = null;
        try {
            enrollingStatusEnum = EnrollingStatusEnum.valueOf(status.toUpperCase());
        } catch (Exception exception) {
            throw new RuntimeException("Status is not valid!");
        }
        enrollingStudent.setEnrollingStatus(enrollingStatusEnum);
        EnrollingStudent save = enrollingStudentRepository.save(enrollingStudent);

        if (enrollingStatusEnum == EnrollingStatusEnum.APPROVED) {
            studentRepository.findByIdentityNumber(enrollingStudent.getIdentityNumber()).ifPresent(student -> {
                throw new RuntimeException("Student already exists!");
            });
            Student student = Student.builder()
                    .fullName(enrollingStudent.getFullName())
                    .identityNumber(enrollingStudent.getIdentityNumber())
                    .studentDob(enrollingStudent.getStudentDob())
                    .major(enrollingStudent.getMajor())
                    .startingSeason(enrollingStudent.getStartingSeason())
                    .build();
            studentRepository.save(student);
        }

        return new StudentResponse(save, "Change enrolling status successfully!");
    }
}
