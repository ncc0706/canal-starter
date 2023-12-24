package io.github.ncc0706.canal.client.handler.impl;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import io.github.ncc0706.canal.client.handler.AbstractMessageHandler;
import io.github.ncc0706.canal.client.handler.EntryHandler;
import io.github.ncc0706.canal.client.handler.RowDataHandler;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class AsyncMessageHandlerImpl extends AbstractMessageHandler {

    private ExecutorService executor;

    public AsyncMessageHandlerImpl(List<? extends EntryHandler> entryHandlers, RowDataHandler<CanalEntry.RowData> rowDataHandler, ExecutorService executor) {
        super(entryHandlers, rowDataHandler);
        this.executor = executor;
    }

    @Override
    public void handleMessage(Message message) {
        executor.execute(() -> super.handleMessage(message));
    }
}
