package com.example.evaluation.controller;

import com.example.evaluation.server.SseEmitterServer;
import com.example.evaluation.utils.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/sse")
public class SseController {
    /**
     * 用于创建连接
     */
    @PostMapping("/connect")
    public SseEmitter connect(@RequestParam String userId) {
        return SseEmitterServer.connect(userId);
    }

    @PostMapping("/close")
    public ResponseEntity<Result> close(@RequestParam String userId) {
        return ResponseEntity.ok(Result.success(SseEmitterServer.completionCallBack(userId)));
    }

//    @GetMapping("/push")
//    public ResponseEntity<String> push(@RequestParam String message) {
//        SseEmitterServer.batchSendMessage(message);
//        return ResponseEntity.ok("WebSocket 推送消息给所有人");
//    }
}

