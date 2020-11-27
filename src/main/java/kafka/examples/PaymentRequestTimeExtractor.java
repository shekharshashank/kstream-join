package kafka.examples;

import kafka.examples.types.PaymentRequest;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.processor.TimestampExtractor;

import java.time.Instant;

/**
 * @author shashshe
 */
public class PaymentRequestTimeExtractor implements TimestampExtractor {

    @Override
    public long extract(ConsumerRecord<Object, Object> consumerRecord, long prevTime) {
        PaymentRequest record = (PaymentRequest) consumerRecord.value();
        long eventTime = Instant.parse(record.getCreatedTime()).toEpochMilli();
        return ((eventTime > 0) ? eventTime : prevTime);
    }
}
