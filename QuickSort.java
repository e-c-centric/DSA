public class QuickSort {
    public static void main(String[] args) {
        int[] arr = { 10, 8, 5, 2 };
        quickSort(arr, 0, arr.length - 1);
        for (int i : arr)
            System.out.print(i + " ");
    }

    public static void quickSort(int[] arr, int start, int end) {
        if (arr == null || arr.length == 0)
            return;
        if (start >= end)
            return;
        if (isSorted(arr)) {
            System.err.println("\u001B[31m" + "Array is already sorted" + "\u001B[0m");
            return;
        }
        int pivot = partition(arr, start, end);
        quickSort(arr, start, pivot - 1);
        quickSort(arr, pivot + 1, end);
    }

    public static int partition(int[] arr, int start, int end) {
        int pivot = arr[end];
        int i = start - 1;
        for (int j = start; j < end; j++)
            if (arr[j] < pivot)
                swap(arr, ++i, j);
        swap(arr, ++i, end);
        return i;
    }

    public static void swap(int[] arr, int i, int j) {
        if (i == j)
            return;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static boolean isSorted(int[] arr) {
        if (arr[0] < arr[arr.length - 1]) {
            for (int i = 1; i < arr.length; i++)
                if (arr[i - 1] > arr[i])
                    return false;
        } else if (arr[0] > arr[arr.length - 1]) {
            for (int i = arr.length - 1; i > 0; i--)
                if (arr[i - 1] > arr[i])
                    return false;
        }
        return true;
    }
}
