package lists.linkedList;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.*;

//Helper loop: for (Node current = firstNode; current!=null; current = current.next, i++)
public class LinkedList<T> implements List<T>, Iterable<T> {
	private Node firstNode;
	private int size = 0;
	
	//done
	public LinkedList() {
	}
	
	//done
	public LinkedList(T firstObject) {
		this.add(firstObject);
	}
	
	//done
	public LinkedList(Collection<? extends T> collection) {
		this.addAll(collection);
	}
	
	//returns an entry set with key firstNode and value lastNode of a List that is to be used in an incomplete manner
	private Map.Entry<Node, Node> getNodeStrand(Collection<? extends T> collection) {
		Node fakeFirst = new Node(null);
		//use a fake first as a holder to which you attach everything in the collection to
		Node holder = fakeFirst;
		for (T content : collection) {
			holder.next = new Node(content);
			holder = holder.next;
		}
		//get a map, add the values, convert to list of entries, and get and return the first and only entry
		var map = new HashMap<Node, Node>();
		map.put(fakeFirst.next, holder);
		return map.entrySet()
				.stream()
				.toList()
				.get(0);
	}
	
	private Node getListEnd() {
		var tempEnd = firstNode;
		while (tempEnd.next != null)
			tempEnd = tempEnd.next;
		return tempEnd;
	}
	
	private Node getNodeAt(int index) {
		if (firstNode == null)
			throw new IndexOutOfBoundsException("This " + this.getClass().toString().substring(6) + " is empty");
		if (index >= size() || index < 0)
			throw new IndexOutOfBoundsException();
		
		Node atIndex = firstNode;
		//starts at one because it's already assigned to
		for (int i = 1; i <= index; i++)
			atIndex = atIndex.next;
		
		return atIndex;
	}
	
	//done
	public boolean add(@NotNull T toAdd) {
		if (firstNode == null)
			firstNode = new Node(toAdd);
		else
			getListEnd().next = new Node(toAdd);
		
		size++;
		return true;
	}
	
	//done
	public boolean addAll(@NotNull Collection<? extends T> collection) {
		if (collection.isEmpty()) return false;
		
		var subListHead = getNodeStrand(collection).getKey();
		
		if (firstNode == null) firstNode = subListHead;
		else {
			var listEnd = getListEnd();
			listEnd.next = subListHead;
		}
		
		size += collection.size();
		return true;
	}
	
	@Override
	//done
	public boolean addAll(int index, @NotNull Collection<? extends T> collectionToAdd) {
		Node atIndex = getNodeAt(index);
		
		var nextFromIndex = atIndex.next;
		
		var endsOfTempList = getNodeStrand(collectionToAdd);
		var headOfTempList = endsOfTempList.getKey();
		var lastOfTempList = endsOfTempList.getValue();
		
		atIndex.next = headOfTempList;
		lastOfTempList.next = nextFromIndex;
		size += collectionToAdd.size();
		
		return true;
	}
	
	//done
	public boolean remove(Object objToRemove) {
		if (firstNode == null) return false;
		if (objToRemove == null) throw new NullPointerException();
		
		Node node = firstNode;
		Node next;
		if (firstNode.content.equals(objToRemove)) {
			firstNode = firstNode.next;
			size--;
			return true;
		}
		while ((next = node.next) != null) { //assign Node next to the next node and check if it's null
			if (next.content.equals(objToRemove)) {
				node.next = next.next;
				size--;
				return true;
			}
			node = next;
		}
		return false;
	}
	
	//done
	public T remove(int index) {
		T objToRemove;
		if (index == 0) {
			objToRemove = firstNode.content;
			firstNode = firstNode.next;
		} else {
			var before = getNodeAt(index - 1);
			objToRemove = before.next.content;
			before.next = before.next.next;
		}
		
		size++;
		return objToRemove;
	}
	
	//done
	public boolean removeAll(@NotNull Collection<?> collection) {
		boolean removed = false;
		for (var each : collection)
			removed = remove(each) || removed;
		
		//size is modified in the remove() method
		return removed;
	}
	
	//done
	@Override
	public boolean retainAll(@NotNull Collection<?> c) {
		boolean removed = false;
		for (T each : this)//the iterator iterates over the content of the Node, so we can use contains
			if (!c.contains(each))
				removed = remove(each) || removed;
		
		return removed;
	}
	
