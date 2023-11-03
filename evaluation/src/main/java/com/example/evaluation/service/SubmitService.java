package com.example.evaluation.service;

import com.example.evaluation.controller.dto.SubmitDto;
import com.example.evaluation.controller.dto.TeacherEvaDto;
import org.springframework.web.multipart.MultipartFile;

public interface SubmitService {

    boolean submitWork(SubmitDto sd);

    boolean teacherEvaluation(TeacherEvaDto td);
}
