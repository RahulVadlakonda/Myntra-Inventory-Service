package com.myntra.api.inventory.exception;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.errors.RecordDeserializationException;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Component;

import com.myntra.api.inventory.logger.ServiceLogger;

@Component
public class KafkaListnerErrorHandler implements CommonErrorHandler {

	@Override
    public boolean handleOne(Exception exception, ConsumerRecord<?, ?> record, Consumer<?, ?> consumer, MessageListenerContainer container) {
        handle(exception, consumer);
        return true;
    }

    @Override
    public void handleOtherException(Exception exception, Consumer<?, ?> consumer, MessageListenerContainer container, boolean batchListener) {
        handle(exception, consumer);
    }

    private void handle(Exception exception, Consumer<?, ?> consumer) {
        ServiceLogger.info(getClass(), "Inside handle method with exception: " + exception);
        if (exception instanceof RecordDeserializationException ex) {
            consumer.seek(ex.topicPartition(), ex.offset() + 1L);
            consumer.commitSync();
        } 
        else {
        	ServiceLogger.error(getClass(), "Exception thrown" + exception);
        }
    }
}
