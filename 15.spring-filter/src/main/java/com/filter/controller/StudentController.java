package com.filter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.filter.entity.Student;


@RestController
@RequestMapping("/student")
public class StudentController {

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    @ResponseBody
    public Student getStudent() {
        Student student = new Student();
        student.setStudentId(1);
        student.setStudentName("John Smith");
        student.setMajor("Computer Science");

        return student;
    }

}