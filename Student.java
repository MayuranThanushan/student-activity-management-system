/**
 * Represents a student with a unique ID, name, and associated academic module.
 */
public class Student {
    private String id;
    private String name;
    Module module;

    /**
     * Constructs a new Student object with the specified ID and name.
     * Initializes the student's ID and name based on the provided parameters.
     * Additionally, initializes a new Module object associated with this student.
     * @param id the unique identifier of the student
     * @param name the name of the student
     */
    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.module = new Module(this);
    }

    /**
     * Retrieves the ID of the student.
     * @return the ID of the student
     */
    public String getId() {
        return id;
    }

    /**
     * Retrieves the NAME of the student.
     * @return the NAME of the student
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the ID of the student.
     * @param id the new ID to set for the student
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Sets the NAME of the student.
     * @param name the new NAME to set for the student
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the module associated with the student.
     * @return the Module object associated with the student
     */
    public Module getModule() {
        return module;
    }

}
