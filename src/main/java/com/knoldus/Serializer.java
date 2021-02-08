package com.knoldus;

import java.io.Closeable;
import java.util.Map;

public interface Serializer<Custom> extends Closeable
{
    void configure(Map<String, ?> var1, boolean var2);

    byte[] serialize(String var1, Custom var2);

    void close();
}