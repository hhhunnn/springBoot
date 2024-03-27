package com.huna.basic.service.implement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.huna.basic.dto.Request.Student.PostStudentRequestDto;
import com.huna.basic.entity.StudentEntity;
import com.huna.basic.repository.StudentRepository;
import com.huna.basic.service.StudentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImplement implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public ResponseEntity<String> postStudent(PostStudentRequestDto dto) {

        // 인스턴스 만드는 작업
        // 코드 지저분해짐
        // StudentEntity studentEntity = new StudentEntity();
        // studentEntity.setName(dto.getName());
        // studentEntity.setAge(dto.getAge());
        // studentEntity.setAddress(dto.getAddress());
        // studentEntity.setGraduation(dto.getGraduation());
        
        // CREATE (SQL : Insert)
        // 1. Entity 클래스의 인스턴스 생성
        // 2. 생성한 인스턴스를 repository.save() 메서드로 저장
        StudentEntity studentEntity = new StudentEntity(dto);
        // save() : 저장 및 수정 (덮어쓰기)
        studentRepository.save(studentEntity);
        // StudentEntity savedEntity = studentRepository.save(studentEntity);


        return ResponseEntity.status(HttpStatus.CREATED).body("성공!");
    }
    
}
