package logic;


import java.util.HashSet;
import java.util.Set;

public class MyCustomHashMap<K, V> {
    private Entry<K, V>[] entries;
    private int initialCapacity = 16;

    @SuppressWarnings("unchecked")
    public MyCustomHashMap() {
        entries = new Entry[initialCapacity];
    }

    @SuppressWarnings("unchecked")
    public MyCustomHashMap(int initialCapacity) {
        entries = new Entry[initialCapacity];
    }

    public void put(K key, V value) {
        if (key == null) {
            throw new RuntimeException("key can't be null!");
        }
        Entry<K, V> entry = new Entry<K, V>(key, value, null);
        int bucketIndex = calcIndex(key);
        if (entries[bucketIndex] == null) {
            entries[bucketIndex] = entry;
        } else {
            Entry<K, V> previous = null;
            Entry<K, V> current = entries[bucketIndex];
            while (current != null) {
                if (current.getK().equals(key)) {
                    if (previous == null) {
                        entry.setNext(current.getNext());
                        entries[bucketIndex] = entry;
                    } else {
                        entry.setNext(current.getNext());
                        previous.setNext(entry);
                    }
                }
                previous = current;
                current = current.getNext();
            }
            previous.setNext(entry);
        }
    }

    public V get(K key) {
        int bucketIndex = calcIndex(key);
        if (entries[bucketIndex] == null) {
            return null;
        } else {
            Entry<K, V> forGet = entries[bucketIndex];
            while (forGet != null)
                if (forGet.getK().equals(key)) {
                    return forGet.getV();
                }
        }
        return null;
    }

    public boolean remove(K key) {
        if (key == null) {
            throw new RuntimeException("key can't be null!");
        }
        int bucketIndex = calcIndex(key);
        if (entries[bucketIndex] == null) {
            return false;
        } else {
            Entry<K, V> previous = entries[bucketIndex];
            Entry<K, V> current = entries[bucketIndex].getNext();
            while (current != null)
                if (current.getK().equals(key)) {
                    if (previous == null) {
                        entries[bucketIndex] = entries[bucketIndex].getNext();
                        return true;
                    } else {
                        previous.setNext(current.getNext());
                    }
                    previous = current;
                    current = current.getNext();
                }
            return false;
        }
    }

    public boolean containsKey(K key) {
        if (key == null) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("key can't be null!");
            }
        }
        int bucketIndex = calcIndex(key);
        if (entries[bucketIndex] == null) {
            return false;
        } else {
            Entry<K, V> contain = entries[bucketIndex];
            while (contain != null)
                if (contain.getK().equals(key)) {
                    return true;
                }
        }
        return false;
    }

    public int size() {
        int count = 0;
        for (int i = 0; i < entries.length; i++) {
            if (entries[i] != null) {
                int countLL = 0;
                for (Entry<K, V> a = entries[i]; a.getNext() != null; a = a.getNext()) {
                    countLL++;
                }
                count += countLL;
                count++;
            }
        }
        return count;
    }


    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        Entry<K, V> e;
        for (int i = 0; i < entries.length; i++) {
            e = entries[i];
            if (e != null) {
                set.add(e.getK());
                while (e.getNext() != null) {
                    e = e.getNext();
                    set.add(e.getK());
                }
            }
        }
        return set;
    }

    public void printBucket(int index) {
        Entry<K, V> entry = entries[index];
        while (entry != null) {
            System.out.println(entry.getV());
            entry = entry.getNext();
        }
    }


    public int calcIndex(K key) {
        return Math.abs(key.hashCode()) % initialCapacity;
    }
}
