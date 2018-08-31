package bof;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.msgpack.core.MessageBufferPacker;
import org.msgpack.core.MessagePack;
import org.msgpack.core.buffer.MessageBuffer;
import org.msgpack.core.buffer.MessageBufferInput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Demo11 extends CacheDemo<Demo11.SingleMessageBufferInput> {

    @Override
    protected LoadingCache<UUID, SingleMessageBufferInput> createCache() {
        return CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build(new CacheLoader<UUID, SingleMessageBufferInput>() {
                    @Override
                    public SingleMessageBufferInput load(final UUID key) throws Exception {
                        System.out.println("Load Id: " + key);
                        return objects
                                .stream()
                                .filter(obj -> obj.getId().equals(key))
                                .findFirst()
                                .map(Demo11::pack)
                                .orElse(null);
                    }
                });
    }


    @RequiredArgsConstructor
    static class SingleMessageBufferInput implements MessageBufferInput {
        @NonNull
        private final MessageBuffer buffer;

        private boolean read = false;

        @Override
        public MessageBuffer next() throws IOException {
            if (read) {
                return null;
            }
            read = true;
            return buffer;
        }


        @Override
        public void close() throws IOException {
        }
    }

    private static SingleMessageBufferInput pack(CachableObject object) {
        try {
            MessageBufferPacker packer = MessagePack.newDefaultBufferPacker();
            packer.packString(object.getId().toString())
                    .packString(object.getName())
                    .packInt(object.getPrice())
                    .packArrayHeader(object.getTags().size());

            for (int i = 0; i < object.getTags().size(); i++) {
                packer.packString(object.getTags().get(i));
            }
            return new SingleMessageBufferInput(packer.toMessageBuffer());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static CachableObject unpack(SingleMessageBufferInput buffer) throws IOException {
        buffer.read = false;
        val unpacker = MessagePack.newDefaultUnpacker(buffer);
        val id = (UUID.fromString(unpacker.unpackString()));
        val name = (unpacker.unpackString());
        val price = (unpacker.unpackInt());
        val tagsCount = unpacker.unpackArrayHeader();
        val tags = new ArrayList<String>(tagsCount);
        for (int i = 0; i < tagsCount; i++) {
            tags.add(unpacker.unpackString());
        }

        return new CachableObject(name, id, price, tags);
    }

    @Override
    public CachableObject get(UUID uuid) {
        try {
            return unpack(cache.get(uuid));
        } catch (IOException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }
}
