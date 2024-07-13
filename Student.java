

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private String studentID;
    private String name;
    private List<Course> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(Course course) {
        if (course.isAvailable()) {
            registeredCourses.add(course);
            course.enrollStudent();
            JOptionPane.showMessageDialog(null, "Course registered successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "Course is full. Cannot register.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.remove(course)) {
            course.dropStudent();
            JOptionPane.showMessageDialog(null, "Course dropped successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "Course not found in registered courses.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public String toString() {
        return "Student ID: " + studentID + ", Name: " + name + ", Registered Courses: " + registeredCourses.size();
    }
}
