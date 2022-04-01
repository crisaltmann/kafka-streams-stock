package com.crisaltmann.config;

import org.apache.kafka.common.serialization.Serdes;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;

import java.util.Map;

import static org.apache.kafka.clients.consumer.ConsumerConfig.AUTO_OFFSET_RESET_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG;

@EnableKafkaStreams
@Configuration
public class StreamConfiguration {

    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public KafkaStreamsConfiguration kafkaStreamConfig(final KafkaProperties kafkaProperties) {

        Map<String, Object> streamsProperties = kafkaProperties.buildStreamsProperties();

        streamsProperties.put(AUTO_OFFSET_RESET_CONFIG, "latest");
        streamsProperties.put(DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.ByteArray().getClass().getName());
//        streamsProperties.put(DEFAULT_VALUE_SERDE_CLASS_CONFIG, JsonSerde.class);
        streamsProperties.put(DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());

        return new KafkaStreamsConfiguration(streamsProperties);
    }

}
