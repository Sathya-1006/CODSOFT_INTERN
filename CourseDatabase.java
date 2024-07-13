

import java.util.ArrayList;
import java.util.List;

public class CourseDatabase {
    private List<Course> courses;

    public CourseDatabase() {
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public Course getCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }

    public void displayAvailableCourses() {
        for (Course course : courses) {
            System.out.println(course);
        }
    }
}
