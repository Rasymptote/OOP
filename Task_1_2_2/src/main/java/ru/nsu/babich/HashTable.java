package ru.nsu.babich;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Represents a hash table.
 *
 * @param <K> The key type.
 * @param <V> The value type.
 */
public class HashTable<K, V> implements Iterable<HashTable.Entry<K, V>> {
    private static final int DEFAULT_CAPACITY = 2;
    private static final double LOAD_FACTOR = 0.75;

    private int size;
    private int capacity;
    private int modCount;
    private Entry<K, V>[] buckets;

    /**
     * Represents a key-value pair stored in the hash table.
     */
    public static class Entry<K, V> {
        private final K key;
        private V value;
        private Entry<K, V> next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.format("%s=%s", key, value);
        }
    }

    /**
     * Constructs an empty hash table.
     */
    @SuppressWarnings("unchecked")
    public HashTable() {
        this.size = 0;
        this.capacity = DEFAULT_CAPACITY;
        this.modCount = 0;
        this.buckets = new Entry[capacity];
    }

    /**
     * Inserts a key-value pair into the hash table.
     * If the key already exists, its value is updated.
     *
     * @param key The key to insert.
     * @param value The value to associate with the key.
     */
    public void put(K key, V value) {
        int idx = hash(key);
        Entry<K, V> entry = buckets[idx];
        while (entry != null) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
            entry = entry.next;
        }
        if (++size > LOAD_FACTOR * capacity) {
            resize();
            idx = hash(key);
        }
        Entry<K, V> newEntry = new Entry<>(key, value);
        newEntry.next = buckets[idx];
        buckets[idx] = newEntry;
        modCount++;
    }

    /**
     * Returns the value associated with the specified key.
     *
     * @param key The key whose value is to be returned.
     * @return The value associated with the key, or {@code null} if not found.
     */
    public V get(Object key) {
        int idx = hash(key);
        Entry<K, V> entry = buckets[idx];
        while (entry != null) {
            if (Objects.equals(entry.key, key)) {
                return entry.value;
            }
            entry = entry.next;
        }
        return null;
    }

    /**
     * Removes the entry for the specified key from the table, if present.
     *
     * @param key A mapped key.
     */
    public void remove(Object key) {
        int idx = hash(key);
        Entry<K, V> entry = buckets[idx];
        Entry<K, V> prev = null;
        while (entry != null) {
            if (Objects.equals(entry.key, key)) {
                if (prev == null) {
                    buckets[idx] = entry.next;
                } else {
                    prev.next = entry.next;
                }
                size--;
                modCount++;
                return;
            }
            prev = entry;
            entry = entry.next;
        }
    }

    /**
     * Checks whether the specified kwy exists in the hash table.
     *
     * @param key The key to check for.
     * @return {@code true} if the key exists, {@code false} otherwise
     */
    public boolean containsKey(Object key) {
        int idx = hash(key);
        Entry<K, V> entry = buckets[idx];
        while (entry != null) {
            if (Objects.equals(entry.key, key)) {
                return true;
            }
            entry = entry.next;
        }
        return false;
    }

    public int size() {
        return this.size;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new HashTableIterator();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof HashTable<?, ?> other) || this.size != other.size) {
            return false;
        }
        for (var entry : this) {
            if (!other.containsKey(entry.key)
                    || !Objects.equals(entry.value, other.get(entry.key))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        var hash = 0;
        for (var entry : this) {
            hash += entry.hashCode();
        }
        return hash;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "{}";
        }
        StringBuilder builder = new StringBuilder().append("{");
        for (var entry : this) {
            builder.append(entry).append(", ");
        }
        builder.delete(builder.length() - 2, builder.length());
        builder.append("}");
        return builder.toString();
    }

    private int hash(Object key) {
        if (key == null) {
            return 0;
        }
        int h = key.hashCode();
        return (h ^ (h >>> 16)) & (capacity - 1);
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        capacity <<= 1;
        Entry<K, V>[] newBuckets = new Entry[capacity];

        for (var entry : buckets) {
            while (entry != null) {
                Entry<K, V> next = entry.next;
                int idx = hash(entry.key);
                entry.next = newBuckets[idx];
                newBuckets[idx] = entry;
                entry = next;
            }
        }
        buckets = newBuckets;
    }

    private class HashTableIterator implements Iterator<Entry<K, V>> {
        private int remaining;
        private int bucketIndex;
        private Entry<K, V> currentEntry;
        private final int expectedMod;

        private HashTableIterator() {
            remaining = size;
            bucketIndex = 0;
            currentEntry = null;
            expectedMod = modCount;
        }

        @Override
        public boolean hasNext() {
            return remaining > 0;
        }

        @Override
        public Entry<K, V> next() {
            if (expectedMod != modCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            while (currentEntry == null) {
                currentEntry = buckets[bucketIndex++];
            }
            Entry<K, V> entry = currentEntry;
            currentEntry = entry.next;
            remaining--;
            return entry;
        }
    }
}
