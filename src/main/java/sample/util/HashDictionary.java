package sample.util;

import java.io.Serializable;
import java.util.*;

/**
 * This is an implementation of a hash table using separate chaining. Entries in
 * the array contain Nodes, which form a chain of all key-value pairs with the
 * same key hash index.
 * <p>
 * There's no mechanism to resize the array here. There's also no real need, as
 * a table entry will contain a linked chain which may have multiple nodes.
 * <p>
 * Some things to think about: - How would you implement the iterators for this
 * collection? - What is the runtime complexity of the methods?
 *
 * @author Stephen J. Sarma-Weierman
 * @author YOUR NAME HERE
 */
@SuppressWarnings("unchecked")
public class HashDictionary<K, V> implements Serializable, Dictionary<K, V>
{
    private Object[] entries; //array of Nodes
    private int size;
    private static final int DEFAULT_CAPACITY = 17;
    private static final float LOAD_FACTOR = 0.75f;
    private float loadfactor;

    public HashDictionary()
    {
        this(DEFAULT_CAPACITY, LOAD_FACTOR);
    }

    //TODO
    public HashDictionary(int initialCapacity, float factor)
    {
        entries = new Object[initialCapacity];
        loadfactor = factor;
        size = 0;
    }

    //TODO
    @Override
    public Iterator<K> keys()
    {
        return new Iterator<K>()
        {
            int count = 0;
            int c = 0;
            int next = 0;

            @Override
            public boolean hasNext()
            {
                return count < size;
            }

            public K next()
            {
                if (!hasNext())
                {
                    throw new NoSuchElementException("End of collection");
                }
                Node n = (Node) entries[c];
                while (n == null)
                {
                    n = (Node) entries[++c];
                }
                for (int i = 0; i < next; i++)
                {
                    n = n.getNext();
                }
                if (n.getNext() != null)
                {
                    next++;
                }
                else
                {
                    ++c;
                    next = 0;
                }
                count++;
                return n.getKey();
            }
        };
    }

    //TODO
    @Override
    public Iterator<V> elements()
    {
        return new Iterator<V>()
        {
            int count = 0;
            int c = 0;
            int next = 0;

            @Override
            public boolean hasNext()
            {
                return count < size;
            }

            public V next()
            {
                if (!hasNext())
                {
                    throw new NoSuchElementException("End of collection");
                }
                Node n = (Node) entries[c];
                while (n == null)
                {
                    n = (Node) entries[++c];
                }
                for (int i = 0; i < next; i++)
                {
                    n = n.getNext();
                }
                if (n.getNext() != null)
                {
                    next++;
                }
                else
                {
                    ++c;
                    next = 0;
                }
                count++;
                return n.getValue();
            }
        };
    }

    //TODO
    @SuppressWarnings("unchecked")
    @Override
    public V get(K key)
    {
        int index = getHashIndex(key);
        Node n = (Node) entries[index];
        while (n != null && !n.getKey().equals(key))
        {
            n = n.getNext();
        }
        if (n == null)
        {
            return null;
        }
        return n.getValue();
    }

    private void rehash(int newCap)
    {
        Object[] arr = entries;
        entries = new Object[nextPrime(newCap)];
        for (int i = 0; i < arr.length; i++)
        {
            Node n = (Node) arr[i];
            while (n != null)
            {
                Node node = new Node(n.getKey(), n.getValue());
                int index = getHashIndex(node.getKey());
                if (entries[index] == null)
                {
                    entries[index] = node;
                }
                else
                {
                    Node n2 = (Node) entries[index];
                    node.setNext(n2);
                    entries[index] = node;
                }
                n = n.getNext();
            }
        }
    }

    //TODO
    @Override
    public V remove(K key)
    {
        V ret = null;
        if (key == null || get(key) == null || size == 0)
        {
            return ret;
        }

        int index = getHashIndex(key);
        Node n = (Node) entries[index];

        if (n.getNext() == null)
        {
            ret = (V) n.getValue();
            entries[index] = null;
            size--;
            return ret;
        }
        else
        {
            Node prev = n;
            n = n.getNext();
            while (n != null)
            {
                if (key == n.getKey())
                {
                    ret = n.getValue();
                    if (n.getNext() == null)
                    {
                        prev.setNext(null);
                    }
                    else
                    {
                        prev.setNext(n.getNext());
                    }
                    break;
                }
                prev = n;
                n = n.getNext();
            }
        }

        size--;
        return ret;
    }

    //TODO
    @Override
    public V put(K key, V value)
    {
        V ret = null;
        if (key == null)
        {
            return null;
        }

        if (size / entries.length >= loadfactor)
        {
            rehash(nextPrime(size << 1));
        }

        int index = getHashIndex(key);

        if (entries[index] == null)
        {
            entries[index] = new Node(key, value);
            size++;
        }

        else
        {
            Node n = (Node) entries[index];
            while (n != null)
            {
                if (key == n.getKey())
                {
                    ret = n.getValue();
                    n.setValue(value);
                    break;
                }

                if (n.getNext() == null)
                {
                    n.setNext(new Node(key, value));

                    size++;
                    break;
                }

                else
                {
                    n = n.getNext();
                }
            }
        }
        return ret;
    }

    public boolean containsKey(K k)
    {
        if (k == null)
        {
            return false;
        }
        if (get(k) == null)
        {
            return false;
        }
        int index = getHashIndex(k);

        Node n = (Node) entries[index];

        if (Objects.equals(k, n.key))
        {
            return true;
        }
        else
        {
            while (n != null || Objects.equals(k, n.key))
            {
                n = n.next;
            }

            if (n.key != k)
            {
                return false;
            }
            return true;
        }

    }


    /**
     * This returns an index based on the hashCode for the key object. The index
     * must be in the bounds of the array.
     *
     * @param key
     * @return
     */
    private int getHashIndex(K key)
    {
        int capacity = entries.length;
        int index = key.hashCode() % capacity;
        if (index < 0)
        {
            index += capacity;
        }
        return index;
    }

    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }

    @Override
    public int size()
    {
        return size;
    }

    private boolean isPrime(int n)
    {
        if (n < 2)
        {
            return false;
        }
        if (n == 2 || n == 3)
        {
            return true;
        }
        if (n % 2 == 0)
        {
            return false;
        }

        for (int i = 3; i < (int) Math.sqrt(n) + 1; i += 2)
        {
            if (n % i == 0)
            {
                return false;
            }
        }

        return true;
    }

    private int nextPrime(int n)
    {
        int p = n + 1;
        while (!isPrime(p))
        {
            p++;
        }
        return p;
    }

    private class Node implements Serializable
    {

        private K key;
        private V value;
        private Node next;

        public Node(K key, V value)
        {
            this.key = key;
            this.value = value;
        }

        public K getKey()
        {
            return key;
        }

        public void setKey(K key)
        {
            this.key = key;
        }

        public V getValue()
        {
            return value;
        }

        public void setValue(V value)
        {
            this.value = value;
        }

        public Node getNext()
        {
            return next;
        }

        public void setNext(Node next)
        {
            this.next = next;
        }

    }
}
