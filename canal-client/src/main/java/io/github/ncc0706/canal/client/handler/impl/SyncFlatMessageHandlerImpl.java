package io.github.ncc0706.canal.client.handler.impl;

import com.alibaba.otter.canal.protocol.FlatMessage;
import io.github.ncc0706.canal.client.handler.AbstractFlatMessageHandler;
import io.github.ncc0706.canal.client.handler.EntryHandler;
import io.github.ncc0706.canal.client.handler.RowDataHandler;

import java.util.List;
import java.util.Map;

public class SyncFlatMessageHandlerImpl extends AbstractFlatMessageHandler {

    public SyncFlatMessageHandlerImpl(List<? extends EntryHandler> entryHandlers, RowDataHandler<List<Map<String, String>>> rowDataHandler) {
        super(entryHandlers, rowDataHandler);
    }

    @Override
    public void handleMessage(FlatMessage flatMessage) {
        super.handleMessage(flatMessage);
    }
}
