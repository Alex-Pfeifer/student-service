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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

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
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        studentRepository.deleteById(id);
        return new StudentDto(student.getId(), student.getName(), student.getScores());
    }

    @Override
    public StudentAddDto updateStudent(Long id, StudentUpdateDto studentUpdateDto) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        student.setName(studentUpdateDto.getName());
        student.setPassword(studentUpdateDto.getPassword());
        studentRepository.save(student);
        return new StudentAddDto();
    }

    @Override
    public Boolean addScore(Long id, ScoreDto scoreDto) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        student.addScore(scoreDto.getExamName(), scoreDto.getScore());
        studentRepository.save(student);
        return true;
    }

    @Override
    public List<StudentDto> findStudentsByName(String name) {
        List<StudentDto> result = new ArrayList<>();
        for (Student student : studentRepository.findAll()) {
            if (student.getName().equals(name)) {
                result.add(new StudentDto(student.getId(), student.getName(), student.getScores()));
            }
        }
        return result;
    }

    @Override
    public Long getStudentsQuantityByNames(Set<String> names) {
        long result = 0;
        for (Student student : studentRepository.findAll()) {
            if (names.contains(student.getName())) {
                result++;
            }
        }
        return result;
    }

    @Override
    public List<StudentDto> findStudentsByExamNameMinScore(String exam, Integer minScore) {
        List<StudentDto> result = new ArrayList<>();
        for (Student student : studentRepository.findAll()) {
            if (student.getScores().getOrDefault(exam, 0) >= minScore) {
                result.add(new StudentDto(student.getId(), student.getName(), student.getScores()));
            }
        }
        return result;
    }
}