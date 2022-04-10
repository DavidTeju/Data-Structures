package lists.linkedList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {
	
	@Test
	@DisplayName("New Lists should be empty")
	void new_list_should_be_empty() {
		final var sut = new LinkedList<>();
		assertTrue(sut.isEmpty());
	}
	
	@Test
	@DisplayName("LinkedList.get(n) for empty list should throw index out of bounds exception")
	void queryingNewList() {
		final var sut = new LinkedList<>();
		assertThrows(IndexOutOfBoundsException.class, () -> sut.get(0));
		assertThrows(IndexOutOfBoundsException.class, () -> sut.get(1));
	}
	
	@Test
	@DisplayName("Passing parameter in List constructor should create list with parameter as first member")
	void passingMemberInConstructor() {
		final var sut = new LinkedList<>("David");
		assertEquals("David", sut.get(0));
	}
	
	@Test
	void passing_collection_in_constructor_should_initialize_list_with_all_members() {
		var arrayInQuestion = new ArrayList<String>();
		for (int i = 0; i < 10; i++)
			arrayInQuestion.add("member" + i);
		
		final var sut = new LinkedList<>(arrayInQuestion);
		assertEquals(arrayInQuestion, sut);
		
		for(int i = 0; i < 10; i++)
			assertEquals(arrayInQuestion.get(i), sut.get(i));
	}
	
	@Test
	void add_all_at_index_i_should_include_all_elements_in_collection_and_update_size() {
		final var sut = new LinkedList<String>();
		sut.add("member0");
		sut.add("member1");
		sut.add("member2");
		sut.add("member7");
		sut.add("member8");
		sut.add("member9");
		var array = new ArrayList<String>();
		for (int i = 3; i < 7; i++)
			array.add("member" + i);
		sut.addAll(3, array);
		
		assertEquals("member2", sut.get(2));
		assertEquals("member3", sut.get(3));
		assertEquals("member6", sut.get(6));
		assertEquals("member7", sut.get(7));
		assertEquals(10, sut.size());
	}
	
	@Test
	void add_all_at_index_size_should_update_endNode() {
		final var sut = new LinkedList<String>();
		sut.add("member0");
		sut.add("member1");
		sut.add("member2");
		var array = new ArrayList<String>();
		for (int i = 3; i < 7; i++)
			array.add("member" + i);
		sut.addAll(3, array);
		
		sut.add("member7");
		assertEquals("member6", sut.get(6));
	}
	
	@Test
	void add_all_at_index_0_on_empty_list() {
		final var sut = new LinkedList<String>();
		var array = new ArrayList<String>();
		for (int i = 0; i < 5; i++)
			array.add("member" + i);
		sut.addAll(0, array);
		
		assertEquals("member4", sut.get(4));
	}
	
	@Test
	void list_iterator_next_index_should_return_index_of_element_to_be_returned_by_next() {
		final var sut = new LinkedList<String>();
		sut.add("member0");
		sut.add("member1");
		sut.add("member2");
		sut.add("member3");
		sut.add("member4");
		final var sutIterator = sut.listIterator();
		
		while (sutIterator.hasNext())
			assertEquals(sut.get(sutIterator.nextIndex()), sutIterator.next());
	}
	
	@Test
	void has_next_on_empty_lists_list_iterator_should_return_false() {
		final var sut = new LinkedList<String>();
		assertFalse(sut.listIterator().hasNext());
	}
	
	@Test
	void has_next_on_untraversed_loaded_lists_list_iterator_should_return_true() {
		final var sut = new LinkedList<String>();
		sut.add("member0");
		sut.add("member1");
		sut.add("member2");
		sut.add("member3");
		sut.add("member4");
		assertTrue(sut.listIterator().hasNext());
	}
	
	@Test
	void next_on_untraversed_loaded_lists_list_iterator_should_return_first_element() {
		final var sut = new LinkedList<String>();
		sut.add("member0");
		sut.add("member1");
		sut.add("member2");
		sut.add("member3");
		sut.add("member4");
		assertEquals(sut.get(0), sut.listIterator().next());
	}
	
	@Test
	void next_on_untraversed_loaded_lists_iterator_should_return_first_element() {
		final var sut = new LinkedList<String>();
		sut.add("member0");
		sut.add("member1");
		sut.add("member2");
		sut.add("member3");
		sut.add("member4");
		assertEquals(sut.get(0), sut.iterator().next());
	}
	
	@Test
	void has_next_on_empty_lists_iterator_should_return_false() {
		final var sut = new LinkedList<String>();
		assertFalse(sut.iterator().hasNext());
	}
	
	@Test
	void next_on_empty_lists_iterator_should_throw_exception() {
		final var sut = new LinkedList<String>();
		assertThrows(NoSuchElementException.class, () -> sut.iterator().next());
	}
	
	@Test
	void next_on_empty_lists_list_iterator_should_throw_exception() {
		final var sut = new LinkedList<String>();
		assertThrows(NoSuchElementException.class, () -> sut.listIterator().next());
	}
	
	@Test
	void strainTestOnLargeNumberOfElements() {
		final var sut = new LinkedList<>();
		for (int i = 0; i < 10000000; i++)
			sut.add(i);
		assertEquals(1000000, sut.get(1000000));
		assertEquals(10000000, sut.size());
		//adding an endNode changed execution time from over 20 mins to 730ms
		//without endNode, time Complexity was O(!n) which is O(n^2)
	}
	
	@Test
	void querying_negative_indices_should_throw_index_out_of_bounds_exception() {
		final var sut = new LinkedList<Integer>();
		sut.add(5);
		sut.add(9);
		sut.add(7);
		
		assertThrows(IndexOutOfBoundsException.class, () -> sut.get(-1));
	}
	
	@Test
	void querying_index_at_edge_should_not_throw_index_out_of_bounds_exception() {
		final var sut = new LinkedList<Integer>();
		sut.add(5);
		sut.add(9);
		sut.add(7);
		
		assertDoesNotThrow(() -> sut.get(sut.size() - 1));
		assertEquals(7, sut.get(sut.size() - 1));
	}
	
	@Test
	void adding_members_should_increase_size() {
		final var sut = new LinkedList<Integer>();
		sut.add(5);
		sut.add(9);
		sut.add(7);
		
		assertEquals(3, sut.size());
	}
	
	@Test
	void adding_collection_should_modify_size() {
		final var sut = new LinkedList<String>();
		sut.add("member0");
		sut.add("member1");
		sut.add("member2");
		sut.add("member3");
		sut.add("member4");
		
		String[] array = {"member5", "member6", "member7", "member8", "member9"};
		sut.addAll(List.of(array));
		
		assertEquals(10, sut.size());
	}
	
	@Test
	void adding_collection_to_empty_list_should_modify_size() {
		final var sut = new LinkedList<String>();
		String[] array = {"member1", "member2", "member3", "member4", "member5"};
		sut.addAll(List.of(array));
		
		assertEquals(5, sut.size());
	}
	
	
	@Test
	void addAll_to_empty_list_should_add_the_contents_of_the_collection_argument() {
		final var sut = new LinkedList<String>();
		String[] array = {"member1", "member2", "member3", "member4", "member5"};
		sut.addAll(List.of(array));
		
		assertEquals(5, sut.size());
	}
	
	@Test
	void continuous_calls_to_list_iterator_previous_and_next_should_be_equal() {
		var sut = new LinkedList<String>();
		sut.add("first");
		sut.add("middle");
		sut.add("last");
		
		var sutListIterator = sut.listIterator();
		for (int i = 0; i < 10; i++)
			assertEquals(sutListIterator.next(), sutListIterator.previous());
		
		var sutListIterator2 = sut.listIterator();
		for (int i = 0; i < 10; i++)
			assertEquals(sutListIterator2.next(), sutListIterator2.previous());
	}
	
	@Test
	void iteration_through_iterator_should_equal_iteration_through_equal_array() {
		final var sut = new LinkedList<String>();
		sut.add("member0");
		sut.add("member1");
		sut.add("member2");
		sut.add("member3");
		sut.add("member4");
		final String[] arrayToCompare = {"member0", "member1", "member2", "member3", "member4"};
		final var sutIterator = sut.iterator();
		
		int i = 0;
		while (sutIterator.hasNext())
			assertEquals(arrayToCompare[i++], sutIterator.next());
		
		i = 0;
		for (var each : sut)
			assertEquals(arrayToCompare[i++], each);
		
	}
	
	@Test
	void iteration_through_list_iterator_should_equal_iteration_through_equal_array() {
		final var sut = new LinkedList<String>();
		sut.add("member0");
		sut.add("member1");
		sut.add("member2");
		sut.add("member3");
		sut.add("member4");
		final String[] arrayToCompare = {"member0", "member1", "member2", "member3", "member4"};
		final var sutIterator = sut.listIterator();
		
		int i = 0;
		while (sutIterator.hasNext())
			assertEquals(arrayToCompare[i++], sutIterator.next());
	}
	
	@Test
	void nearly_equal_files_of_different_lengths_should_be_unequal() {
		final var sut = new LinkedList<String>();
		sut.add("first");
		sut.add("second");
		sut.add("third");
		
		var sutLonger = new LinkedList<String>();
		sutLonger.add("first");
		sutLonger.add("second");
		sutLonger.add("third");
		sutLonger.add("fourth");
		
		assertNotEquals(sut, sutLonger);
	}
	
	@Test
	void added_collection_should_be_appended_at_end() {
		final var sut = new LinkedList<String>();
		String[] array = {"member0", "member1", "member2", "member3", "member4"};
		
		sut.addAll(List.of(array));
		
		assertEquals("member0", sut.get(0));
		assertEquals("member4", sut.get(4));
	}
	
	@Test
	void reversed_list_should_equal_list_created_in_reverse() {
		final var sut = new LinkedList<String>();
		sut.add("member0");
		sut.add("member1");
		sut.add("member2");
		sut.add("member3");
		sut.add("member4");
		
		var reversed = new LinkedList<String>();
		reversed.add("member4");
		reversed.add("member3");
		reversed.add("member2");
		reversed.add("member1");
		reversed.add("member0");
		
		assertEquals(reversed, sut.reverse());
	}
	
	@Test
	void lists_with_equal_elements_should_be_equal_regardless_of_type() {
		final var sut = new LinkedList<String>();
		final var sut2 = new ArrayList<String>();
		assertEquals(sut, sut2);
		
		sut.add("Hey");
		sut.add("Hey Again");
		
		sut2.add("Hey");
		sut2.add("Hey Again");
		
		assertEquals(sut, sut2);
	}
	
	@Test
	void lists_with_unequal_elements_should_be_unequal_regardless_of_type() {
		final var sut = new LinkedList<String>();
		final var sut2 = new ArrayList<String>();
		final var sut3 = new ArrayList<String>();
		
		sut.add("Hey");
		sut.add("Hey Again");
		
		sut2.add("Hey");
		sut2.add("Hey Different");
		
		sut3.add("Hey");
		sut3.add("Hey Different");
		
		assertNotEquals(sut, sut2);
		assertNotEquals(sut, sut3);
	}
	
	
	@Test
	void cleared_list_should_be_empty() {
		final var sut = new LinkedList<String>();
		sut.add("David");
		sut.add("Sarah");
		sut.add("Johnson");
		
		sut.clear();
		assertTrue(sut.isEmpty());
	}
	
	@Test
	void uncleared_list_should_not_be_empty() {
		final var sut = new LinkedList<String>();
		sut.add("David");
		sut.add("Sarah");
		sut.add("Johnson");
		
		assertFalse(sut.isEmpty());
	}
	
	@Test
	void to_string_should_return_clean_string_representation_for_empty_and_nonempty_lists() {
		final var sut = new LinkedList<String>();
		
		assertEquals("[]", sut.toString());
		
		sut.add("David");
		sut.add("Sarah");
		sut.add("Johnson");
		assertEquals("[David, Sarah, Johnson]", sut.toString());
	}
}