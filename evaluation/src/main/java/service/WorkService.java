package service;

import com.oracle.net.Sdp;
import controller.dto.SubmitDto;
import controller.dto.WorkDto;
import domain.Homework;

import java.util.Date;

public interface WorkService {

    Homework getWorkInfoById(String wid);

    boolean updateReleaseStatus(String wid);

    boolean updateEvaluateStatus(String wid, int status);

    boolean checkOvertime(String wid, String cid, Date submitTime);

    boolean updateWorkInfo(WorkDto wd);
}
