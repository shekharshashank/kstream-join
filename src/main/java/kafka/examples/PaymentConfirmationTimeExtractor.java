package kafka.examples;

import kafka.examples.types.PaymentConfirmation;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.processor.TimestampExtractor;

import java.time.Instant;

/**
 * @author shashshe
 */
public class PaymentConfirmationTimeExtractor implements TimestampExtractor{
        @Override
        public long extract(ConsumerRecord<Object, Object> consumerRecord, long prevTime) {
            PaymentConfirmation record = (PaymentConfirmation) consumerRecord.value();
            long eventTime = Instant.parse(record.getCreatedTime()).toEpochMilli();
            return ((eventTime > 0) ? eventTime : prevTime);
        }
}
