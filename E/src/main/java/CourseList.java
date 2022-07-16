import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseList {
    public static Map<Integer, Course> courses = new HashMap<>();

    public CourseList() {
        Course c1 = new Course();
        //c1.setIndex(1);
        c1.setId(c1.getCounter_for_id().incrementAndGet());
        c1.setName("UM");
        c1.setLecturer("Miazga");

        Course c2 = new Course();
        //c2.setIndex(2);
        c2.setId(c2.getCounter_for_id().incrementAndGet());
        c2.setName("WF");
        c2.setLecturer("Bartkowiak");

        Course c3 = new Course();
        //c3.setIndex(3);
        c3.setId(c3.getCounter_for_id().incrementAndGet());
        c3.setName("AM");
        c3.setLecturer("Wójcik");

        courses.put(c1.getId(), c1);
        courses.put(c2.getId(), c2);
        courses.put(c3.getId(), c3);
    }

    public List<Course> getCourses() {
        return new ArrayList<Course>(courses.values());
    }

    public Course getCourse(int id) {
        return courses.get(id);
    }

    public void addCourse(Course c) {
        courses.put(c.getId(), c);
    }

    public void updateCourse(Course c) {
        courses.put(c.getId(), c);
    }

    public Course deleteCourse(int index) {
        var output = courses.remove(index);
        return output;
    }
}
