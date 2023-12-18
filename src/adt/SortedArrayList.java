
package adt;


import entity.Programme;
import entity.Course;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * @param <T>
 * @author thongjiayi
 */


public class SortedArrayList<T extends Comparable<T>> implements ListInterface<T> {

    private T[] array;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 25;
    private List<T> list;


    public SortedArrayList() {
        this(DEFAULT_CAPACITY);
        list = new ArrayList<>();
    }


    public SortedArrayList(int initialCapacity) {
        numberOfEntries = 0;
        array = (T[]) new Comparable[initialCapacity];
    }

    @Override
    public boolean add(T newEntry) {
        int i = 0;
        while (i < numberOfEntries && newEntry.compareTo(array[i]) > 0) {
            i++;
        }
        makeRoom(i + 1);
        array[i] = newEntry;
        numberOfEntries++;
        return true;
    }

//  public boolean remove(T anEntry) {
//    throw new UnsupportedOperationException();
//  }

    public void clear() {
        numberOfEntries = 0;
    }

    public boolean contains(T anEntry) {
        boolean found = false;
        for (int index = 0; !found && (index < numberOfEntries); index++) {
            if (anEntry.equals(array[index])) {
                found = true;
            }
        }
        return found;
    }

    public int getNumberOfEntries() {
        return numberOfEntries;

    }

    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    public String toString() {
        String outputStr = "";
        for (int index = 0; index < numberOfEntries; ++index) {
            outputStr += array[index] + "\n";
        }

        return outputStr;
    }

    private boolean isArrayFull() {
        return numberOfEntries == array.length;
    }

    private void doubleArray() {
        T[] oldList = array;
        int oldSize = oldList.length;

        array = (T[]) new Object[2 * oldSize];

        for (int index = 0; index < oldSize; index++) {
            array[index] = oldList[index];
        }
    }

    private void makeRoom(int newPosition) {
        int newIndex = newPosition - 1;
        int lastIndex = numberOfEntries - 1;

        for (int index = lastIndex; index >= newIndex; index--) {
            array[index + 1] = array[index];
        }
    }

    private void removeGap(int givenPosition) {
        int removedIndex = givenPosition;
        int lastIndex = numberOfEntries;

        if (removedIndex > 0){
            removedIndex = givenPosition - 1;
            lastIndex = numberOfEntries - 1;
        }

        for (int index = removedIndex; index < lastIndex; index++) {
            array[index] = array[index + 1];
        }
    }

    /**
     * @return
     */

//@Override
//  public boolean isArrayFull() {
//    return false;
//  }
    @Override
    public T getEntry(int givenPosition) {
        T result = null;

        if (givenPosition < 0 || givenPosition >= numberOfEntries) {
            throw new IndexOutOfBoundsException();
        }else{
            return array[givenPosition];
        }
    }

    public boolean remove(T anEntry) {
        int position = findPosition(anEntry);
        if (position != -1) {
            removeGap(position);
            numberOfEntries--;
            return true;
        } else {
            return false; // Element not found
        }
    }

    private int findPosition(T anEntry) {
        int position = -1;
        for (int i = 0; i < numberOfEntries; i++) {
            if (anEntry.equals(array[i])) {
                position = i;
                break;
            }
        }
        return position;
    }

    private int findEntry(T anEntry) {
        int index = 0;
        while (index < numberOfEntries && anEntry.compareTo(array[index]) != 0) {
            index++;
        }

        return (index < numberOfEntries) ? index : -1;
    }

    private int binarySearch(T target) {
        int low = 0;
        int high = numberOfEntries - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            T midValue = array[mid];

            if (midValue.equals(target)) {
                return mid; // Target found
            } else if (midValue.compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1; // Target not found
    }

    @Override
    public int size() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//        return super.size();
        return list.size();
    }

    /**
     * @param element
     */
    public void addSorted(T element) {
        // Your implementation of adding element in sorted order...
    }

    @Override
    public Iterator<Course> iterator() {
        return (Iterator<Course>) list.iterator();
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object getCode() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        Object[] programCode = null;
        return Objects.hash(programCode);
    }


    public void addProgramme(Programme programme) {
        // Implementation goes here...
        throw new UnsupportedOperationException("Not supported yet.");
    }


}

