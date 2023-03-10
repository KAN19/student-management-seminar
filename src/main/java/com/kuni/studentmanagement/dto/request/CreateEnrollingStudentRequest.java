package com.kuni.studentmanagement.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEnrollingStudentRequest {

    private String fullName;

    private Timestamp studentDob;

    private String identityNumber;

    private String major;

    private String startingSeason;

    private String highSchoolCert;

    private String highSchoolResult;

    private String email;
}
