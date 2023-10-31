package service;

import domain.Homework;

public interface WorkService {

    Homework getWorkInfoById(String wid);

    boolean updateReleaseStatus(String wid);

    boolean updateEvaluateStatus(String wid, int status);
}
