//ok
package sample.util.structures;

import sample.util.structures.interfaces.Set;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArraySet<T> implements Serializable, Set<T> {
    private static final int defaultCap = 25;
    private Object[] set;
    private int size;
    private int capacity;





    public ArraySet(){
        this(defaultCap);
    }



    public ArraySet(int capacity) {
        this.capacity = capacity;
        this.set = new Object[capacity];
    }


    public Iterator<T> iterator()
    {
        return new Iterator<T>()
        {
            private int position = 0;

            @Override
            public boolean hasNext() {
                return (position < size);
            }

            @Override
            public T next()
            {
                if (!hasNext())
                {
                    throw new NoSuchElementException();
                }
                return (T)set[position++];
            }
        };
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
