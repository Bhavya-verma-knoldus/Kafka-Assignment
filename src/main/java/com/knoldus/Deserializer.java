package com.knoldus;

import java.io.Closeable;
import java.util.Map;

public interface Deserializer<Custom> extends Closeable
{
    void configure(Map<String, ?> var1, boolean var2);

    Custom deserialize(String var1, byte[] var2);

    void close();
}