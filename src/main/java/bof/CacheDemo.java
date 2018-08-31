package bof;

import com.google.common.cache.LoadingCache;
import lombok.val;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

abstract class CacheDemo<T> extends Worker implements MemCache<UUID, CachableObject> {
    final List<CachableObject> objects = new ArrayList<>();
    final LoadingCache<UUID, T> cache;

    CacheDemo() {
        for (int i = 0; i < 10; i++) {
            val id = UUID.randomUUID();
            objects.add(CachableObject.builder()
                    .id(id)
                    .name(String.format("Foo %d", i))
                    .price(i * 100)
                    .tags(Arrays.asList("Hello", "World"))
                    .build());
        }
        cache = createCache();
    }

    protected abstract LoadingCache<UUID, T> createCache();

    @Override
    protected final void doWork() {
        for (int i = 0; i < 100_000; i++) {
            for (int j = 0; j < objects.size(); j++) {
                val obj = this.get(objects.get(j).getId());
                if (obj == null) {
                    throw new NullPointerException("Cache returns null.");
                }
            }
        }
    }
}
