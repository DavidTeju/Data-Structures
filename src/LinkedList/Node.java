package LinkedList;

import org.jetbrains.annotations.NotNull;

public class Node<T> {
    private T content;
    private Node<T> next;

    protected Node(T content) {
        this.content = content;
    }

    protected boolean contains(T toCheck){
        if (content.equals(toCheck)) return true;
        return next.contains(toCheck);
    }

    protected boolean trackRemove (T objToRemove){
        if (next.getContent().equals(objToRemove)){
            next = next.getNext();
            return true;
        }
        return next.trackRemove(objToRemove);
    }

    protected T findAndRemove(int index){
        T objToRemove;
        if (index == 1){
            objToRemove = next.getContent();
            next = next.getNext();
            return objToRemove;
        }
        return findAndRemove(--index);
    }

    protected String listToString(String previousConcat){
        return this.next == null
                ? previousConcat
                : next.listToString(String.format("%s -> %s", previousConcat, next));
    }

    protected void setNext(Node<T> next) {
        if (this.next == null)
            this.next = next;
        else this.next.setNext(next);
    }

    protected Node<T> find(int index){
        return index == 0
                ? this
                : next.find(--index);
    }

    protected void reverse(LinkedList<T> reversed){
        if (this.next == null)
            reversed.add(this.content);
        else {
            next.reverse(reversed);
            reversed.add(this.content);
        }
    }

    public String toString(){
        return content.toString();
    }

    protected int extractSize(int num){
        return this.next == null
                ? ++num
                : next.extractSize(++num);
    }

    public void loadArray(Object @NotNull [] arrayToReturn, int index) {
        arrayToReturn[index] = content;
        if (next != null)
            next.loadArray(arrayToReturn, ++index);
    }

    public Node<T> getNext(){
        return next;
    }

    public T getContent() {
        return content;
    }
}
