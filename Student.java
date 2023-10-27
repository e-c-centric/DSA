public class Student {
    private String name;
    private int age;
    public int id;

    public Student(String name, int age, int id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public boolean equals(Student other) {
        return this.id == (other.id);
    }

    public String toString() {
        return name + " (" + id + "), " + age + " years old";
    }

    /**
     * Returns true if this student's ID is less than the other student's ID.
     * 
     * @param other the other student
     * @return true if this student's ID is less than the other student's ID
     */
    public boolean lessThan(Student other) {
        return this.id < other.id;
    }
}
