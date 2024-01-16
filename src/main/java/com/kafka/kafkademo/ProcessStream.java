package com.kafka.kafkademo;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProcessStream {

    private static final Serde<String> STRING_SERDE = Serdes.String();
    private static final Serde<DataRequest> JSON_SERDE = new JsonSerde<>(DataRequest.class);

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {
        KStream<String, DataRequest> stream = streamsBuilder.stream("send",
                Consumed.with(STRING_SERDE, JSON_SERDE));

        stream.foreach((key, value) -> {
            log.error("Key: " + key + " Value: " + value);
        });

        KTable<String, String> concatStream = stream
                .mapValues(data -> data.getName() + ": " + data.getMessage())
                .toTable();

        concatStream.toStream().to("output", Produced.with(STRING_SERDE, STRING_SERDE));
    }
}
