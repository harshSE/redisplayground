package com.harshdev.redisplayground;

import com.harshdev.redisplayground.transport.KryoObjectCodec;
import io.lettuce.core.RedisURI;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.codec.ByteArrayCodec;
import io.lettuce.core.codec.RedisCodec;
import io.lettuce.core.codec.StringCodec;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

public class RedisplayGround {

    public static void main(String[] args) {
        String[] ips = {"10.30.150.219:7000", "10.30.150.217:7000", "10.30.150.218:7000"};
        List<RedisURI> uris = Arrays
                .stream(ips)
                .map(val -> new RedisURI(val.split(":")[0], parseInt(val.split(":")[1]), Duration.ofSeconds(10)))
                .collect(Collectors.toList());
        RedisClusterClient client = RedisClusterClient.create(uris);
        RedisCodec<String, byte[]> redisCoded = RedisCodec.of(StringCodec.UTF8, new ByteArrayCodec());
        StatefulRedisClusterConnection<String, byte[]> connection = client.connect(redisCoded);

        RedisDriver redisDriver = new RedisDriver(connection, new KryoObjectCodec());
    }
}
