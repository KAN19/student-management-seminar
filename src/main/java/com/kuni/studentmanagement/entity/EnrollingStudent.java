package com.kuni.studentmanagement.entity;

import com.kuni.studentmanagement.enumeration.EnrollingStatusEnum;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "enrolling_student")
public class EnrollingStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "student_dob", nullable = false)
    private Timestamp studentDob;

    @Column(name = "identity_number", nullable = false)
    private String identityNumber;

    @Column(name = "major", nullable = false)
    private String major;

    @Column(name = "starting_season", nullable = false)
    private String startingSeason;

    @Column(name = "high_school_cert", nullable = false)
    private String highSchoolCert;

    @Column(name = "high_school_result", nullable = false)
    private String highSchoolResult;

    @Column(name = "enrolling_status", nullable = false)
    private EnrollingStatusEnum enrollingStatus;
}
