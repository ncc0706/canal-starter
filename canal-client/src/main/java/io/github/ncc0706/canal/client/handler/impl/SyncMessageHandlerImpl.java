package io.github.ncc0706.canal.client.handler.impl;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import io.github.ncc0706.canal.client.handler.AbstractMessageHandler;
import io.github.ncc0706.canal.client.handler.EntryHandler;
import io.github.ncc0706.canal.client.handler.RowDataHandler;

import java.util.List;

public class SyncMessageHandlerImpl extends AbstractMessageHandler {

    public SyncMessageHandlerImpl(List<? extends EntryHandler> entryHandlers, RowDataHandler<CanalEntry.RowData> rowDataHandler) {
        super(entryHandlers, rowDataHandler);
    }

    @Override
    public void handleMessage(Message message) {
        super.handleMessage(message);
    }

}
