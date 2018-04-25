package cisc187.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * {@link SequenceList} Unit tests
 */
public class TestArrayStack {

	private Stack<Integer> foo;

	@Before
	public void setUp() {
		foo = new ArrayStack<Integer>();
	}

	@Test
	public void initConstructor() {
		assertEquals("Failed to init.", 0, foo.size());
	}

	@Test
	public void pushOneSize() {
		foo.push(42);
		assertEquals("Failed to update size.", 1, foo.size());
	}

	@Test
	public void pushOne() {
		foo.push(42);
		assertEquals("Failed to push item.", new Integer(42), foo.peek());
	}

	public void pushStuff() {
		for (int i = 1; i <= 100; i++) {
			foo.push(i);
		}
	}

	@Test
	public void pushMoreSize() {
		pushStuff();
		assertEquals("Failed to update size.", 100, foo.size());
	}

	@Test
	public void pushMoreAndPop3() {
		pushStuff();
		foo.pop();
		foo.pop();
		foo.pop();
		assertEquals("Failed to att 100 and pop 3 items.", new Integer(97), foo.peek());
	}

	//
	// Check expected exceptions
	//
	@Test(expected = EmptyStackException.class)
	public void verifyEmptyStackExceptionPop() {
		foo = new ArrayStack<>();
		foo.pop();
	}

	@Test(expected = EmptyStackException.class)
	public void verifyEmptyStackExceptionPeek() {
		foo = new ArrayStack<>();
		foo.peek();
	}

	@Test(expected = NoSuchElementException.class)
	public void verifyNoSuchElementException() {
		pushStuff();
		Iterator<Integer> itr = foo.iterator();
		while (itr.hasNext()) {
			itr.next();
		}
		itr.next();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void verifyUnsupportedOperationException() {
		pushStuff();
		Iterator<Integer> itr = foo.iterator();
		itr.remove();
	}

	//
	// Check the iterator
	//
	@Test
	public void stackIterator() {
		pushStuff();
		int i = 1;
		for (Integer itr : foo) {
			assertEquals("Failed to use iterator to get item.", new Integer(i++), itr);
		}
	}
	// if there are 100 things in the stack, i should go to 100

	@Test
	public void countIterator() {
		pushStuff();
		int i = 0;
		for (Integer itr : foo) {
			i++;
		}
		assertEquals("Failed to increment iterator to end.", 100, i);
	}

	@Test
	public void manualIterator() {
		pushStuff();
		Iterator<Integer> itr = foo.iterator();
		for (int i = 1; itr.hasNext(); i++) {
			assertEquals("Failed to use iterator to get item.", new Integer(i), itr.next());
		}
	}

}
