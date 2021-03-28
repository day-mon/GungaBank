package sample.util.structures.interfaces;

import sample.util.structures.ArrayList;

import java.util.Iterator;
import java.util.ListIterator;

/**
 * @param <E> type of element in this list
 *
 * @author Ryan Leitenbeger
 * @author Damon L Montague Jr.
 * @author Shanes Wiles
 *
 * @see ArrayList
 *
 */

public interface ListInterface<E> {
  /**
   *
   * @param index
   * @param e
   * @return
   */
  public boolean add(int index, E e);

  /**
   *
   * @param e
   * @return
   */
  public boolean add(E e);

  /**
   *
   * @param index
   * @return
   */
  public E get(int index);

  /**
   *
   * @param e
   * @return
   */
  public int indexOf(E e);

  /**
   *
   * @param index
   * @param element
   * @return
   */
  public E set(int index, E element);

  /**
   *
   * @param e
   * @return
   */
  public int lastIndexOf(E e);

  /**
   *
   * @param o
   * @return
   */
  public boolean remove(Object o);

  /**
   *
   * @param index
   * @return
   */
  public E remove(int index);

  /**
   *
   */
  public void clear();

  /**
   *
   * @return
   */
  public int size();

  /**
   *
   * @param e
   * @return
   */
  public boolean contains(E e);

  /**
   *
   * @return
   */
  public boolean isEmpty();

  /**
   *
   * @return
   */
  public Object[] toArray();

  /**
   *
   * @param e
   * @return
   */
  public E[] toArray(E[] e);

  /**
   *
   * @return
   */
  public ListIterator<E> listIterator();

  /**
   *
   * @return
   */
  public default Iterator<E> iterator() {
    return listIterator();
  }
}
