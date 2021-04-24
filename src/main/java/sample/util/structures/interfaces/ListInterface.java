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
  boolean add(int index, E e);

  /**
   *
   * @param e
   * @return
   */
  boolean add(E e);

  /**
   *
   * @param index
   * @return
   */
  E get(int index);

  /**
   *
   * @param e
   * @return
   */
  int indexOf(E e);

  /**
   *
   * @param index
   * @param element
   * @return
   */
  E set(int index, E element);

  /**
   *
   * @param e
   * @return
   */
  int lastIndexOf(E e);

  /**
   *
   * @param o
   * @return
   */
  boolean remove(Object o);

  /**
   *
   * @param index
   * @return
   */
  E remove(int index);

  /**
   *
   */
  void clear();

  /**
   *
   * @return
   */
  int size();

  /**
   *
   * @param e
   * @return
   */
  boolean contains(E e);

  /**
   *
   * @return
   */
  boolean isEmpty();

  /**
   *
   * @return
   */
  Object[] toArray();

  /**
   *
   * @param e
   * @return
   */
  E[] toArray(E[] e);

  /**
   *
   * @return
   */
  ListIterator<E> listIterator();

  /**
   *
   * @return
   */
  default Iterator<E> iterator() {
    return listIterator();
  }
}
