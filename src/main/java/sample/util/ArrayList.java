package sample.util;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import sample.util.interfaces.ListInterface;

/**
 * @param <E> type of data in this list
 * @author Damon l. Montague Jr
 * @author Ryan Leitenbeger
 * @author Shanes Wiles
 * @see ListInterface
 */

public class ArrayList<E> implements ListInterface<E>, Serializable, Iterable<E>
{

    private static final long serialVersionUID = -3897356633951676105L;
    private int size;
    private Object[] list;
    private static final int DEFAULT_CAPACITY = 25;
    private int capacity;

    public ArrayList()
    {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int capacity)
    {
        this.capacity = capacity;
        list = new Object[capacity];
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }

    @Override
    public boolean contains(Object o)
    {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<E> iterator()
    {
        return new Iterator<E>()
        {
            private int position = 0;

            @Override
            public boolean hasNext()
            {
                return (position < size);
            }

            @Override
            public E next()
            {
                if (!hasNext())
                {
                    throw new NoSuchElementException();
                }
                return (E) list[position++];
            }
        };
    }

    @Override
    public Object[] toArray()
    {
        return this.list;
    }

    @Override
    public boolean add(E e)
    {
        growCheck();

        list[size++] = e;
        return true;
    }

    @Override
    public boolean remove(Object o)
    {
        if (o != null)
        {
            boolean found = false;
            for (int i = 0; i < list.length; i++)
            {
                if (list[i].equals(o))
                {
                    list[i] = null;
                    size--;
                    found = true;
                }
                if (found && list[i] != null)
                {
                    list[i - 1] = list[i];
                }
            }
            if (found)
            {
                list[size + 1] = null;
            }
            return found;
        }
        return false;
    }

    @Override
    public void clear()
    {
        list = new Object[capacity];
        size = 0;
    }

    @Override
    public E get(int index)
    {
        if (index >= 0 && index < size)
        {
            return (E) list[index];
        }
        return null;
    }

    @Override
    public E set(int index, E element)
    {
        if (index >= 0 && index < size)
        {
            list[index] = element;
        }
        return null;
    }

    @Override
    public E remove(int index)
    {
      if (index > capacity)
      {
        throw new IndexOutOfBoundsException();
      }
        E element = get(index);
        return remove(list[index]) ? element : null;
    }

    @Override
    public int indexOf(Object o)
    {
        int index = -1;
        for (int i = 0; i < list.length; i++)
        {
            if (list[i].equals(o))
            {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public int lastIndexOf(Object o)
    {
      if (indexOf(o) < 0)
      {
        return -1;
      }
        int index = 0;
        for (int i = 0; i < list.length; i++)
        {
            if (list[i].equals(o))
            {
                index = i;
            }
        }
        return index;
    }

    @Override
    public ListIterator<E> listIterator()
    {
        return (ListIterator<E>) this.iterator();
    }

    private void growCheck()
    {
        if (size == capacity - 1)
        {
            int newNum = capacity << 1;
            capacity = newNum;
            list = Arrays.copyOf(list, newNum);
        }
    }

    @Override
    public boolean add(int index, E e)
    {
        if (index >= 0 && index < size)
        {
            growCheck();
            for (int i = size; i > index; i--)
            {
                list[i] = list[i - 1];
            }
            list[index] = e;
            size++;
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E[] toArray(E[] e)
    {
        return (E[]) Arrays.copyOf(toArray(), size, e.getClass());

    }
}
