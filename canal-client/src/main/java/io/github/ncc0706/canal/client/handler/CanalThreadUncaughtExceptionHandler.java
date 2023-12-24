package io.github.ncc0706.canal.client.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CanalThreadUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(CanalThreadUncaughtExceptionHandler.class);

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        logger.error("thread " + t.getName() + " have a exception", e);
    }
}
