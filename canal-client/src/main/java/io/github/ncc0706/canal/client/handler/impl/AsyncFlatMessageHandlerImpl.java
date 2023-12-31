package io.github.ncc0706.canal.client.handler.impl;

import com.alibaba.otter.canal.protocol.FlatMessage;
import io.github.ncc0706.canal.client.handler.AbstractFlatMessageHandler;
import io.github.ncc0706.canal.client.handler.RowDataHandler;
import lombok.extern.slf4j.Slf4j;
import io.github.ncc0706.canal.client.handler.EntryHandler;


import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
public class AsyncFlatMessageHandlerImpl extends AbstractFlatMessageHandler {

    private ExecutorService executor;

    public AsyncFlatMessageHandlerImpl(List<? extends EntryHandler> entryHandlers, RowDataHandler<List<Map<String, String>>> rowDataHandler, ExecutorService executor) {
        super(entryHandlers, rowDataHandler);
        this.executor = executor;
    }

    @Override
    public void handleMessage(FlatMessage flatMessage) {
        ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor) executor;
        log.info("当前队列线程数 {} 堆积数量 {}",poolExecutor.getActiveCount(), poolExecutor.getQueue().size());
        executor.execute(() -> super.handleMessage(flatMessage));
    }
}
