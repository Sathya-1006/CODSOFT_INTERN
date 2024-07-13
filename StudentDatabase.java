
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class StudentDatabase {
    private Map<String, Student> students;
    private String databaseFile = "students.dat";

    public StudentDatabase() {
        this.students = new HashMap<>();
        loadStudents();
    }

    private void loadStudents() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(databaseFile))) {
            students = (Map<String, Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No existing student data found. Starting with an empty database.");
        }
    }

    private void saveStudents() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(databaseFile))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving student data: " + e.getMessage());
        }
    }

    public void addStudent(Student student) {
        students.put(student.getStudentID(), student);
        saveStudents();
    }

    public Student getStudentByID(String studentID) {
        return students.get(studentID);
    }
}
