import java.util.Arrays;

/**
 * Represents a module with module marks and associated grade for a student.
 */
public class Module {
    private int[] moduleMarks;
    private String grade;
    Student student;

    /**
     * Constructs a new Module object for the specified student.
     * Initializes module marks array with three elements.
     * @param student the student associated with this module
     */
    public Module(Student student) {
        this.student = student;
        this.moduleMarks = new int[3];
    }

    /**
     * Retrieves the module marks.
     * @return the array of module marks
     */
    public int[] getModuleMarks() {
        return moduleMarks;
    }

    /**
     * Sets the module marks and calculates the grade.
     * @param moduleMarks the array of module marks to set
     */
    public void setModuleMarks(int[] moduleMarks) {
        this.moduleMarks = moduleMarks;
        calculateGrade();
    }

    /**
     * Retrieves the grade of the module.
     * @return the grade of the module
     */
    public String getGrade() {
        return grade;
    }

    /**
     * Calculates the grade based on the average of module marks.
     */
    public void calculateGrade() {
        double average = calculateAverage();
        if (average >= 80) {
            grade = "Distinction";
        } else if (average >= 70) {
            grade = "Merit";
        } else if (average >= 40) {
            grade = "Pass";
        } else {
            grade = "Fail";
        }
    }

    /**
     * Calculates the average of module marks.
     * @return the average of module marks
     */
    public double calculateAverage() {
        int total = Arrays.stream(moduleMarks).sum();
        double markAverage = total / 3.0;
        return Math.ceil(markAverage * 100) / 100.0;
    }
}
