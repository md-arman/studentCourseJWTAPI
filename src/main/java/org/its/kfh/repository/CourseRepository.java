package org.its.kfh.repository;

import org.its.kfh.model.CourseDetails;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CourseRepository extends JpaRepository<CourseDetails, Integer> {


}
