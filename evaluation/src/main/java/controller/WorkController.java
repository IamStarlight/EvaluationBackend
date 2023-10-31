package controller;

import controller.dto.LoginDto;
import controller.dto.SubmitDto;
import controller.dto.WorkDto;
import domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import service.SubmitService;
import service.impl.SubmitServiceImpl;
import service.impl.WorkServiceImpl;

import javax.validation.Valid;
import java.util.Date;

@RestController
@Validated
@RequestMapping("/work")
public class WorkController {

    @Autowired
    private WorkServiceImpl workService;

    @Autowired
    private SubmitServiceImpl submitService;

    @GetMapping("/info")
    public ResponseEntity<Result> getWorkInfoById(@RequestParam @Valid String wid){
        return new ResponseEntity<>(Result.success(workService.getWorkInfoById(wid)), HttpStatus.OK);
    }

    @PostMapping("/release")
    public ResponseEntity<Result> updateReleaseStatus(@RequestParam @Valid String wid){
        return new ResponseEntity<>(Result.success(workService.updateReleaseStatus(wid)), HttpStatus.OK);
    }

    @PostMapping("/evaluate")
    public ResponseEntity<Result> updateEvaluateStatus(@RequestParam @Valid String wid,
                                                       @RequestParam @Valid int status){
        return new ResponseEntity<>(Result.success(workService.updateEvaluateStatus(wid,status)), HttpStatus.OK);
    }

    @PostMapping("/edit")
    public ResponseEntity<Result> updateWorkInfo(@RequestBody @Valid WorkDto workDto){
        return new ResponseEntity<>(Result.success(workService.updateWorkInfo(workDto)), HttpStatus.OK);
    }

    @PostMapping("/submit")
    public ResponseEntity<Result> submitWork(@RequestBody @Valid SubmitDto submitDto){
        return new ResponseEntity<>(Result.success(submitService.submitWork(submitDto)), HttpStatus.OK);
    }

    // TODO: 2023-10-31 uploadAttachments 

    // TODO: 2023-10-31 downloadAttachments 

    // TODO: 2023-10-31 teacherEvaluation

    // TODO: 2023-10-31 statistics 



}
