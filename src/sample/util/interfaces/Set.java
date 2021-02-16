package sample.util.interfaces;

public interface Set<T> {
    public boolean add(T obj);

    public void clear();

    public int size();

    public Object[] toArray();

    public boolean remove(T obj);

    public boolean contains(T obj);

    public boolean isEmpty();

    public boolean resizeArray(int cap);
}