	//done
	public boolean containsAll(@NotNull Collection<?> collection) {
		for (Object element : collection)
			if (!this.contains(element)) return false;
		return true;
	}
	
	//done
	public void clear() {
		firstNode = null;
	}
	
	//done
	public int size() {
		return size;
	}
	
	//done
	public boolean isEmpty() {
		return firstNode == null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(@NotNull Object toEqual) {
		var listToEqual = (List<T>) toEqual;
		
		if (size != listToEqual.size()) return false;
		
		for (int i = 0; i < listToEqual.size(); i++)
			if (!get(i).equals(listToEqual.get(i)))
				return false;
		
		return true;
	}
	
	//done
	@NotNull
	@Override
	@SuppressWarnings("unchecked")
	public <E> E @NotNull [] toArray(E @NotNull [] arrayToLoad) {
		if (arrayToLoad.length < size)
			arrayToLoad = (E[]) Array.newInstance(arrayToLoad.getClass().getComponentType(), size);
		
		var current = firstNode;
		Object[] take = arrayToLoad; //We use an Object[] to escape compilation error and throw ArrayStoreException
		for (int i = 0; i < size; i++) {
			take[i] = current.content;
			current = current.next;
		}//we still modified arrayToLoad cause arrays are mutable :)
		
		if (arrayToLoad.length > size) arrayToLoad[size] = null;
		
		return arrayToLoad;
	}
	
	//done
	public boolean contains(Object objToFind) {
		for (Node current = firstNode; current != null; current = current.next)
			if (current.content.equals(objToFind)) return true;
		return false;
	}
	
	@NotNull
	@Override
	//done
	public Iterator<T> iterator() {
		return new LocalIterator();
	}
	
	@NotNull
	@Override
	//done
	public Object @NotNull [] toArray() {
		var arrayToReturn = new Object[this.size()];
		
		
		Node current = firstNode;
		for (int i = 0; i < size; i++) {
			arrayToReturn[i] = current.content;
			current = current.next;
		}
		return arrayToReturn;
	}
	
	//done
	public int indexOf(Object objToFind) {
		int i = 0;
		for (T each : this)
			if (each.equals(objToFind))
				return i;
		return -1;
	}
	
	//done
	public int lastIndexOf(Object objToFind) {
		int lastIndex = -1;
		int i = 0;
		
		for (T each : this) {
			if (each.equals(objToFind))
				lastIndex = i;
			i++;
		}
		
		return lastIndex;
	}
	
	@NotNull
	@Override
	public ListIterator<T> listIterator() {
		return new LocalListIterator();
	}
	
	@NotNull
	@Override
	public ListIterator<T> listIterator(int index) {
		return new LocalListIterator(index);
	}
	
	//done
	@NotNull
	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		if (fromIndex < 0 || toIndex > size() || fromIndex > toIndex)
			throw new IndexOutOfBoundsException();
		
		if (fromIndex == toIndex)
			return new LinkedList<>();
		
		LinkedList<T> list = new LinkedList<>();
		Node current = this.getNodeAt(fromIndex);
		for (int i = fromIndex; i < toIndex; i++) {
			list.add(current.content);
			current = current.next;
		}
		return list;
	}
	
	//done
	public T get(int index) {
		if (index >= size()) throw new IndexOutOfBoundsException();
		
		return getNodeAt(index).content;
	}
	
	/**
	 * Replaces the element at the specified position in this list with the
	 * specified element (optional operation).
	 *
	 * @param index   index of the element to replace
	 * @param element element to be stored at the specified position
	 * @return the element previously at the specified position
	 * @throws UnsupportedOperationException if the {@code set} operation
	 *                                       is not supported by this list
	 * @throws ClassCastException            if the class of the specified element
	 *                                       prevents it from being added to this list
	 * @throws NullPointerException          if the specified element is null and
	 *                                       this list does not permit null elements
	 * @throws IllegalArgumentException      if some property of the specified
	 *                                       element prevents it from being added to this list
	 * @throws IndexOutOfBoundsException     if the index is out of range
	 *                                       ({@code index < 0 || index >= size()})
	 */
	@Override
	public T set(int index, T element) {
		if (index >= size()) throw new IndexOutOfBoundsException();
		
		
		Node node = getNodeAt(index);
		T temp = node.content;
		node.content = element;
		return temp;
	}
	
