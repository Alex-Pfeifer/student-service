package ait.cohort55.student.service;

import ait.cohort55.student.dao.StudentRepository;
import ait.cohort55.student.dto.ScoreDto;
import ait.cohort55.student.dto.StudentAddDto;
import ait.cohort55.student.dto.StudentDto;
import ait.cohort55.student.dto.StudentUpdateDto;
import ait.cohort55.student.dto.exceptions.StudentNotFoundException;
import ait.cohort55.student.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Boolean addStudent(StudentAddDto studentAddDto) {
        if (studentRepository.findById(studentAddDto.getId()).isPresent()) {
            return false;
        }
        Student student = new Student(studentAddDto.getId(), studentAddDto.getName(), studentAddDto.getPassword());
        studentRepository.save(student);
        return true;
    }

    @Override
    public StudentDto findStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        return new StudentDto(student.getId(), student.getName(), student.getScores());
    }

    @Override
    public StudentDto removeStudent(Long id) {
        return null;
    }

    @Override
    public StudentAddDto updateStudent(Long id, StudentUpdateDto studentUpdateDto) {
        return null;
    }

    @Override
    public Boolean addScore(Long id, ScoreDto scoreDto) {
        return null;
    }

    @Override
    public List<StudentDto> findStudentsByName(String name) {
        return List.of();
    }

    @Override
    public Long getStudentsQuantityByNames(Set<String> names) {
        return 0L;
    }

    @Override
    public List<StudentDto> findStudentsByExamNameMinScore(String exam, Integer minScore) {
        return List.of();
    }
}