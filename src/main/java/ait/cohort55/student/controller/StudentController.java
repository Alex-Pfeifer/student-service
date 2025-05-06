package ait.cohort55.student.controller;

import ait.cohort55.student.dto.ScoreDto;
import ait.cohort55.student.dto.StudentAddDto;
import ait.cohort55.student.dto.StudentDto;
import ait.cohort55.student.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class StudentController {

    private StudentService studentService;

    @PostMapping("/student")
    public Boolean addStudent(@RequestBody StudentAddDto studentAddDto) {
        return studentService.addStudent(studentAddDto);
    }

    @GetMapping("/student/{id}")
    public StudentDto findStudentById(@PathVariable Long id) {
        return studentService.findStudentById(id);
    }

    @DeleteMapping("/student/{id}")
    public StudentDto removeStudentById(@PathVariable Long id) {
        return studentService.removeStudentById(id);
    }

    @PostMapping("/score/student/{id}")
    public StudentAddDto updateStudent(@PathVariable Long id, @RequestBody StudentAddDto studentAddDto) {
        return studentService.updateStudent(id, studentAddDto);
    }

    @PostMapping("/score/student/{id}")
    public Boolean addScore(@PathVariable Long id, @RequestBody ScoreDto scoreDto) {
        return studentService.addScore(id, scoreDto);
    }

    @GetMapping("/quantity/student")
    public List<StudentDto> findAllStudents(@RequestParam String name) {
        return studentService.findStudentByName(name);
    }

    @GetMapping
    public Long getStudentQuantityByName(@RequestParam Set<String> names) {
        return studentService.getStudentQuantityByName(new HashSet<>(names));
    }

    @GetMapping
    public List<StudentDto> findStudentByExamMinScore(@PathVariable Long examMinScore, @PathVariable Integer minScore) {
        return studentService.findStudentByExamMinScore(examMinScore, minScore);
    }
}
