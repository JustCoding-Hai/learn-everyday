package com.example.learnredis.queue;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 队列消息
 * @create 2021/5/29 - 13:32
 **/
public class QueueMessage {
    private String id;
    private Object data;

    @Override
    public String toString() {
        return "QueueMessage{" +
                "id='" + id + '\'' +
                ", data=" + data +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
