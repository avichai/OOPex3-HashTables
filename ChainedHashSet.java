package ex3;

import java.util.LinkedList;

/**
 * A class for data structure chainedHashSet that extends SimpleHashSet.
 * 
 * @author Avichai
 *
 */
public class ChainedHashSet extends SimpleHashSet {
	private LinkedListFacadeSet[] chainedHashSetArray;

	/**
	 * A default constructor. Constructs a new, empty table with default initial
	 * capacity (16), upper load factor (0.75) and lower load factor (0.25).
	 */
	public ChainedHashSet() {
		super();
		this.chainedHashSetArray = new LinkedListFacadeSet[Constant.INITIAL_TABLE_SIZE];
	}

	/**
	 * Constructs a new, empty table with the specified load factors, and the
	 * default initial capacity (16).
	 * 
	 * @param upperLoadFactor
	 *            The upper load factor of the hash table.
	 * @param lowerLoadFactor
	 *            The lower load factor of the hash table.
	 */
	public ChainedHashSet(float upperLoadFactor, float lowerLoadFactor) {
		super(upperLoadFactor, lowerLoadFactor);
		this.chainedHashSetArray = new LinkedListFacadeSet[Constant.INITIAL_TABLE_SIZE];
	}

	/**
	 * Data constructor - builds the hash set by adding the elements one by one.
	 * Duplicate values should be ignored. The new table has the default values
	 * of initial capacity (16), upper load factor (0.75), and lower load factor
	 * (0.25).
	 * 
	 * @param data
	 *            Values to add to the set.
	 */
	public ChainedHashSet(java.lang.String[] data) {
		super();
		if (data != null) {
			this.chainedHashSetArray = new LinkedListFacadeSet[Constant.INITIAL_TABLE_SIZE];
			for (String elem : data) {
				if (elem != null) {
					add(elem);
				}
			}
		}
	}

