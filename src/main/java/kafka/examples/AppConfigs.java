package kafka.examples;
/**
 * @author shashshe
 */
class AppConfigs {

    public final static String applicationID = "KStreamJoinDemo";
    public final static String applicationIDsimpleStream = "simpleStreamDemo";
    public final static String applicationIDWordCount = "WordCountDemo";
    public final static String bootstrapServers = "localhost:9092";
    public final static String paymentRequestTopicName = "payment_request";
    public final static String paymentConfirmationTopicName = "payment_confirmation";
    public final static String stateStoreName = "tmp/state-store";
    public final static String wordcountTopicName = "word_count";
}
