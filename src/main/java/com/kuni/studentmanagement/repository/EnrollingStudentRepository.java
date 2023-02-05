package com.kuni.studentmanagement.repository;

import com.kuni.studentmanagement.entity.EnrollingStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollingStudentRepository extends JpaRepository<EnrollingStudent, Long> {
}
