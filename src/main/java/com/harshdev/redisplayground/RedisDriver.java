package com.harshdev.redisplayground;

import com.harshdev.redisplayground.transport.KryoObjectCodec;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;

public class RedisDriver {
    public RedisDriver(StatefulRedisClusterConnection<String, byte[]> connection, KryoObjectCodec kryoObjectCodec) {
        throw new UnsupportedOperationException("RedisDriver not supported");
    }
}
