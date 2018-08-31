package bof;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Demo10 extends CacheDemo<CachableObject> {
    @Override
    protected LoadingCache<UUID, CachableObject> createCache() {
        return CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build(new CacheLoader<UUID, CachableObject>() {
                    @Override
                    public CachableObject load(final UUID key) throws Exception {
                        System.out.println("load key: " + key);
                        return objects.stream().filter(obj -> obj.getId().equals(key)).findFirst().orElse(null);
                    }
                });
    }

    @Override
    public CachableObject get(UUID uuid) {
        try {
            return cache.get(uuid);
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }
}
