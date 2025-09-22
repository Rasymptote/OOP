package ru.nsu.babich;

/**
 * Heapsort class.
 */
public final class Heapsort {

    /**
     * Heapsort method that sorts an array.
     *
     * @param numbers array to be sorted.
     */
    public static void heapsort(int[] numbers) {
        for (int i = numbers.length; i >= 0; i--) {
            heapify(numbers, numbers.length, i);
        }
        for (int i = numbers.length - 1; i > 0; i--) {
            swap(numbers, i, 0);
            heapify(numbers, i, 0);
        }
    }

    private static void heapify(int[] numbers, int heapSize, int heapRootIndex) {
        while ((2 * heapRootIndex + 1) < heapSize) {
            int leftChild = 2 * heapRootIndex + 1;
            int rightChild = 2 * heapRootIndex + 2;
            int largest = leftChild;

            if (rightChild < heapSize && numbers[rightChild] > numbers[leftChild]) {
                largest = rightChild;
            }

            if (numbers[heapRootIndex] >= numbers[largest]) {
                break;
            }
            swap(numbers, heapRootIndex, largest);
            heapRootIndex = largest;
        }
    }

    private static void swap(int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }
}
