package controller;

import domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.impl.CourseServiceImpl;

@RestController
@Validated
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseServiceImpl courseService;

    @GetMapping("/info")
    public ResponseEntity<Result> getCourseInfo(){
        return new ResponseEntity<>(Result.success(courseService.getAllCourseInfo()), HttpStatus.OK);
    }
}
