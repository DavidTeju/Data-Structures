package LinkedList;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class LinkedList<T> {
    private Node<T> firstNode;

    public LinkedList(T firstObject) {
        this.firstNode = new Node<>(firstObject);
    }

    public LinkedList() {
    }

    public boolean add(T content){
        if (firstNode == null)
            firstNode = new Node<>(content);
        else firstNode.setNext(new Node<>(content));
        return true;
    }

    public boolean addAll(@NotNull Collection<T> collection) {
        if (collection.isEmpty()) return false;
        collection.forEach(this::add);
        return true;
    }

    public boolean remove(@NotNull T objToRemove){
        if (firstNode == null) return false;
        if (firstNode.getContent().equals(objToRemove)){
            firstNode = firstNode.getNext();
            return true;
        }
        return firstNode.trackRemove(objToRemove);
    }

    /**
     * Removes the element at the specified position in this list. <br>
     * Shifts any subsequent elements to the left (subtracts one from their indices). <br>
     * Returns the element that was removed from the list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws UnsupportedOperationException if the {@code remove} operation
     *                                       is not supported by this list
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       ({@code index < 0 || index >= size()})
     */
    public Object remove(int index) {//TODO Test This when empty
        if (firstNode == null) throw new IndexOutOfBoundsException("This " + this.getClass() + "is empty");
        if (index == 0){
            Object objToRemove = firstNode.getContent();
            firstNode = firstNode.getNext();
            return objToRemove;
        }
        return firstNode.findAndRemove(--index);
    }

    public boolean removeAll(@NotNull Collection<T> collection) {
        boolean toReturn = false;
        for(T e: collection)
            toReturn = remove(e) || toReturn;
        return toReturn;
    }

    public void clear(){
        firstNode = null;
    }

    public int size(){
        return firstNode.extractSize(0);
    }

    public boolean isEmpty(){
        return firstNode==null;
    }

    public Object[] toArray() {
        var arrayToReturn = new Object[this.size()];
        firstNode.loadArray(arrayToReturn, 0);
        return arrayToReturn;
    }

    public boolean contains(T objToFind){
        return firstNode.contains(objToFind);
    }

    public Object get(int index){
        if (index>=size()) throw new IndexOutOfBoundsException();
        return this.firstNode.find(index).getContent();
    }

    public String toString(){
        return isEmpty()
                ? "[ ]"
                : String.format("[ %s ]", firstNode.listToString(firstNode.toString()));
    }

    public LinkedList<T> reverse(){
        var reversedList = new LinkedList<T>();
        firstNode.reverse(reversedList);
        return reversedList;
    }

    public void reverseThis(){
        var reversedList = new LinkedList<T>();
        firstNode.reverse(reversedList);
        this.firstNode = reversedList.firstNode;
    }
}
