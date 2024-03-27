package com.huna.basic.service;

import org.springframework.http.ResponseEntity;

import com.huna.basic.dto.Request.Student.PostStudentRequestDto;

public interface StudentService {
    ResponseEntity<String> postStudent(PostStudentRequestDto dto);
}
