package kafka.examples;

import com.fasterxml.jackson.core.JsonProcessingException;
import kafka.examples.serde.AppSerdes;
import kafka.examples.types.MostFrequentWords;
import kafka.examples.types.WordCount;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueStore;

import java.util.Arrays;
import java.util.Properties;

/**
 * @author shashshe
 */
public class KtableWordCountDemo {
    public static void main(String[] args){
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfigs.applicationIDWordCount);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        props.put(StreamsConfig.STATE_DIR_CONFIG, AppConfigs.stateStoreName);
        props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 0);
        props.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, 1);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,Serdes.String().getClass());

        StreamsBuilder streamsBuilder = new StreamsBuilder();
        KStream<String,String> inputStream  = streamsBuilder.stream(AppConfigs.wordcountTopicName);
        inputStream.foreach((k,v)-> System.out.println(v));

        KStream<String,String> wordStream = inputStream.flatMapValues(v-> Arrays.asList(v.toLowerCase().split(" ")));

        KGroupedStream<String,String> kGroupedStream = wordStream.groupBy((k,v)->v);
        KTable<String,Long> kCountTable = kGroupedStream.count();
        kCountTable.toStream().print(Printed.<String,Long>toSysOut());
/*

        kCountTable.groupBy((k, v) -> {
            WordCount wc = new WordCount();
            wc.setWord(k);
            wc.setCount(v);
            return KeyValue.pair("frequentWords", wc);
        }, Grouped.with(AppSerdes.String(), AppSerdes.WordCount()))
                .aggregate(
                        //init
                        () -> new MostFrequentWords(),
                        //add
                        (k, newWC, mostFrequentWords) -> {
                            mostFrequentWords.add(newWC);
                            return mostFrequentWords;
                        },
                        //remove
                        (k, oldWC, mostFrequentWords) -> {
                            mostFrequentWords.remove(oldWC);
                            return mostFrequentWords;
                        },
                        //serializer
                        Materialized.<String, MostFrequentWords, KeyValueStore<Bytes, byte[]>>
                                as("top3-words")
                                .withKeySerde(AppSerdes.String())
                                .withValueSerde(AppSerdes.MostFrequentWords())

                ).toStream().foreach((k,v)->{
            try {
                System.out.println("k"+"==="+v.getSortedMostFrequentWords());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
*/

        Topology topology = streamsBuilder.build();
        KafkaStreams streams = new KafkaStreams(topology,props);

        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("sutting down stream");
            streams.close();
        }));


    }
}
