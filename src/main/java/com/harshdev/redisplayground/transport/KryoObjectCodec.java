package com.harshdev.redisplayground.transport;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.CompatibleFieldSerializer;
import com.esotericsoftware.kryo.util.DefaultInstantiatorStrategy;
import org.objenesis.strategy.StdInstantiatorStrategy;

public class KryoObjectCodec implements ObjectCodec {

    private final ThreadLocal<Kryo> kryo = ThreadLocal.withInitial(KryoObjectCodec::createKyro);
    private static Kryo createKyro() {
        Kryo kryo = new Kryo();
        kryo.setInstantiatorStrategy(new DefaultInstantiatorStrategy(new StdInstantiatorStrategy()));
        kryo.setRegistrationRequired(false);
        kryo.setReferences(true);
        kryo.setDefaultSerializer(CompatibleFieldSerializer.class);
        return kryo;
    }

    @Override
    public byte[] serialize(Object obj) {
        Output output = new Output(1024, -1);
        kryo.get().writeObject(output, obj);
        return output.toBytes();
    }


    @Override
    public <T> T deserialize(byte[] bytes, Class<? extends T> claszz) {
        return kryo.get().readObject(new Input(bytes), claszz);
    }
}
