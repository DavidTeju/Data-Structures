package lists.linkedList;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {
	
	@Test
	void new_list_should_be_empty() {
		final var sut = new LinkedList<>();
		assert sut.isEmpty();
	}
	
	@Test
	void querying_new_list_should_throw_index_out_of_bounds_exception() {
		final var sut = new LinkedList<>();
		assertThrows(IndexOutOfBoundsException.class, () -> sut.get(0));
		assertThrows(IndexOutOfBoundsException.class, () -> sut.get(1));
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
		sut.add("member0");
		sut.add("member1");
		sut.add("member2");
		sut.add("member3");
		sut.add("member4");
		
		String[] array = {"member5", "member6", "member7", "member8", "member9"};
		sut.addAll(List.of(array));
		
		assertEquals("member5", sut.get(5));
		assertEquals("member9", sut.get(9));
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
	void lists_should_be_equal_regardless_of_type() {
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
	void clearedListShouldBeEmpty() {
		final var sut = new LinkedList<String>();
		sut.add("David");
		sut.add("Sarah");
		sut.add("Johnson");
		
		sut.clear();
		assert sut.isEmpty();
	}
	
	@Test
	void unclearedListShouldNotBeEmpty() {
		final var sut = new LinkedList<String>();
		sut.add("David");
		sut.add("Sarah");
		sut.add("Johnson");
		
		assertFalse(sut.isEmpty());
	}
	
	@Test
	void toStringShouldReturnCleanStringRepresentationForEmptyOrNot() {
		final var sut = new LinkedList<String>();
		assertEquals("[]", sut.toString());
		
		sut.add("David");
		sut.add("Sarah");
		sut.add("Johnson");
		
		assertEquals("[David -> Sarah -> Johnson]", sut.toString());
	}
}