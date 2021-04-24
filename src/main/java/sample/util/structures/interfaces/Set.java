package sample.util.structures.interfaces;

public interface Set<T>
{
    boolean add(T obj);

    void clear();

    int size();

    Object[] toArray();

    boolean remove(T obj);

    boolean contains(T obj);

    boolean isEmpty();

}