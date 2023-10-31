package controller;

import domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.impl.WorkServiceImpl;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/work")
public class WorkController {

    @Autowired
    private WorkServiceImpl workService;

    // TODO: 2023-10-30  getWorkInfoById
    @GetMapping("/info")
    public ResponseEntity<Result> getWorkInfoById(@RequestParam @Valid String wid){
        return new ResponseEntity<>(Result.success(workService.getWorkInfoById(wid)), HttpStatus.OK);
    }

    //todo: updateReleaseStatus
    @GetMapping("/release")
    public ResponseEntity<Result> updateReleaseStatus(@RequestParam @Valid String wid){
        return new ResponseEntity<>(Result.success(workService.updateReleaseStatus(wid)), HttpStatus.OK);
    }

    //todo: updateEvaluateStatus
    @GetMapping("/evaluate")
    public ResponseEntity<Result> updateEvaluateStatus(@RequestParam @Valid String wid,
                                                       @RequestParam @Valid int status){
        return new ResponseEntity<>(Result.success(workService.updateEvaluateStatus(wid,status)), HttpStatus.OK);
    }

    //todo: checkOvertime

    //todo: updateWorkInfo

    //todo: uploadAttachments

    //todo: downloadAttachments

    //todo: submitWork

    //todo: statistics



}
