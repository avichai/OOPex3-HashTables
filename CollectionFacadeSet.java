package ex3;

/**
 * Wraps an underlying Collection and serves to both simplify its API and give
 * it a common type with the implemented SimpleHashSets.
 * 
 * @author Avichai
 *
 */
public class CollectionFacadeSet implements SimpleSet {

	private java.util.Collection<java.lang.String> collection;

	/**
	 * Creates a new facade wrapping the specified collection.
	 * 
	 * @param collection
	 *            The Collection to wrap.
	 */
	public CollectionFacadeSet(java.util.Collection<java.lang.String> collection) {
		if (collection != null) {
			this.collection = collection;
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
		if (newValue != null) {
			if (contains(newValue)) {
				return false;
			} else {
				this.collection.add(newValue);
				return true;
			}
		}
		return false;
	}

	/**
	 * Look for a specified value in the set.
	 * 
	 * @param searchVal
	 *            Value to search for
	 * @return True iff searchVal is found in the set
	 */
	public boolean contains(String searchVal) {
		if (searchVal != null) {
			return this.collection.contains(searchVal);
		}
		return false;
	}

	/**
	 * Remove the input element from the set.
	 * 
	 * @param toDelete
	 *            Value to delete
	 * @return True iff toDelete is found and deleted
	 */
	public boolean delete(String toDelete) {
		if (toDelete != null) {
			if (!contains(toDelete)) {
				return false;
			} else {
				this.collection.remove(toDelete);
				return true;
			}
		}
		return false;
	}

	/**
	 * @return The number of elements currently in the set
	 */
	public int size() {
		return this.collection.size();
	}

}
