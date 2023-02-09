package com.kuni.studentmanagement.service.impl;

import com.kuni.studentmanagement.dto.request.CreateEnrollingStudentRequest;
import com.kuni.studentmanagement.dto.response.StudentResponse;
import com.kuni.studentmanagement.entity.EnrollingStudent;
import com.kuni.studentmanagement.entity.Student;
import com.kuni.studentmanagement.enumeration.EnrollingStatusEnum;
import com.kuni.studentmanagement.mail.MailService;
import com.kuni.studentmanagement.repository.EnrollingStudentRepository;
import com.kuni.studentmanagement.repository.StudentRepository;
import com.kuni.studentmanagement.service.EnrollingStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollingStudentServiceImpl implements EnrollingStudentService {

    private final EnrollingStudentRepository enrollingStudentRepository;

    private final StudentRepository studentRepository;

    private final MailService mailService;

    @Autowired
    public EnrollingStudentServiceImpl(EnrollingStudentRepository enrollingStudentRepository, StudentRepository studentRepository, MailService mailService) {
        this.enrollingStudentRepository = enrollingStudentRepository;
        this.studentRepository = studentRepository;
        this.mailService = mailService;
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
                .email(request.getEmail())
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
            Optional<Student> existedStudent = studentRepository.findByIdentityNumber(enrollingStudent.getIdentityNumber());
            if (existedStudent.isEmpty()) {
                Student student = Student.builder()
                        .fullName(enrollingStudent.getFullName())
                        .identityNumber(enrollingStudent.getIdentityNumber())
                        .studentDob(enrollingStudent.getStudentDob())
                        .major(enrollingStudent.getMajor())
                        .startingSeason(enrollingStudent.getStartingSeason())
                        .build();
                studentRepository.save(student);
            }
            mailService.sendEmail(enrollingStudent.getEmail(), "K-Uni Enrolling Status", "Your enrolling status is approved!");
        }

        if (enrollingStatusEnum == EnrollingStatusEnum.REJECTED)
            mailService.sendEmail(enrollingStudent.getEmail(), "K-Uni Enrolling Status", "Your enrolling status is rejected!");

        return new StudentResponse(save, "Change enrolling status successfully!");
    }

    @Override
    public StudentResponse getEnrollingStudentById(Long id) {
        EnrollingStudent enrollingStudent = enrollingStudentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrolling student not found!"));

        return new StudentResponse(enrollingStudent, "Get enrolling student by id successfully!");
    }
}
