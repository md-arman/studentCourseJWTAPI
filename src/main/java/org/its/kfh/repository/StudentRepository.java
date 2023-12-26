package org.its.kfh.repository;

import org.its.kfh.model.StudentDetails;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<StudentDetails, Integer> {

}
