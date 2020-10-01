package com.learn.websocket02.bean;

/**
 * @author Hai
 * @date 2020/6/5 - 17:52
 */
public class PrivateMessage {
  private String from;
  private String to;
  private String content;

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
