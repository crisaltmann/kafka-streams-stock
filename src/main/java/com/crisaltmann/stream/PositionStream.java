package com.crisaltmann.stream;

import org.apache.kafka.streams.StreamsBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PositionStream {

    public PositionStream(StreamsBuilder streamsBuilder,
                          @Value("${stock.inbound}") String inputTopic,
                          @Value("${stock.outbound}") String outputTopic) {


        streamsBuilder.stream(inputTopic)
                .to(outputTopic);

    }

}
