package io.github.ncc0706.autoconfigure;


import io.github.ncc0706.canal.client.client.KafkaCanalClient;
import io.github.ncc0706.canal.client.factory.MapColumnModelFactory;
import io.github.ncc0706.canal.client.handler.EntryHandler;
import io.github.ncc0706.canal.client.handler.MessageHandler;
import io.github.ncc0706.canal.client.handler.RowDataHandler;
import io.github.ncc0706.canal.client.handler.impl.AsyncFlatMessageHandlerImpl;
import io.github.ncc0706.canal.client.handler.impl.MapRowDataHandlerImpl;
import io.github.ncc0706.canal.client.handler.impl.SyncFlatMessageHandlerImpl;
import io.github.ncc0706.properties.CanalKafkaProperties;
import io.github.ncc0706.properties.CanalProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

@Configuration
@EnableConfigurationProperties(CanalKafkaProperties.class)
@ConditionalOnBean(value = {EntryHandler.class})
@ConditionalOnProperty(value = CanalProperties.CANAL_MODE, havingValue = "kafka")
@Import(ThreadPoolAutoConfiguration.class)
public class KafkaClientAutoConfiguration {

    @Bean
    public RowDataHandler<List<Map<String, String>>> rowDataHandler() {
        return new MapRowDataHandlerImpl(new MapColumnModelFactory());
    }

    @Bean
    @ConditionalOnProperty(value = CanalProperties.CANAL_ASYNC, havingValue = "true", matchIfMissing = true)
    public MessageHandler messageHandler(RowDataHandler<List<Map<String, String>>> rowDataHandler,
                                         List<EntryHandler> entryHandlers,
                                         ExecutorService executorService) {
        return new AsyncFlatMessageHandlerImpl(entryHandlers, rowDataHandler, executorService);
    }

    @Bean
    @ConditionalOnProperty(value = CanalProperties.CANAL_ASYNC, havingValue = "false")
    public MessageHandler messageHandler(RowDataHandler<List<Map<String, String>>> rowDataHandler, List<EntryHandler> entryHandlers) {
        return new SyncFlatMessageHandlerImpl(entryHandlers, rowDataHandler);
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public KafkaCanalClient kafkaCanalClient(MessageHandler messageHandler, CanalKafkaProperties canalKafkaProperties) {
        return KafkaCanalClient.builder().servers(canalKafkaProperties.getServer())
                .groupId(canalKafkaProperties.getGroupId())
                .topic(canalKafkaProperties.getDestination())
                .messageHandler(messageHandler)
                .batchSize(canalKafkaProperties.getBatchSize())
                .filter(canalKafkaProperties.getFilter())
                .timeout(canalKafkaProperties.getTimeout())
                .unit(canalKafkaProperties.getUnit())
                .build();
    }

}
