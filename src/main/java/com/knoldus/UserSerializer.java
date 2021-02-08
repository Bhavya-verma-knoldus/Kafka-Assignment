package com.knoldus;

import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.kafka.common.serialization.Serializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class UserSerializer<Custom> implements Serializer<Custom> {

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public byte[] serialize(String topic, Custom data)
    {
        byte[] value = null;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        try
        {
            value = objectMapper.writeValueAsString(data).getBytes();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return value;
    }

    @Override public void close() {

    }

}