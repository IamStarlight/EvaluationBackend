package com.example.evaluation.controller;

import com.example.evaluation.entity.Result;
import com.example.evaluation.service.impl.ScServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/sc")
public class ScController {

    @Autowired
    private ScServiceImpl service;

    @PostMapping("/delete")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER')")
    public ResponseEntity<Result> deleteScStu(@RequestParam @Valid Integer sid,
                                              @RequestParam @Valid Integer cid){
        return new ResponseEntity<>(Result.success(service.deleteScStu(sid,cid)), HttpStatus.OK);
    }

    @GetMapping("/add")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER')")
    public ResponseEntity<Result> addScStu(@RequestParam @Valid Integer sid,
                                           @RequestParam @Valid Integer cid){
        return new ResponseEntity<>(Result.success(service.addScStu(sid,cid)), HttpStatus.OK);
    }

}
