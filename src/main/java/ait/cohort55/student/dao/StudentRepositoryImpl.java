package ait.cohort55.student.dao;

import ait.cohort55.student.model.Student;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
    private final Map<Long, Student> students = new ConcurrentHashMap<>();

    @Override
    public Student save(Student student) {
        students.put(student.getId(), student);
        return student;
    }

    @Override
    public Optional<Student> findById(Long id) {
        return Optional.ofNullable(students.get(id));
    }

    @Override
    public void deleteById(Long id) {
        students.remove(id);
    }

    @Override
    public Iterable<Student> findAll() {
        return students.values();
    }
}
