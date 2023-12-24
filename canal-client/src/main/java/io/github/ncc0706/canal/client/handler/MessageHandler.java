package io.github.ncc0706.canal.client.handler;

public interface MessageHandler<T> {
    void handleMessage(T t);
}
