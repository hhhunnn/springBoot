package com.huna.basic.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huna.basic.dto.Request.Student.PatchStudentRequestDto;
import com.huna.basic.dto.Request.Student.PostStudentRequestDto;
import com.huna.basic.service.StudentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    
    private final StudentService studentService;

    @PostMapping("/")
    public ResponseEntity<String> postStudent(
        // client에서 controller로 받아오는 것을 그대로 적음 requestBody / studentService에 있는 dto랑 헷갈리지 않기
        @RequestBody @Valid PostStudentRequestDto requestBody
    ) {
        ResponseEntity<String> response = studentService.postStudent(requestBody);
        return response;
    }

    // UPDATE
    @PatchMapping("/")
    public ResponseEntity<?> patchStudent (
        @RequestBody @Valid PatchStudentRequestDto requestBody
    ) {
        ResponseEntity<String> response = studentService.patchStudent(requestBody);
        return response;
    }

    // DELETE
    @DeleteMapping("/{studentNumber}")
    public ResponseEntity<String> deleteStudent (
        @PathVariable("studentNumber") Integer studentNumber
    ) {
        ResponseEntity<String> response = studentService.deleteStudent(studentNumber);
        return response;
    }

}
