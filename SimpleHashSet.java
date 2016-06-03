package ex3;

/**
 * A superclass for implementations of hash-sets implementing the SimpleSet
 * interface.
 * 
 * @author Avichai
 *
 */
public abstract class SimpleHashSet implements SimpleSet {
	
	private final float upperLoadFactor;
	private final float lowerLoadFactor;
	private int tableSize;
	private int tableSizeMinusOne;
	private int numElemnt;
	private double loadFactor;

	/**
	 * A default constructor. Initial the hash set, upper load factor (0.75) and
	 * lower load factor (0.25).
	 */
	public SimpleHashSet() {
		this.upperLoadFactor = Constant.UPPER_LOAD_FACTOR;
		this.lowerLoadFactor = Constant.LOWER_LOAD_FACTOR;
		this.tableSize = Constant.INITIAL_TABLE_SIZE;
		this.tableSizeMinusOne = this.tableSize - 1;
		this.numElemnt = 0;
		this.loadFactor = 0;
	}

	/**
	 * Initial the hash set, with the specified load factors.
	 * 
	 * @param upperLoadFactor
	 *            The upper load factor of the hash table.
	 * @param lowerLoadFactor
	 *            The lower load factor of the hash table.
	 */
	public SimpleHashSet(float upperLoadFactor, float lowerLoadFactor) {
		if (upperLoadFactor <= Constant.MAX_UPPER_LOAD_FACTOR
				&& upperLoadFactor > lowerLoadFactor
				&& lowerLoadFactor >= Constant.MIN_LOWE_LOAD_FACTOR) {
			this.upperLoadFactor = upperLoadFactor;
			this.lowerLoadFactor = lowerLoadFactor;
		} else {
			this.upperLoadFactor = Constant.UPPER_LOAD_FACTOR;
			this.lowerLoadFactor = Constant.LOWER_LOAD_FACTOR;
		}
		this.tableSize = Constant.INITIAL_TABLE_SIZE;
		this.tableSizeMinusOne = this.tableSize - 1;
		this.numElemnt = 0;
		this.loadFactor = 0;
	}

	/**
	 * 
	 * @return The current capacity (number of cells) of the table.
	 */
	public abstract int capacity();

	/**
	 * @return The number of elements currently in the set
	 */
	public int size() {
		return this.numElemnt;
	}

	/**
	 * try the table size (increase or decrease) if needed according to the
	 * given boolean.
	 * 
	 * @param addedOrRemoved
	 *            true if we want to try to increase and false if we want to try
	 *            to decrease.
	 */
	protected void changeTableSizeIfNeeded(boolean addedOrRemoved) {
		this.loadFactor = (double) (size()) / (double) (capacity());
		if (addedOrRemoved) {
			if (this.loadFactor > this.upperLoadFactor) {
				increaseTableSize();
			}
		} else if (this.loadFactor < this.lowerLoadFactor) {
			decreaseTableSize();
		}
	}

	/**
	 * Increase the table size to old size*2.
	 */
	protected void increaseTableSize() {
		int oldTableSize = this.tableSize;
		int newTableSize = oldTableSize <<= 1;
		moveOldTable(newTableSize);
	}

	/**
	 * Decrease the table size to old size/2.
	 */
	protected void decreaseTableSize() {
		int oldTableSize = this.tableSize;
		int newTableSize = oldTableSize >>= 1;
		moveOldTable(newTableSize);
	}

	/**
	 * copy the old hash table into a new hash table with the given size.
	 * 
	 * @param newTableSize
	 *            the size of the new hash table.
	 */
	protected abstract void moveOldTable(int newTableSize);

	/**
	 * The hash function for a given string.
	 * 
	 * @param word
	 *            a string we want to hash.
	 * @return the calculation of the hash function on the word.
	 */
	protected int hash(String word) {
		return word.hashCode() & this.tableSizeMinusOne;
		// return word.hashCode() % this.tableSize; // this takes more time.
	}

	/**
	 * 
	 * @return the table size.
	 */
	public int getTableSize() {
		return this.tableSize;
	}

	/**
	 * sets the table size.
	 * 
	 * @param tableSize
	 *            the new table size.
	 */
	public void setTableSize(int tableSize) {
		if ( tableSize >= Constant.MIN_FOR_TABLE_SIZE){
			this.tableSize = tableSize;
		}
	}

	/**
	 * 
	 * @return the number of elements in the table.
	 */
	public int getNumElemnt() {
		return this.numElemnt;
	}

	/**
	 * sets the number of element in the table.
	 * 
	 * @param numElemnt
	 *            the new number of element in the table.
	 */
	public void setNumElemnt(int numElemnt) {
		if(numElemnt >= Constant.MIN_FOR_NUM_ELEMENTS){
			this.numElemnt = numElemnt;
		}
	}

	/**
	 * 
	 * @return the table size minus one.
	 */
	public int getTableSizeMinusOne() {
		return this.tableSizeMinusOne;
	}

	/**
	 * sets the table size minus one field.
	 * 
	 * @param tableSizeMinusOne
	 *            the new number for table size minus one field.
	 */
	public void setTableSizeMinusOne(int tableSizeMinusOne) {
		if(tableSizeMinusOne >= Constant.MIN_FOR_TABLE_SIZE_MINUS_ONE){
			this.tableSizeMinusOne = tableSizeMinusOne;
		}
	}

}
