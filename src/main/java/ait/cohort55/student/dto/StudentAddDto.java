package ait.cohort55.student.dto;

import lombok.Getter;

@Getter
public class StudentAddDto {
     private Long id;
     private String name;
     private String password;

     public StudentAddDto(long id, String name, String password) {
     }
}
