package ex3;

/**
 * A class for data structure openHashSet that extends SimpleHashSet.
 * 
 * @author Avichai
 *
 */
public class OpenHashSet extends SimpleHashSet {

	private String[] hashTable;

	/**
	 * A default constructor. Constructs a new, empty table with default initial
	 * capacity (16), upper load factor (0.75) and lower load factor (0.25).
	 */
	public OpenHashSet() {
		super();
		this.hashTable = new String[Constant.INITIAL_TABLE_SIZE];
		fillWithEmptyStrings();
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
	public OpenHashSet(float upperLoadFactor, float lowerLoadFactor) {
		super(upperLoadFactor, lowerLoadFactor);
		this.hashTable = new String[Constant.INITIAL_TABLE_SIZE];
		fillWithEmptyStrings();
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
	public OpenHashSet(java.lang.String[] data) {
		super();
		if (data != null) {
			this.hashTable = new String[Constant.INITIAL_TABLE_SIZE];
			fillWithEmptyStrings();
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
			return hashAndDoSomething(Constant.ADD, newValue, newValue);
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
			return hashAndDoSomething(Constant.CONTAINS, searchVal,
					Constant.EMPTY);
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
			return hashAndDoSomething(Constant.DELETE, toDelete, Constant.EMPTY);
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
	 * fills the hash table with empty strings.
	 */
	private void fillWithEmptyStrings() {
		for (int i = 0; i < this.hashTable.length; i++) {
			this.hashTable[i] = Constant.EMPTY;
		}
	}

	/**
	 * do the requested action with the String input.
	 * 
	 * @param action
	 *            the action we want to do (add, search(contain, delete)
	 * @param valToHash
	 *            the String we want to do the action on.
	 * @param falseInput
	 *            if we get this String in the hashed cell we return false.
	 * @return true if the action was done successfully.
	 */
	private boolean hashAndDoSomething(String action, String valToHash,
			String falseInput) {
		if (action != null && valToHash != null && falseInput != null) {
			int hashedIndex = super.hash(valToHash);

			for (int i = 0; i <= capacity(); i++) {
				int currentCell = (hashedIndex + (i + i ^ 2) / 2)
						& super.getTableSizeMinusOne();
				if (this.hashTable[currentCell] != null
						&& this.hashTable[currentCell].equals(falseInput)) {
					if (action.equals(Constant.DELETE)
							|| action.equals(Constant.CONTAINS)) {
						if (this.hashTable[currentCell] == Constant.EMPTY) {
							return false;
						}
					} else if (action.equals(Constant.ADD)) {
						if (this.hashTable[currentCell] != Constant.EMPTY) {
							return false;
						}
					}
				}
				boolean succeed = doAction(action, currentCell, valToHash);
				if (succeed) {
					return true;
				}

			}
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
	 * try to delete valTohash from the table.
	 * 
	 * @param cell
	 *            the cell at the table we are currently looking in.
	 * @param valTohash
	 *            the String we want to delete from the table.
	 * @return true if valToHash was deleted from the table.
	 */
	private boolean dealWithDeleteAction(int cell, String valTohash) {
		if (valTohash != null) {
			if (this.hashTable[cell] != null
					&& this.hashTable[cell].equals(valTohash)) {
				if (this.hashTable[cell] != Constant.EMPTY) {
					this.hashTable[cell] = null;
					super.setNumElemnt(super.getNumElemnt() - 1);
					super.changeTableSizeIfNeeded(false);
					return true;
				}
			} else {
				return false;
			}
		}
		return false;
	}

	/**
	 * try to search valTohash in the table.
	 * 
	 * @param cell
	 *            the cell at the table we are currently looking in.
	 * @param valTohash
	 *            the String we want to search in the table.
	 * @return true if valToHash was found in the table.
	 */
	private boolean dealWithContainsAction(int cell, String valTohash) {
		if (valTohash != null) {
			if (this.hashTable[cell] != null) {
				if (this.hashTable[cell].equals(valTohash)
						&& this.hashTable[cell] != Constant.EMPTY) {
					return true;
				}
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
	 * @param valTohash
	 *            the String we want to add to the table.
	 * @return true if valToHash was added to the table.
	 */
	private boolean dealWithAddAction(int cell, String valTohash) {
		if (valTohash != null) {
			if (this.hashTable[cell] != null) {
				if (this.hashTable[cell] == (Constant.EMPTY)) {
					this.hashTable[cell] = valTohash;
					super.setNumElemnt(super.getNumElemnt() + 1);
					super.changeTableSizeIfNeeded(true);
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

	/**
	 * implement the method from SimpleHashSet.
	 */
	protected void moveOldTable(int newTableSize) {
		String[] cleanTable = removeEmptySpacesInTable(this.hashTable);
		if (cleanTable != null) {
			this.hashTable = new String[newTableSize];
			fillWithEmptyStrings();
			super.setTableSize(newTableSize);
			super.setTableSizeMinusOne(super.getTableSize() - 1);
			super.setNumElemnt(0);
			for (String word : cleanTable) {
				if (word == null || word == Constant.EMPTY) {
					break;
				}
				add(word);
			}
		}
	}

	/**
	 * remove empty string and null pointer from the array.
	 * 
	 * @param tableToClean
	 *            the hash table we wish to clean.
	 * @return a new cleaned table.
	 */
	private String[] removeEmptySpacesInTable(String[] tableToClean) {
		if (tableToClean != null) {
			// there can't be more element then the table size.
			String[] cleanList = new String[super.getTableSize()];
			int i = 0;
			for (String word : tableToClean) {
				if (word != null && word != Constant.EMPTY) {
					cleanList[i] = word;
					i++;
				}
			}
			return cleanList;
		}
		return null;
	}

	public void display() {
		for (String a : this.hashTable) {
			if (a != null && a != Constant.EMPTY) {
				System.out.println(a);
			}
		}
	}
}
