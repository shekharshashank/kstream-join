export KAFKA_HOME=/Users/shashshe/kafka_2.12-2.5.0
$KAFKA_HOME/bin/kafka-console-producer.sh --broker-list localhost:9092 --topic payment_request --property parse.key=true --property key.separator=":"

