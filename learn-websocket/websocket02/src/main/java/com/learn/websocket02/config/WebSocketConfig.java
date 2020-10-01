package com.learn.websocket02.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author Hai
 * @date 2020/6/4 - 23:15
 */
@Configuration
@EnableWebSocketMessageBroker//@EnableWebSocketMessageBroker注解表示开启使用STOMP协议来传输基于代理的消息，Broker就是代理的意思。
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  /**
   * configureMessageBroker方法用来配置消息代理，由于我们是实现推送功能，这里的消息代理是/topic
   * 作用：1.消息代理会处理前缀为“/topic”的消息
   *      2.发往客户端的消息将会带有“/app”前缀
   * @param registry
   */
  @Override
  public void configureMessageBroker(MessageBrokerRegistry registry) {
    registry.enableSimpleBroker("/topic","/queue");
    registry.setApplicationDestinationPrefixes("/app");
  }

  /**
   * 作用：注册STOMP协议的节点（端点），并指定映射的URL。
   * 将“/chat”注册为端点，客户端订阅消息或发送消息到目的地前要连接该端点
   * @param registry
   */
  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    //注册STOMP协议节点，同时指定使用SockJS协议。
    registry.addEndpoint("/chat").withSockJS();
  }
}
