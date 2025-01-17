package com.knoldus;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import java.util.Map;

public class UserDeserializer<Custom> implements Deserializer<Custom> {

    @Override public void close() {

    }

    @Override public void configure(Map<String, ?> arg0, boolean arg1) {

    }

    @Override
    public Custom deserialize(String arg0, byte[] data)
    {
        ObjectMapper mapper = new ObjectMapper();
        User user = null;
        try
        {
            user = mapper.readValue(data, User.class);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return (Custom) user;
    }

}
