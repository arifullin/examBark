package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.StudentGroup;

import java.util.List;

/**
 * Created by vladislav on 18.04.17.
 */
@Repository
public interface StudentGroupRepository extends JpaRepository<StudentGroup, Long>{
    List<StudentGroup> findByCourse(int course);
    StudentGroup findByCourseAndNum(int course, int num);
}
