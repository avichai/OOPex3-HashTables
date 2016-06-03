package ex3;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * ג€‹A class that has a main method that measures the run-times requested in
 * the ג€�Performance Analysisג€� section.
 * 
 * @author Avichai
 *
 */
public class SimpleSetPerformanceAnalyzer {

	private static final int TIMES_TO_CHECK = 5000;

	private ChainedHashSet chainedHashSet;

	private OpenHashSet openHashSet;

	private TreeSet<String> treeSet;
	private CollectionFacadeSet wrapedTreeSet;

	private LinkedList<String> linkedList;
	private CollectionFacadeSet wrapedLinkedList;

	private HashSet<String> hashSet;
	private CollectionFacadeSet wrapedHashSet;

	private SimpleSet[] collections;

	private String[] collectionNames;

	/**
	 * Creates a new object for tests.
	 */
	private SimpleSetPerformanceAnalyzer() {
		this.chainedHashSet = new ChainedHashSet();
		this.openHashSet = new OpenHashSet();

		this.treeSet = new TreeSet<>();
		this.wrapedTreeSet = new CollectionFacadeSet(treeSet);

		this.linkedList = new LinkedList<>();
		this.wrapedLinkedList = new CollectionFacadeSet(linkedList);

		this.hashSet = new HashSet<>();
		this.wrapedHashSet = new CollectionFacadeSet(hashSet);

		SimpleSet[] temp = { this.chainedHashSet, this.openHashSet,
				this.wrapedTreeSet, this.wrapedLinkedList, this.wrapedHashSet };
		this.collections = temp;

		String[] temp1 = { Constant.CHAINED_HASH_SET_NAME,
				Constant.OPEN_HASH_SET_NAME, Constant.WRAPED_TREE_SET_NAME,
				Constant.WRAPED_LINKED_LIST_SET_NAME,
				Constant.WRAPED_HASH_SET_NAME };
		this.collectionNames = temp1;
	}

	/**
	 * 
	 * @return the array of collections the instance has.
	 */
	public SimpleSet[] getCollections() {
		return collections;
	}

	/**
	 * 
	 * @return the array of collections names the instance has.
	 */
	public String[] getCollectionNames() {
		return collectionNames;
	}

	/**
	 * Adding an array of string to a given data structure.
	 * 
	 * @param data
	 *            an array of string
	 * @param collection
	 *            a given data structure
	 */
	private static void addWordArrayToDataStructure(String[] data,
			SimpleSet collection) {
		if (data != null && collection != null) {
			for (String word : data) {
				if (word != null) {
					collection.add(word);
				}
			}
		}
	}

	/**
	 * the tests for adding data to a data structure.
	 * 
	 * @param data
	 *            an array of string
	 * @param collections
	 *            a given array of data structures
	 * @param collectionNames
	 *            a given array of data structures names.
	 */
	// private static void firstAndSecondTest(String[] data,
	// SimpleSet[] collections, String[] collectionNames) {
	// if (data != null && collections != null && collectionNames != null) {
	// System.out.println(Constant.PERFORM_ADD_TO_STRUCTURE_TEST);
	// int i = 0;
	// for (SimpleSet collection : collections) {
	// long timeBefore = System.nanoTime();
	// System.out.println(Constant.STRAT_WITH_DATA_STRUCTUR
	// + collectionNames[i]);
	// addWordArrayToDataStructure(data, collection);
	// System.out.println(Constant.END_WITH_DATA_STRUCTUR
	// + collectionNames[i]);
	// long difference = System.nanoTime() - timeBefore;
	// long reultInMiliSecond = difference
	// / Constant.CHANGE_TO_MILI_SECOND;
	// System.out
	// .println(String
	// .format(Constant.THE_TIME_IT_TOOK_FOR_THE_DATA_STUCTURE_TO_ADD_THE_DATA,
	// collectionNames[i], reultInMiliSecond));
	// i++;
	// System.out.println(Constant.FINISHED_ADD_TO_STRUCTURE_TEST);
	// System.out.println();
	// }
	// }
	// }

