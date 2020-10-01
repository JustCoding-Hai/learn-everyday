package com.learn.websocket02.controller;

import com.learn.websocket02.bean.Message;
import com.learn.websocket02.bean.PrivateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * @author Hai
 * @date 2020/6/4 - 23:24
 */
@Controller
public class GreetingController {
  @Autowired
  SimpMessagingTemplate simpMessagingTemplate;
  /**
   * 群聊
   * @param message
   * @return
   */
  @MessageMapping("/hello")
  @SendTo("/topic/greetings")
  public Message greeting(Message message){
    return message;
  }

  /**
   * 私聊
   * @param principal 登录用户
   * @param message 私聊信息实体
   */
  @MessageMapping("/privateChat")
  public void privateChat(Principal principal, PrivateMessage message){
    message.setFrom(principal.getName());

    simpMessagingTemplate.convertAndSendToUser(message.getTo(),"/queue/chat",message);
  }
}
