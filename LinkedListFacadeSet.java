package ex3;

/**
 * Wraps a linked list collection and serves to both simplify its API and for
 * the ability to keep an array of linked lists.
 * 
 * @author Avichai
 *
 */
public class LinkedListFacadeSet implements SimpleSet {

	private java.util.LinkedList<java.lang.String> collection;
	private CollectionFacadeSet linkedListCollection;

	/**
	 * Creates a new facade wrapping the specified collection.
	 * 
	 * @param collection
	 *            The Collection to wrap.
	 */
	public LinkedListFacadeSet(java.util.LinkedList<java.lang.String> collection) {
		if (collection != null) {
			this.collection = collection;
			this.linkedListCollection = new CollectionFacadeSet(this.collection);
		}
	}

	/**
	 * Add a specified element to the set if it's not already in it.
	 * 
	 * @param newValue
	 *            New value to add to the set
	 * @return False iff newValue already exists in the set
	 */
	public boolean add(String newValue) {
		return this.linkedListCollection.add(newValue);
	}

	/**
	 * Look for a specified value in the set.
	 * 
	 * @param searchVal
	 *            Value to search for
	 * @return True iff searchVal is found in the set
	 */
	public boolean contains(String searchVal) {
		return this.linkedListCollection.contains(searchVal);
	}

	/**
	 * Remove the input element from the set.
	 * 
	 * @param toDelete
	 *            Value to delete
	 * @return True iff toDelete is found and deleted
	 */
	public boolean delete(String toDelete) {
		return this.linkedListCollection.delete(toDelete);
	}

	/**
	 * @return The number of elements currently in the set
	 */
	public int size() {
		return this.linkedListCollection.size();
	}

	/**
	 * 
	 * @return the collection we are currently working with.
	 */
	public java.util.LinkedList<java.lang.String> linkedList() {
		return this.collection;
	}
}
