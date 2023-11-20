package com.example.evaluation.controller;

import com.example.evaluation.utils.Result;
import com.example.evaluation.server.WebSocketServer;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/websocket")
public class MessageController {
    @Autowired
    private WebSocketServer webSocketServer;

    /**
     * 模拟数据发送
     */
    @ApiOperation(value = "模拟数据发送", notes = "模拟数据发送")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "message", value = "模拟消息", required = true, dataType = "String"),
    })
    @RequestMapping(value = "/sendTestMessage", method = RequestMethod.GET)
    public Result sendTestMessage(String message) {
        Result result = new Result();
        try {
            webSocketServer.sendAllMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
            return result.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "发送消息失败。");
        }
        return result.success();
    }
    @RequestMapping(value = "/sendOneMessage", method = RequestMethod.GET)
    public Result sendOneMessage(String message,String userId) {
        Result result = new Result();
        try {
            webSocketServer.sendOneMessage(userId,message);
        } catch (Exception e) {
            e.printStackTrace();
            return result.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "发送消息失败。");
        }
        return result.success();
    }
}