	private static void firstAndSecondTest1(String[] data,
			SimpleSet[] collections, String[] collectionNames, String dataName) {
		if (data != null && collections != null && collectionNames != null) {
			int i = 0;
			for (SimpleSet collection : collections) {
				// long sum = 0;
				// for (int j=0; j<TIMES_TO_CHECK; j++){
				long timeBefore = System.nanoTime();
				addWordArrayToDataStructure(data, collection);
				long difference = System.nanoTime() - timeBefore;
				// sum += difference;
				// }
				// sum = sum/TIMES_TO_CHECK;
				// long reultInMiliSecond = sum
				// / Constant.CHANGE_TO_MILI_SECOND;
				long reultInMiliSecond = difference
						/ Constant.CHANGE_TO_MILI_SECOND;
				System.out
						.println(String
								.format(Constant.THE_TIME_IT_TOOK_FOR_THE_DATA_STUCTURE_TO_ADD_THE_DATA,
										collectionNames[i], dataName,
										reultInMiliSecond));
				i++;
				System.out.println();
			}
		}
	}

	/**
	 * the tests for searching in a data.
	 * 
	 * @param collections
	 *            a given array of data structures
	 * @param toSearch
	 *            the string the function should look for.
	 * @param collectionNames
	 *            a given array of data structures names.
	 */
	// private static void thirdToSixedTest(SimpleSet[] collections,
	// String toSearch, String[] collectionNames) {
	// if (toSearch != null && collections != null && collectionNames != null) {
	// System.out.println(Constant.PERFORM_CONTAIN_TEST);
	// int i = 0;
	// for (SimpleSet collection : collections) {
	// long timeBefore = System.nanoTime();
	// System.out.println(Constant.STRAT_WITH_DATA_STRUCTUR
	// + collectionNames[i]);
	// if (collection.contains(toSearch)) {
	// System.out
	// .println(Constant.THE_WORD_WAS_FOUND_IN_THIS_DATA_STRUCTURE);
	// } else {
	// System.out
	// .println(Constant.THE_WORD_IS_NOT_IN_THIS_DATA_STRUCTURE);
	// }
	// System.out.println(Constant.END_WITH_DATA_STRUCTUR
	// + collectionNames[i]);
	// long difference = System.nanoTime() - timeBefore;
	// System.out
	// .println(String.format
	// (Constant.THE_TIME_IT_TOOK_FOR_THE_DATA_STUCTURE_TO_SEARCH_THE_The_STRING,
	// collectionNames[i], toSearch, difference));
	// i++;
	// System.out.println(Constant.FINISHED_CONTAIN_TEST);
	// System.out.println();
	// }
	// }
	// }

	private static void thirdToSixedTest1(SimpleSet[] collections,
			String toSearch, String[] collectionNames, String dataName) {
		if (toSearch != null && collections != null && collectionNames != null) {
			int i = 0;
			for (SimpleSet collection : collections) {
				long sum = 0;
				for (int j = 0; j < TIMES_TO_CHECK; j++) {
					long timeBefore = System.nanoTime();
					collection.contains(toSearch);
					long difference = System.nanoTime() - timeBefore;
					sum += difference;
				}
				System.out
						.println(String
								.format(Constant.THE_TIME_IT_TOOK_FOR_THE_DATA_STUCTURE_TO_SEARCH_THE_The_STRING,
										collectionNames[i], toSearch, dataName,
										sum / TIMES_TO_CHECK));
				i++;
				System.out.println();
			}
		}
	}

	public static void main(String[] args) {

		SimpleSetPerformanceAnalyzer first = new SimpleSetPerformanceAnalyzer();
		SimpleSetPerformanceAnalyzer second = new SimpleSetPerformanceAnalyzer();

		String[] data1 = Ex3Utils
				.file2array("C:/Users/Avichai/Desktop/object orianted/תרגיל 3/ex3/src/ex3/data1.txt");
		String[] data2 = Ex3Utils
				.file2array("C:/Users/Avichai/Desktop/object orianted/תרגיל 3/ex3/src/ex3/data2.txt");

		firstAndSecondTest1(data1, first.getCollections(),
				first.getCollectionNames(), "data1");
		thirdToSixedTest1(first.getCollections(), "hi",
				first.getCollectionNames(), "data1");
		thirdToSixedTest1(first.getCollections(), "-13170890158",
				first.getCollectionNames(), "data1");

		firstAndSecondTest1(data2, second.getCollections(),
				second.getCollectionNames(), "data2");
		thirdToSixedTest1(second.getCollections(), "23",
				second.getCollectionNames(), "data2");
		thirdToSixedTest1(second.getCollections(), "hi",
				second.getCollectionNames(), "data2");
	}
}
