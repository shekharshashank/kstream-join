package kafka.examples.serde;

import kafka.examples.types.MostFrequentWords;
import kafka.examples.types.PaymentConfirmation;
import kafka.examples.types.PaymentRequest;
import kafka.examples.types.WordCount;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

import java.util.HashMap;
import java.util.Map;

public class AppSerdes extends Serdes {

//   static public final class LongSerde extends WrapperSerde<Long> {
//        public LongSerde() {
//            super(new LongSerializer(), new LongDeserializer());
//        }
//    }
//
//
//    static public Serde<Long> Long() {
//        return new Serdes.LongSerde();
//    }
//
//

    static final class PaymentRequestSerde extends WrapperSerde<PaymentRequest> {
        PaymentRequestSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<PaymentRequest> PaymentRequest() {
        PaymentRequestSerde serde = new PaymentRequestSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, PaymentRequest.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

    static final class PaymentConfirmationSerde extends WrapperSerde<PaymentConfirmation> {
        PaymentConfirmationSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<PaymentConfirmation> PaymentConfirmation() {
        PaymentConfirmationSerde serde = new PaymentConfirmationSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, PaymentConfirmation.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }


    static final class WordCountSerde extends WrapperSerde<WordCount> {
        WordCountSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<WordCount> WordCount() {
        WordCountSerde serde = new WordCountSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, WordCount.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

    static final class MostFrequentWordsSerde extends WrapperSerde<MostFrequentWords> {
        MostFrequentWordsSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<MostFrequentWords> MostFrequentWords() {
        MostFrequentWordsSerde serde = new MostFrequentWordsSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, MostFrequentWords.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

}
