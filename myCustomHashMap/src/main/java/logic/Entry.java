package logic;

public class Entry<K, V> {
    private K k;
    private V v;
    private Entry<K, V> next;

    public Entry(K k, V v, Entry<K, V> next) {
        this.k = k;
        this.v = v;
        this.next = next;
    }

    public K getK() {
        return k;
    }

    public V getV() {
        return v;
    }

    public Entry<K, V> getNext() {
        return next;
    }

    public void setNext(Entry<K, V> next) {
        this.next = next;
    }
}
