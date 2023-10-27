public class StudentMain {
    private Student[] studentArray;
    private int size;

    public StudentMain(int capacity) {
        studentArray = new Student[capacity];
        size = 0;
    }

    public void bubbleSort(){
        for (int i = 0; i < studentArray.length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < studentArray.length - i - 1; j++) {
                if (studentArray[j].lessThan(studentArray[j + 1])) {
                    Student temp = studentArray[j];
                    studentArray[j] = studentArray[j + 1];
                    studentArray[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    public void binarySearch(Student student) {
        int low = 0;
        int high = studentArray.length - 1;
        while (low <= high) {
            int mid = (low + high)/2;
            if (studentArray[mid].equals(student)) {
                System.out.println(studentArray[mid]);
                return;
            } else if (studentArray[mid].lessThan(student)) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        System.out.println("Student not found");
    }

    public static void main(String[] args) {
        StudentMain studentMain = new StudentMain(5);
        studentMain.studentArray[0] = new Student("John", 20, 123);
        studentMain.studentArray[1] = new Student("Jane", 19, 456);
        studentMain.studentArray[2] = new Student("Bob", 21,789);
        studentMain.studentArray[3] = new Student("Mary", 18, 101);
        studentMain.studentArray[4] = new Student("Alice", 22, 112);

        studentMain.bubbleSort();

        for (Student student : studentMain.studentArray) {
            System.out.println(student);
        }

        System.out.println("test");
        studentMain.binarySearch(new Student("Bob", 21, 789));
        studentMain.binarySearch(new Student("Alice", 22, 112));
        studentMain.binarySearch(new Student("John", 20, 123));
        studentMain.binarySearch(new Student("Mary", 18, 101));
        studentMain.binarySearch(new Student("Jane", 19, 456));
        studentMain.binarySearch(new Student("Bob", 21, 000));

    }
}