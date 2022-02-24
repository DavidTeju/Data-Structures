package lists.arrayList;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;

public class ArrayList<T> {
    private static final int DEFAULT_CAPACITY = 10; //Math.ceil(capacity*4/3)
    int capacity = DEFAULT_CAPACITY;
    int size = 0;
    @SuppressWarnings("unchecked")
    T[] content = (T[]) new Object[capacity];

    public ArrayList(){
    }

    public void add(@NotNull T toAdd){
        if (size>=capacity)
            extendArray();
        content[size] = toAdd;
        size++;
    }

    public boolean addAll(@NotNull Collection<? extends T> collection) {
        if (collection.isEmpty()) return false;
        collection.forEach(this::add);
        return true;
    }

    @SuppressWarnings("unchecked")
    public boolean remove(@NotNull T objToRemove){
        int index = indexOf(objToRemove);
        if (index == -1) return false;

        T[] temp = (T[]) new Object[size-1];
        System.arraycopy(content, 0, temp, 0, index);
        System.arraycopy(content, index+1, temp, index, size-(index+1));
        content = temp;

        size--;
        if (size > capacity/2)
            shrinkArray();
        return true;
    }

    public int indexOf(T objToFind){
        for (int i = 0; i<size; i++)
            if (content[i].equals(objToFind))
                return i;
        return -1;
    }

    public int lastIndexOf(T objToFind){
        for (int i = size-1; i>=0; i--)
            if (content[i].equals(objToFind))
                return i;
        return -1;
    }

    private void shrinkArray(){
        capacity /= Math.floor(3f/4);
        content = Arrays.copyOf(content, capacity);
    }

    private void extendArray(){
        capacity *= Math.ceil(4f/3);
        content = Arrays.copyOf(content, capacity);
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i<size; i++)
            builder.append(content[i].toString()).append(", ");
        return String.format("[ %s\b\b ]", builder);

    }
}