	/**
	 * Add a specified element to the set if it's not already in it.
	 * 
	 * @param newValue
	 *            New value to add to the set
	 * @return False iff newValue already exists in the set
	 */
	public boolean add(java.lang.String newValue) {
		if (newValue != null) {
			return hashAndDoSomething(Constant.ADD, newValue);
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
	public boolean contains(java.lang.String searchVal) {
		if (searchVal != null) {
			return hashAndDoSomething(Constant.CONTAINS, searchVal);
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
	public boolean delete(java.lang.String toDelete) {
		if (toDelete != null) {
			return hashAndDoSomething(Constant.DELETE, toDelete);
		}
		return false;
	}

	/**
	 * @return The current capacity (number of cells) of the table.
	 */
	public int capacity() {
		return super.getTableSize();
	}

	/**
	 * do the requested action with the String input.
	 * 
	 * @param action
	 *            the action we want to do (add, search(contain, delete)
	 * @param valToHash
	 *            the String we want to do the action on.
	 * @return true if the action was done successfully.
	 */
	private boolean hashAndDoSomething(String action, String valToHash) {
		if (action != null && valToHash != null) {
			int indexToInsert = super.hash(valToHash);
			return doAction(action, indexToInsert, valToHash);
		}
		return false;

	}

	/**
	 * do the action we want to do (help function for hashAndDOSomething).
	 * 
	 * @param action
	 *            the action we want to do (add, search(contain, delete)
	 * @param cell
	 *            the cell at the table we are currently looking in.
	 * @param head
	 *            the head of the chain we are currently looking at.
	 * @param valTohash
	 *            the String we want to do the action on.
	 * @return true if the action was done successfully.
	 */
	private boolean doAction(String action, int cell, String valTohash) {
		if (action != null && valTohash != null) {
			if (action.equals(Constant.ADD)) {
				return dealWithAddAction(cell, valTohash);
			} else if (action.equals(Constant.CONTAINS)) {
				return dealWithContainsAction(cell, valTohash);
			} else if (action.equals(Constant.DELETE)) {
				return dealWithDeleteAction(cell, valTohash);
			} else {
				return false;
			}
		}
		return false;
	}

	/**
	 * try to add valTohash to the table.
	 * 
	 * @param cell
	 *            the cell at the table we are currently looking in.
	 * @param head
	 *            the head of the chain we are currently looking at.
	 * @param valTohash
	 *            the String we want to add to the table.
	 * @return true if valToHash was added to the table.
	 */
	private boolean dealWithAddAction(int cell, String valTohash) {
		if (valTohash != null) {
			if (this.chainedHashSetArray[cell] == null) {
				LinkedList<String> collection = new LinkedList<String>();
				this.chainedHashSetArray[cell] = new LinkedListFacadeSet(
						collection);
			}
			boolean addedToArray = this.chainedHashSetArray[cell]
					.add(valTohash);
			if (addedToArray) {
				super.setNumElemnt(super.getNumElemnt() + 1);
				super.changeTableSizeIfNeeded(true);
			}
			return addedToArray;
		}
		return false;
	}

	/**
	 * try to search valTohash in the table.
	 * 
	 * @param head
	 *            the head of the chain we are currently looking at.
	 * @param valTohash
	 *            the String we want to search in the table.
	 * @return true if valToHash was found in the table.
	 */
	private boolean dealWithContainsAction(int cell, String valTohash) {
		if (valTohash != null) {
			if (this.chainedHashSetArray[cell] == null) {
				return false;
			} else {
				return this.chainedHashSetArray[cell].contains(valTohash);
			}
		}
		return false;
	}

	/**
	 * try to delete valTohash from the table.
	 * 
	 * @param cell
	 *            the cell at the table we are currently looking in.
	 * @param head
	 *            the head of the chain we are currently looking at.
	 * @param valTohash
	 *            the String we want to delete from the table.
	 * @return true if valToHash was deleted from the table.
	 */
	private boolean dealWithDeleteAction(int cell, String valTohash) {
		if (valTohash != null) {
			if (this.chainedHashSetArray[cell] != null) {
				boolean deletedFromArray = this.chainedHashSetArray[cell]
						.delete(valTohash);
				if (deletedFromArray) {
					super.setNumElemnt(super.getNumElemnt() - 1);
					super.changeTableSizeIfNeeded(false);
				}
				return deletedFromArray;
			}
		}
		return false;
	}

	/**
	 * implement the method from SimpleHashSet.
	 */
	protected void moveOldTable(int newTableSize) {
		String[] data = saveOldArray();
		refreshTable(newTableSize, data);
	}

	/**
	 * make a new table with a new size and the old data.
	 * 
	 * @param newTableSize
	 *            the size for the new table.
	 * @param data
	 *            the data to insert to the new table.
	 */
	private void refreshTable(int newTableSize, String[] data) {
		if (data != null) {
			super.setNumElemnt(0);
			super.setTableSize(newTableSize);
			super.setTableSizeMinusOne(super.getTableSize() - 1);
			this.chainedHashSetArray = new LinkedListFacadeSet[newTableSize];
			for (String elem : data) {
				if (elem != null) {
					add(elem);
				}
			}
		}
	}

	/**
	 * copy the data from the array to a new array.
	 * 
	 * @return an array with the old data with the old table size.
	 */
	private String[] saveOldArray() {
		String[] data = new String[super.getTableSize()]; // there can't be more
															// element then the
															// table size.
		int i = 0;
		for (LinkedListFacadeSet linkedList : this.chainedHashSetArray) {
			if (linkedList != null) {
				for (String word : linkedList.linkedList()) {
					data[i] = word;
					i++;
				}
			}
		}
		return data;
	}

	public void display() {
		for (LinkedListFacadeSet linkedList : this.chainedHashSetArray) {
			if (linkedList != null) {
				for (String a : linkedList.linkedList()) {
					if (a != null) {
						System.out.println(a);
					}
				}
			}
		}
	}
}
