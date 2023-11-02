package com.example.evaluation.service;

import com.example.evaluation.controller.dto.SubmitDto;
import org.springframework.stereotype.Service;

@Service
public interface SubmitService {

    boolean submitWork(SubmitDto sd);
}