	/**
	 * Inserts the specified element at the specified position in this list
	 * (optional operation).  Shifts the element currently at that position
	 * (if any) and any subsequent elements to the right (adds one to their
	 * indices).
	 *
	 * @param index   index at which the specified element is to be inserted
	 * @param element element to be inserted
	 * @throws UnsupportedOperationException if the {@code add} operation
	 *                                       is not supported by this list
	 * @throws ClassCastException            if the class of the specified element
	 *                                       prevents it from being added to this list
	 * @throws NullPointerException          if the specified element is null and
	 *                                       this list does not permit null elements
	 * @throws IllegalArgumentException      if some property of the specified
	 *                                       element prevents it from being added to this list
	 * @throws IndexOutOfBoundsException     if the index is out of range
	 *                                       ({@code index < 0 || index > size()})
	 */
	@Override
	public void add(int index, T element) {
	
	}
	
	public String toString() {
		if (isEmpty()) return "[]";
		
		StringBuilder internal = new StringBuilder(firstNode.toString());
		//since we have already appended firstNode, we start the loop with the next Node
		for (Node current = firstNode.next;
		     current != null;
		     current = current.next)
			internal.append(" -> ").append(current);
		
		return String.format("[%s]", internal);
	}
	
	public LinkedList<T> reverse() {
		var reversedList = new LinkedList<T>();
		var listIterator = listIterator(size);
		
		while (listIterator.hasPrevious())
			reversedList.add(listIterator.previous());
		
		return reversedList;
	}
	
	@SuppressWarnings("InnerClassMayBeStatic")
	private class Node {
		private T content;
		private Node next;
		
		private Node(T content) {
			this.content = content;
		}
		
		@Override
		public String toString() {
			return content.toString();
		}
	}
	
	private class LocalIterator implements Iterator<T> {
		Node current = null;
		
		@Override
		//if current is null then we have not started iteration. If current's next is null then we are at the end of the iteration
		public boolean hasNext() {
			return current == null || current.next != null;
		}
		
		@Override
		public T next() {
			if (!hasNext()) throw new NoSuchElementException();
			
			if (current == null) current = firstNode;
			else current = current.next;
			
			return current.content;
		}
	}
	
	private class LocalListIterator extends LocalIterator implements ListIterator<T> {
		double cursorIndex = -.5; //Using decimal so floor represents previous and ceil represents next
		int lastOperation = 0; //1 represents next and -1 represents previous and 0 represents none
		
		public LocalListIterator() {
		
		}
		
		public LocalListIterator(int indexToBegin) {
			if (indexToBegin > size) throw new IndexOutOfBoundsException();
			
			current = getNodeAt(indexToBegin - 1);
			cursorIndex = indexToBegin - .5;
		}
		
		@Override
		public boolean hasPrevious() {
			return previousIndex() >= 0;
		}
		
		@Override
		public T next() {
			T hold = super.next(); //we introduce a holder so any thrown errors from super.next can interrupt the method
			lastOperation = 1;
			cursorIndex++;
			return hold;//still using the parent's next because it's faster than using get()
		}
		
		@Override
		public T previous() {
			if (!hasPrevious()) throw new NoSuchElementException();
			lastOperation = -1;
			
			return (current =
					getNodeAt((int) Math.floor(cursorIndex--))
			).content;
		}
		
		@Override
		public int nextIndex() {
			return (int) Math.ceil(cursorIndex);
		}
		
		@Override
		public int previousIndex() {
			return (int) Math.floor(cursorIndex);
		}
		
		@Override
		public void remove() {
			if (lastOperation == 0) throw new IllegalStateException();
			
			if (lastOperation == 1)
				LinkedList.this.remove(nextIndex() - 1);
			else if (lastOperation == -1)
				LinkedList.this.remove(previousIndex() + 1);
			
			lastOperation = 0;
		}
		
		@Override
		public void set(T t) {
			if (lastOperation == 0) throw new IllegalStateException();
			
			if (lastOperation == 1)
				LinkedList.this.set(nextIndex() - 1, t);
			else if (lastOperation == -1)
				LinkedList.this.set(previousIndex() + 1, t);
		}
		
		@Override
		public void add(T t) {
			if (lastOperation == 0) throw new IllegalStateException();
			
			if (lastOperation == 1)
				LinkedList.this.add(nextIndex() - 1, t);
			else if (lastOperation == -1)
				LinkedList.this.add(previousIndex() + 1, t);
		}
	}
}
