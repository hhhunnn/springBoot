package com.huna.basic.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huna.basic.dto.SampleDto;

import jakarta.validation.Valid;

@RestController
// HTTP * locahost:4000/request-data/**
@RequestMapping("/request-data") // /request-data의 '/' 안 써도 됨
public class RequestDataController2 {
    
    // @RequestParam() : GET, DELETE 처럼 URL로 데이터를 전송하는 메서드에서 
    //                      Query String 으로 지정된 데이터를 가져오기 위해 사용

    // HTTP GET localhost:4000/request-data/request-param
    @GetMapping("/request-param")
    // http://localhost:4000/request-data/request-param?userId=qwer&userName=gildong
    public String getRequestParam(
        // url에서 userId를 찾아서 String 뒤 변수userId에 넣어줌. 변수명 userId 아니어도 됨
        @RequestParam(name="userId") String userId,
        // required = false 필수가 아님
        @RequestParam(name="userName", required = false) String userName,
        @RequestParam() int userAge
    ) {
        return "사용자 아이디: " + userId + " / 사용자 이름 : " + userName + " / 사용자 나이 : " + userAge;
    }

    // @PathVariable() :  
    // 모든 HTTP 메서드에서 URL의 특정 패턴에 따라서 데이터를 추출하는 방식 (-> 많이 사용)

    // HTTP DELETE localhost:4000/request-data/path-variable
    // int로 작성할 경우 0이 들어오게 되는데 사용자가 0을 입력한 것인지 기본값이 들어온 건지 알 수 없게 됨
    // @DeleteMapping("/path-variable/{age}/{name}")

    // 여러개일 경우
    // http://localhost:4000/request-data/path-variable/10/gildong
    @DeleteMapping({
        "/path-variable/{age}/{name}",
        "/path-variable/{age}"
    })
    public String deletePathVariable(
        @PathVariable("age") Integer age,
        @PathVariable(name="name", required = false) String name
    ) {
        return "사용자 나이 : " + age + " / 사용자 이름 : " + name;
    }

    
    // HTTP PATCH localhost:4000/request-data/patch/gildong/update
    @PatchMapping("/patch/{userName}/update")
    public String patchUpdate(
        @PathVariable("userName") String userName
    ) {
        return "사용자 이름 : " + userName;
    }

    //! ******주의
    /* URL 패턴으로 데이터를 받아오는 방식을 썼을 때
     겹치는 패턴이 존재하는 지 잘 확인해야함 
     http://localhost:4000/request-data/get/get*/ 
    @GetMapping("/{value}/get")
    public String getPathVariable1 (
        @PathVariable("value") String value
    ) {
        return "getPathVariable1";
    }

    @GetMapping("/get/{value}")
    public String getPathVariable2 (
        @PathVariable("value") String value
    ) {
        return "getPathVariable2";
    }
    
    // @RequestBody() : 
    // - POST, PATCH, PUT 처럼 RequestBody로 데이터를 전송하는 메서드에서 데이터를 가져오기 위해 사용

    // HTTP POST localhost:4000/request-data/post
    @PostMapping("/post")
    public String post(
        // @RequestBody String text
        // @RequestBody SampleDto dto
        // @Valid 추가 : 해당 payload에 대해서 유효성 검사를 실시하도록 함
        @RequestBody @Valid SampleDto dto

    ) {
        return "전송한 데이터" + dto;
    }


}
