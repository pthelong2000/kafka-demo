package com.kafka.kafkademo;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;

@Component
public class ProcessStream {

    private static final Serde<String> STRING_SERDE = Serdes.String();
    private static final Serde<Data> JSON_SERDE = new JsonSerde<>(Data.class);

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {
        KStream<String, Data> stream = streamsBuilder.stream("send",
                Consumed.with(STRING_SERDE, JSON_SERDE));

        KTable<String, String> concatStream = stream
                .mapValues(data -> data.getName() + ": " + data.getMessage())
                .toTable();

        concatStream.toStream().to("output");
    }
}
