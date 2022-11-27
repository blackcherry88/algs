package ds.util;

import java.util.*;

public class ObjectIntMap<K> {
    private final Map<K, Integer> obj2Index;
    private final List<K> index2Obj;

    public ObjectIntMap() {
        this.obj2Index = new HashMap<>();
        this.index2Obj = new ArrayList<>();
    }

    public K get(int index) {
        return index2Obj.get(index);
    }

    public Set<K> keySet() {
        return obj2Index.keySet();
    }

    public List<K> getIndices() {
        return index2Obj;
    }

    public int getIndex(K key) {
        Integer index = obj2Index.get(key);
        if (index == null) {
            return -1;
        }
        return index;
    }

    public int put(K key) {
        Integer index = obj2Index.size();
        Integer i = obj2Index.putIfAbsent(key, index);
        if (i == null) {
            i = index;
            index2Obj.add(i, key);
        }
        return i;
    }

    public boolean contains(K key) {
        return obj2Index.containsKey(key);
    }

    public int size() {
        return obj2Index.size();
    }

    @Override
    public String toString() {
        return "ObjectIntMap{" +
                "obj2Index=" + obj2Index +
                ", index2Obj=" + index2Obj +
                '}';
    }
}
