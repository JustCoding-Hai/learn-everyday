package com.learn.websock01.controller;

import com.learn.websock01.bean.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * @author Hai
 * @date 2020/6/4 - 23:24
 */
@Controller
public class GreetingController {
  @MessageMapping("/hello")
  @SendTo("/topic/greetings")
  public Message greeting(Message message){
    return message;
  }
}
