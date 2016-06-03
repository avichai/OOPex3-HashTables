package ex3;

/**
 * class for the constants of this project.
 * 
 * @author Avichai
 *
 */
public class Constant {

	public static final float UPPER_LOAD_FACTOR = 0.75f;
	public static final float LOWER_LOAD_FACTOR = 0.25f;
	public static final int MAX_UPPER_LOAD_FACTOR = 1;
	public static final int MIN_LOWE_LOAD_FACTOR = 0;
	public static final int INITIAL_TABLE_SIZE = 16;
	public static final String EMPTY = new String("");
	public static final String ADD = "add";
	public static final String CONTAINS = "contains";
	public static final String DELETE = "delete";
	public static final String CHAINED_HASH_SET_NAME = "chainedHashSet";
	public static final String OPEN_HASH_SET_NAME = "openHashSet";
	public static final String WRAPED_TREE_SET_NAME = "wrapedTreeSet";
	public static final String WRAPED_LINKED_LIST_SET_NAME = "wrapedLinkedList";
	public static final String WRAPED_HASH_SET_NAME = "wrapedHashSet";
	public static final int CHANGE_TO_MILI_SECOND = 1000000;
	public static final String THE_TIME_IT_TOOK_FOR_THE_DATA_STUCTURE_TO_ADD_THE_DATA = 
			"The time it took for the data stucture %s to add %s is %d";
	public static final String THE_TIME_IT_TOOK_FOR_THE_DATA_STUCTURE_TO_SEARCH_THE_The_STRING = 
			"The time it took for the data stucture %s to search the string %s inside %s is %d";
	public static final String b = "";
	public static final int MIN_FOR_NUM_ELEMENTS = 0;
	public static final int MIN_FOR_TABLE_SIZE_MINUS_ONE = 0;
	public static final int MIN_FOR_TABLE_SIZE = 1;

	// don't want anyone to create instance of this.
	private Constant() {
	}

}
