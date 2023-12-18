
package adt;

import entity.Course;
import java.util.Iterator;

/**
 *
 * @author thongjiayi
 * @param <T>
 */

public interface ListInterface<T extends Comparable<T>> {

  /**
   * Task: Adds a new entry to the sorted list in its proper order.
   *
   * @param newEntry the object to be added as a new entry
   * @return true if the addition is successful
   */
  public boolean add(T newEntry);

  /**
   * Task: Removes a specified entry from the sorted list.
   *
   * @param anEntry the object to be removed
   * @return true if anEntry was located and removed
   */
  public boolean remove(T anEntry);

  public boolean contains(T anEntry);

  public void clear();

  public int getNumberOfEntries();
  
  public T getEntry(int position);

  public boolean isEmpty();

    public int size();

    public Iterator<Course> iterator();

    public Object getCode();
    
    

} 