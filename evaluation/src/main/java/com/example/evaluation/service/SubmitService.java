package com.example.evaluation.service;

import com.example.evaluation.controller.dto.SubmitDto;
import com.example.evaluation.controller.dto.TeacherEvaDto;

public interface SubmitService {

    boolean submitWork(SubmitDto sd);

    boolean teacherEvaluation(TeacherEvaDto td);
}
