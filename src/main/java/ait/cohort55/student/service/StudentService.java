package ait.cohort55.student.service;

import ait.cohort55.student.dto.ScoreDto;
import ait.cohort55.student.dto.StudentAddDto;
import ait.cohort55.student.dto.StudentDto;

import java.util.List;
import java.util.Set;

public interface StudentService {
    Boolean addStudent(StudentAddDto studentAddDto);
    StudentDto findStudentById(Long id);
    StudentDto removeStudentById(Long id);
    StudentAddDto updateStudent(Long id, StudentAddDto studentAddDto);
    Boolean addScore(Long id, ScoreDto scoreDto);
    List<StudentDto> findAllStudents(String name);
    Long getStudentQuantityByName(Set<String> names);
    List<StudentDto> findStudentByExamMinScore(Long examMinScore, Integer minScore);


}
