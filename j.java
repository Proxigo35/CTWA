import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class j {

	public static int[] randomArray(int n) {
		int[] array = new int[n];
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
			for (int i = 0; i < array.length - 1 - iterations; i++) if (array[i] > array[i + 1]) swapElements(array, i, i + 1);
			iterations++;
		}
		return System.nanoTime() - startTime;
	}
	
	public static long selectionSort(int[] array) {
		long startTime = System.nanoTime();
		int min;
		int minIndex = 0;
		int currentStart = 0;
			
		while (currentStart < array.length) {
			min = array[currentStart];
			for (int i = currentStart; i < array.length; i++) {
				if (array[i] <= min) {
					min = array[i];
					minIndex = i;
				}
			}
			swapElements(array, currentStart, minIndex);
			currentStart++;
		}
		return System.nanoTime() - startTime;
	}
	
	public static long insertionSort(int[] array) {
		long startTime = System.nanoTime();
		int j = 0;
		
		for (int i = 1; i < array.length; i++) {
			j = i;   
			while (j >= 1) {
				if (array[j] < array[j - 1]) {
					swapElements(array, j, j - 1);
					j--;
				} else j = 0;
			}
		}
		return System.nanoTime() - startTime;
	}
	
	public static long countingSort(int[] array) {
		long startTime = System.nanoTime();
		int max = 0;
		int index = 0;
		
		for (int i : array) if (i > max) max = i;
		int[] counts =  new int[max + 1];
		
		for (int i : array) counts[i]++;
		
		for (int i = 0; i < counts.length; i++) {
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
			while (array[i] < pivot && i < j) i++;
			while (array[j] >= pivot && j > i) j--;
			if (i == j) break;
			swapElements(array, i, j);
			i++;
			j--;
		}
		
		if (array[i] < pivot) {
			quickSort(array, startIndex, i);
			quickSort(array, i + 1, endIndex);
		} else if (array[i] > pivot) {
			quickSort(array, startIndex, i - 1);
			quickSort(array, i, endIndex);
		} else if (i != endIndex) {
			if (i > j) quickSort(array, startIndex, j);
			else if (i == j && j != startIndex) quickSort(array, startIndex, i - 1);
			quickSort(array, i + 1, endIndex);
		}
	}
	
	public static void main(String[] args) {		
		int[] sampleSize = {100, 250, 500, 750, 1000, 1250, 2500, 3750, 5000, 6250, 7500, 8750, 10000};
		int numRuns = 10;
		int[] array;
		long bubbleSortTime;
		long selectionSortTime;
		long insertionSortTime;
		long countingSortTime;
		long quickSortTime;
		long startTime;
		
		String sizeOutput = "Size\t";
		for (int n : sampleSize) sizeOutput += "\t" + n;
		String bubbleSortOutput = "Bubble sort\t";
		String selectionSortOutput = "Selection sort\t";
		String insertionSortOutput = "Insertion sort\t";
		String countingSortOutput = "Counting sort\t";
		String quickSortOutput = "Quick sort\t";
		
		for (int n : sampleSize) {
			bubbleSortTime = 0;
			selectionSortTime = 0;
			insertionSortTime = 0;
			countingSortTime = 0;
			quickSortTime = 0;
			
			for (int i = 0; i < numRuns; i++) {
				array = randomArray(n);
				bubbleSortTime += bubbleSort(Arrays.copyOf(array, array.length));
				selectionSortTime += selectionSort(Arrays.copyOf(array, array.length));
				insertionSortTime += insertionSort(Arrays.copyOf(array, array.length));
				countingSortTime += countingSort(Arrays.copyOf(array, array.length));
				
				startTime = System.nanoTime();
				quickSort(array, 0, array.length - 1);
				quickSortTime += System.nanoTime() - startTime;
				
			}
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