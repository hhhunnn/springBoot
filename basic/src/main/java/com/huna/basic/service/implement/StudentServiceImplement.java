package com.huna.basic.service.implement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.huna.basic.dto.Request.Student.PatchStudentRequestDto;
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

    @Override
    public ResponseEntity<String> patchStudent(PatchStudentRequestDto dto) {

        Integer studentNumber = dto.getStudentNumber();
        String address = dto.getAddress();

        // 0. student 테이블에 해당하는 Primary key를 가지는 레코드가 존재하는지 확인
        boolean isExistedStudent = studentRepository.existsById(studentNumber);
        if (!isExistedStudent) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("존재하지 않는 학생입니다.");

        // 1. student 테이블로 접근 (StudentRepository 사용)
        StudentEntity studentEntity = studentRepository.
        // 2. dto.studentNumber에 해당하는 레코드를 검색
        findById(studentNumber).get();
        // 3. 검색된 레코드의 address 값을 dto.address로 변경
        studentEntity.setAddress(address);
        // 4. 변경한 인스턴스를 데이터베이스에 저장
        // repository.save()는 레코드를 생성할 때 쓰이지만 수정할 때도 동일하게 사용됨
        studentRepository.save(studentEntity);

        return ResponseEntity.status(HttpStatus.OK).body("성공!");
    }

    @Override
    public ResponseEntity<String> deleteStudent (Integer studentNumber) {

        studentRepository.deleteById(studentNumber);
        return ResponseEntity.status(HttpStatus.OK).body("성공");
    }
    
}
