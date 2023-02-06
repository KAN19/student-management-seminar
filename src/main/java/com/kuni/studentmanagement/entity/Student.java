package com.kuni.studentmanagement.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "student")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identity_number", nullable = false)
    private String identityNumber;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "student_dob", nullable = false)
    private Timestamp studentDob;

    @Column(name = "major", nullable = false)
    private String major;

    @Column(name = "starting_season", nullable = false)
    private String startingSeason;
}
