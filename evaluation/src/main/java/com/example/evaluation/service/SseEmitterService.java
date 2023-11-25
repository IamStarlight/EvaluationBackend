package com.example.evaluation.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
public interface SseEmitterService {
    SseEmitter createSseConnect(String clientId);

    void closeSseConnect(String clientId);

    // 根据客户端id获取SseEmitter对象
    SseEmitter getSseEmitterByClientId(String clientId);

    // 推送消息到客户端，此处结合业务代码，业务中需要推送消息处调用即可向客户端主动推送消息
//    void sendMsgToClient(List<SseEmitterResultVO> sseEmitterResultVOList);
}
