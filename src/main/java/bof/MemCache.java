package bof;

public interface MemCache<TKey, TValue> {
    TValue get(TKey key);
}
