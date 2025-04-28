import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
//Test comment

public class j {
	
	public static int[] randomArray(int n) {
		int[] array = new int[n];
		int temp;
		int randomIndex;
		
		for (int i = 0; i < n; i++) {
			array[i] = i + 1;
		}

		for (int i = 0; i < n; i++) {
			randomIndex = ThreadLocalRandom.current().nextInt(0, n);
			temp = array[i];
			array[i] = array[randomIndex];
			array[randomIndex] = temp;
		}
		return array;
	}
	
	public static long bubbleSort(int[] array) {
		long startTime = System.nanoTime();
		int temp;
		int iterations = 0;
		boolean unsorted = true;
		while (unsorted) {
			unsorted = false;
			for (int i = 0; i < array.length - 1 - iterations; i++) {
				if (array[i + 1] < array[i]) {
					unsorted = true;
					temp = array[i + 1];
					array[i + 1] = array[i];
					array[i] = temp;
				}
			}
			iterations++;
		}
		return System.nanoTime() - startTime;
	}
		
	public static long selectionSort(int[] array) {
		long startTime = System.nanoTime();
		int temp;
		int largestElement = 0;
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

			temp = array[currentStart];
			array[currentStart] = array[minIndex];
			array[minIndex] = temp;
			currentStart++;
		}
		return System.nanoTime() - startTime;
	}
	
	public static long insertionSort(int[] array) {
		long startTime = System.nanoTime();
		int temp;
		int j = 0;
		for (int i = 1; i < array.length; i++) {
			j = i;   
			while (j >= 1) {
				if (array[j] < array[j - 1]) {
					temp = array[j - 1];
					array[j - 1] = array[j];
					array[j] = temp;
					j--;
				} else {
					j = 0;
				} 
			}
		}
		return System.nanoTime() - startTime;
	}
	
	public static long quickSort(int[] array) {
		long startTime = System.nanoTime();
		return System.nanoTime() - startTime;
	}
	
	public static long countingSort(int[] array) {
		long startTime = System.nanoTime();
		return System.nanoTime() - startTime;
	}
	
	public static void main(String[] args) {
		int[] sampleSize = {100, 250, 500, 750, 1000, 1250, 2500, 3750, 5000, 6250, 7500, 8750, 10000};
		int numRuns = 1;
		long startTime;
		long totalTime;
		int[] array;
		
		String sizeOutput = "Size\t";
		for (int n : sampleSize) {
			sizeOutput += "\t" + n;
		}
		String bubbleSortOutput = "Bubble sort\t";
		String selectionSortOutput = "Selection sort\t";
		String insertionSortOutput = "Insertion sort\t";
		
		for (int n : sampleSize) {
			long bubbleSortTime = 0;
			long selectionSortTime = 0;
			long insertionSortTime = 0;
			
			for (int i = 0; i < numRuns; i++) {
				array = randomArray(n);
				int[] copyOfArray = new int[array.length];
				
				for (int j = 0; j < copyOfArray.length; j++) {
					copyOfArray[j] = array[j];
				}
				
				bubbleSortTime += bubbleSort(array);
				array = copyOfArray;
				selectionSortTime += selectionSort(array);
				array = copyOfArray;
				insertionSortTime += insertionSort(array);
				array = copyOfArray;
			}
			bubbleSortOutput += String.format("%.3f\t", bubbleSortTime / (1000000.0d * numRuns));
			selectionSortOutput += String.format("%.3f\t", selectionSortTime / (1000000.0d * numRuns));
			insertionSortOutput += String.format("%.3f\t", insertionSortTime / (1000000.0d * numRuns));
			
		}
		System.out.println(sizeOutput);
		System.out.println(bubbleSortOutput);
		System.out.println(selectionSortOutput);
		System.out.println(insertionSortOutput);
		
		
		// for (int n : sampleSize) {
			// totalTime = 0;
			// for (int i = 0; i < numRuns; i++) {
				// array = randomArray(n);
				// startTime = System.nanoTime();
				// bubbleSort(array);
				// totalTime += System.nanoTime() - startTime;
			// }
			// System.out.printf("%.3f\t", totalTime / (1000000.0d * numRuns));
		// }
		
		// System.out.print("\nSelection sort\t");
		// for (int n : sampleSize) {
			// totalTime = 0;
			// for (int i = 0; i < numRuns; i++) {
				// array = randomArray(n);
				// startTime = System.nanoTime();
				// selectionSort(array);			
				// totalTime += System.nanoTime() - startTime;
			// }
			// System.out.printf("%.3f\t", totalTime / (1000000.0d * numRuns));
		// }
		
		// System.out.print("\nInsertion sort\t");
		// for (int n : sampleSize) {
			// totalTime = 0;
			// for (int i = 0; i < numRuns; i++) {
				// array = randomArray(n);
				// startTime = System.nanoTime();
				// insertionSort(array);
				// totalTime += System.nanoTime() - startTime;
			// }
			// System.out.printf("%.3f\t", totalTime / (1000000.0d * numRuns));
		// }
	}
}