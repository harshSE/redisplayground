package com.harshdev.redisplayground.transport;

import java.io.IOException;

public interface ObjectCodec {
    public byte[] serialize(Object obj) throws IOException;
    public <T> T deserialize(byte[] bytes, Class<? extends T> claszz) throws DeserializationException;
}
