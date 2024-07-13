public class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private int enrolledStudents;
    private String schedule;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolledStudents = 0;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getEnrolledStudents() {
        return enrolledStudents;
    }

    public String getSchedule() {
        return schedule;
    }

    public boolean isAvailable() {
        return enrolledStudents < capacity;
    }

    public void enrollStudent() {
        if (isAvailable()) {
            enrolledStudents++;
        } else {
            System.out.println("Course capacity is full.");
        }
    }

    public void dropStudent() {
        if (enrolledStudents > 0) {
            enrolledStudents--;
        } else {
            System.out.println("No students to drop.");
        }
    }

    @Override
    public String toString() {
        return "Course Code: " + courseCode + ", Title: " + title + ", Description: " + description +
               ", Capacity: " + capacity + ", Enrolled Students: " + enrolledStudents + ", Schedule: " + schedule;
    }
}