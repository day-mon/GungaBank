//ok
package sample.util;

import sample.util.interfaces.Set;

import java.util.Arrays;

public class ArraySet<T> implements Set<T> {
    private static final int defaultCap = 25;
    private Object[] set;
    private int size;
    private int capacity;

    /**
     * TODO: Make resizeArray private,  resize array is not needed to be used by the actual person using the data structure only by you (Shane)
     *
     */

    public ArraySet(){
        this(defaultCap);
    }

    public ArraySet(int capacity) {
        this.capacity = capacity;
        this.set = new Object[capacity];
    }

    @Override
    public boolean add(T obj) {
        if(obj == null){
            return false;
        }
        if(size+1 > capacity){
            resizeArray(capacity+1);
        }
        set[size++] = obj;
        return true;


    }

    @Override
    public void clear() {
        this.set = new Object[defaultCap];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object[] toArray() {
       return set;
    }

    @Override
    public boolean remove(T obj) {
        for (int x=0; x<size; x++) {
            if(set[x] == obj) {
                set[x] = null;
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(T obj) {
        for (int x = 0; x < size; x++) {
            if (set[x] == obj) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean resizeArray(int cap) {
        capacity = cap;
        set = Arrays.copyOf(set, cap);
        return true;
    }
}