package com.kuni.studentmanagement.dto.request;

import com.kuni.studentmanagement.enumeration.EnrollingStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
}
