import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private static CourseDatabase courseDB = new CourseDatabase();
    private static StudentDatabase studentDB = new StudentDatabase();

    public static void main(String[] args) {
        // Adding some courses for demonstration
        courseDB.addCourse(new Course("CS101", "Intro to Computer Science", "Basics of CS", 2, "MWF 10-11AM"));
        courseDB.addCourse(new Course("MATH101", "Calculus I", "Differential Calculus", 2, "TTh 2-3:30PM"));
        courseDB.addCourse(new Course("PHYS101", "Physics I", "Mechanics", 2, "MWF 1-2PM"));

        // Create the main frame
        JFrame frame = new JFrame("Course Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Create buttons
        JButton displayCoursesButton = new JButton("Display Available Courses");
        JButton registerCourseButton = new JButton("Register for a Course");
        JButton dropCourseButton = new JButton("Drop a Course");
        JButton addStudentButton = new JButton("Add a New Student");
        JButton exitButton = new JButton("Exit");

        // Set layout and add buttons to frame
        frame.setLayout(new GridLayout(5, 1));
        frame.add(displayCoursesButton);
        frame.add(registerCourseButton);
        frame.add(dropCourseButton);
        frame.add(addStudentButton);
        frame.add(exitButton);

        // Add action listeners
        displayCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayCourses();
            }
        });

        registerCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerCourse();
            }
        });

        dropCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dropCourse();
            }
        });

        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Set frame visibility
        frame.setVisible(true);
    }

    private static void displayCourses() {
        StringBuilder coursesList = new StringBuilder();
        for (Course course : courseDB.getCourses()) {
            coursesList.append(course).append("\n");
        }
        JOptionPane.showMessageDialog(null, coursesList.toString(), "Available Courses", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void registerCourse() {
        String studentID = JOptionPane.showInputDialog("Enter Student ID:");
        Student student = studentDB.getStudentByID(studentID);
        if (student != null) {
            String courseCode = JOptionPane.showInputDialog("Enter Course Code:");
            Course course = courseDB.getCourseByCode(courseCode);
            if (course != null) {
                student.registerCourse(course);
            } else {
                JOptionPane.showMessageDialog(null, "Course not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Student not found. Please add the student first.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void dropCourse() {
        String studentID = JOptionPane.showInputDialog("Enter Student ID:");
        Student student = studentDB.getStudentByID(studentID);
        if (student != null) {
            String courseCode = JOptionPane.showInputDialog("Enter Course Code:");
            Course course = courseDB.getCourseByCode(courseCode);
            if (course != null) {
                student.dropCourse(course);
            } else {
                JOptionPane.showMessageDialog(null, "Course not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Student not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void addStudent() {
        String studentID = JOptionPane.showInputDialog("Enter Student ID:");
        if (studentDB.getStudentByID(studentID) == null) {
            String name = JOptionPane.showInputDialog("Enter Student Name:");
            studentDB.addStudent(new Student(studentID, name));
            JOptionPane.showMessageDialog(null, "Student added successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "Student with this ID already exists.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
