public class MergeSort {
    public static void main(String[] args) {
        int[] arr = { 5, 2, 4, 6, 1, 3 };
        mergeSort(arr, 0, arr.length - 1);
        printArray(arr);
    }

    public static void mergeSort(int[] arr, int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;

            mergeSort(arr, p, q);
            mergeSort(arr, q + 1, r);

            merge(arr, p, q, r);
        }
    }

    public static void merge(int[] arr, int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;

        // Create two arrays
        int[] L = new int[n1 + 1];
        int[] R = new int[n2 + 1];

        // Copy data to arrays
        for (int i = 0; i < n1; i++) {
            L[i] = arr[p + i];
        }

        for (int j = 0; j < n2; j++) {
            R[j] = arr[q + j + 1];
        }

        // Set sentinel values
        L[n1] = Integer.MAX_VALUE;
        R[n2] = Integer.MAX_VALUE;

        // Merge
        int i = 0;
        int j = 0;

        for (int k = p; k <= r; k++) {
            if (L[i] <= R[j]) {
                arr[k] = L[i++]; // Post-increment
            } else {
                arr[k] = R[j++];
            }
        }
    }

    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
