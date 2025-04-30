import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class j {

	public static int[] randomArray(int n) {
		
		// Declare an array of integers of size n.
		int[] array = new int[n];
		
		// Every element in the array is replaced by a random integer from 0 to n.
		for (int i = 0; i < n; i++) array[i] = ThreadLocalRandom.current().nextInt(n);
		
		return array;
	}

	public static void swapElements(int[] array, int i, int j) {
		
		int temp;
		temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public static long bubbleSort(int[] array) {
		
		long startTime = System.nanoTime();
		int iterations = 0;
		
		while (iterations < array.length - 1) {
			
			// For every element in the array appart from the last one, if the element to its right is smaller, swap the two elements.
			for (int i = 0; i < array.length - 1 - iterations; i++) if (array[i] > array[i + 1]) swapElements(array, i, i + 1);
			
			// The variable iterations is used to shorten the algorithm by limiting iterations of the loop to only elements unsorted by the algorithm.
			iterations++;
		}
		
		return System.nanoTime() - startTime;
	}
	
	public static long selectionSort(int[] array) {
		
		long startTime = System.nanoTime();
		int min;
		int minIndex = 0;
		
		// Keeps track of the index of the first element unsorted by the algorithm.
		int currentStart = 0;
			
		while (currentStart < array.length) {
			min = array[currentStart];
			
			// For every element from currentStart to the end of the array, find the smallest value and its index.
			for (int i = currentStart; i < array.length; i++) {
				if (array[i] <= min) {
					min = array[i];
					minIndex = i;
				}
			}
			
			//Swap the element at currentStart with the smallest index.
			swapElements(array, currentStart, minIndex);
			currentStart++;
		}
		
		return System.nanoTime() - startTime;
	}
	
	public static long insertionSort(int[] array) {
		
		long startTime = System.nanoTime();
		
		// Keeps track of the first element unsorted by the algorithm.
		int j = 0;
		
		// For every element in the array starting from the second element,
		for (int i = 1; i < array.length; i++) {
			j = i;
			
			// while the element to check is greater than the first element,
			while (j > 0) {
				
				// if the element before is smaller,
				if (array[j] < array[j - 1]) {
					
					// swap the elements and decrement the index of the element to check.
					swapElements(array, j, j - 1);
					j--;
				} else break;
			}
		}
	
		return System.nanoTime() - startTime;
	}
	
	public static long countingSort(int[] array) {
		
		long startTime = System.nanoTime();
		int max = 0;
		int index = 0;
		
		// For each element in the array, if the element is greater than the current max, that element is the new max.
		for (int i : array) if (i > max) max = i;
		
		// Declare an array of size max + 1
		int[] counts =  new int[max + 1];
		
		for (int i : array) counts[i]++;
		
		// For every element in counts, 
		for (int i = 0; i < counts.length; i++) {
			
			// for the number of times of the value of the current index in counts (j),
			// replace the next j elements in the array with the value of the current index in counts.
			for (int j = 0; j < counts[i]; j++) {
				array[index] = i;
				index++;
			}
			
		}
		
		return System.nanoTime() - startTime;
	}
	
	public static void quickSort(int[] array, int startIndex, int endIndex) {
		
		int i = startIndex;
		int j = endIndex;
		int pivot = array[startIndex];
		
		while (i < j) {
			
			// Finds the first value from the left that is less than or equal to the pivot, while i < j.
			while (array[i] < pivot && i < j) i++;
			
			// Finds the first value from the right that is less than the pivot, while i < j.
			while (array[j] >= pivot && i < j) j--;
			
			// If the indexes have met, the partition has been created.
			if (i == j) break;
			
			// Otherwise, swap the elements at i and j.
			swapElements(array, i, j);
			i++;
			j--;
		}
		
		// If the value at i is less than the pivot, call quickSort for both partitions, with the index at i being part of the left partition.
		if (array[i] < pivot) {
			quickSort(array, startIndex, i);
			quickSort(array, i + 1, endIndex);
			
		// If the value at i is greater than the pivot, call quickSort for both partitions, with the index at i being part of the right partition.
		} else if (array[i] > pivot) {
			quickSort(array, startIndex, i - 1);
			quickSort(array, i, endIndex);
			
		// Otherwise, if the index i has not reached the rightmost index,
		} else if (i != endIndex) {
			
			// if i is greater than j, call quickSort for only the left partition, from the starting index to i inclusive.
			if (i > j) quickSort(array, startIndex, i);
			
			// Otherwise, if i and j are equal, but not equal to the starting index, call quickSort for only the left partition, from the starting index to i exclusive.
			else if (i == j && j != startIndex) quickSort(array, startIndex, i - 1);
			
			// If i is at least 2 indexes away from the end index, call quickSort for only the right partition, from i exclusive to the end index.
			if (i + 1 < endIndex) quickSort(array, i + 1, endIndex);
		}
	}
	
	public static void main(String[] args) {	
	
		int[] sampleSize = {100, 250, 500, 750, 1000, 1250, 2500, 3750, 5000, 6250, 7500, 8750, 10000};
		int numRuns = 10;
		int[] array;
		
		// Variables for algorithm sorting times.
		long bubbleSortTime;
		long selectionSortTime;
		long insertionSortTime;
		long countingSortTime;
		long quickSortTime;
		long startTime;
		
		// Variables for algorithm sorting time outputs (Allows for the same array to be used for each algorithm).
		String sizeOutput = "Size\t";
		for (int n : sampleSize) sizeOutput += "\t" + n;
		String bubbleSortOutput = "Bubble sort\t";
		String selectionSortOutput = "Selection sort\t";
		String insertionSortOutput = "Insertion sort\t";
		String countingSortOutput = "Counting sort\t";
		String quickSortOutput = "Quick sort\t";
		
		
		// For each sample size,
		for (int n : sampleSize) {
			bubbleSortTime = 0;
			selectionSortTime = 0;
			insertionSortTime = 0;
			countingSortTime = 0;
			quickSortTime = 0;
			
			// Repeat the sort on a new array numRuns times.
			for (int i = 0; i < numRuns; i++) {
				
				// Generates a new random array of size n, with int elements from 0 - n.
				array = randomArray(n);
				
				// The sorting algorithms sort a copy of the array with a different object ID.
				// This is done so that the same array can be used in each algorithm for fairness.
				// The sorting algorithms return their running time in nanoseconds.
				bubbleSortTime += bubbleSort(Arrays.copyOf(array, array.length));
				selectionSortTime += selectionSort(Arrays.copyOf(array, array.length));
				insertionSortTime += insertionSort(Arrays.copyOf(array, array.length));
				countingSortTime += countingSort(Arrays.copyOf(array, array.length));
				
				// The orginal array is passed to quickSort.
				// There is no need to pass in a copy as it is the last algorithm to sort for the current array.
				// The quickSort algorithm does not return a running time. Since it is recursive, the running time
				// is measured from the start of the original quickSort call until the call finishes.
				startTime = System.nanoTime();
				quickSort(array, 0, array.length - 1);				
				quickSortTime += System.nanoTime() - startTime;
			}
			
			// Converts the nanosecond time to milliseconds,
			// gets the mean average run time for each size n,
			// and formats the output to 3 decimal places.
			bubbleSortOutput += String.format("%.3f\t", bubbleSortTime / (1000000.0d * numRuns));
			selectionSortOutput += String.format("%.3f\t", selectionSortTime / (1000000.0d * numRuns));
			insertionSortOutput += String.format("%.3f\t", insertionSortTime / (1000000.0d * numRuns));
			countingSortOutput += String.format("%.3f\t", countingSortTime / (1000000.0d * numRuns));
			quickSortOutput += String.format("%.3f\t", quickSortTime / (1000000.0d * numRuns));
			
		}
		
		System.out.println(sizeOutput);
		System.out.println(bubbleSortOutput);
		System.out.println(selectionSortOutput);
		System.out.println(insertionSortOutput);
		System.out.println(countingSortOutput);
		System.out.println(quickSortOutput);
	}
	
}