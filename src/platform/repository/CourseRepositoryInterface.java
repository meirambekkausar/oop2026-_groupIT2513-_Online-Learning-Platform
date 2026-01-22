package platform.repository;

import platform.entity.Course;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CourseRepositoryInterface {


    public Course findById(int id) throws SQLException;
    public Course save(String title) throws SQLException;
    List<Course> findAll();
}
