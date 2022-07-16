import javax.xml.bind.annotation.XmlRootElement;
import java.util.concurrent.atomic.AtomicInteger;

@XmlRootElement
public class Course {
    private static final AtomicInteger counter_for_id = new AtomicInteger(0);
    private int id;
    private String name;
    private String lecturer;


    public AtomicInteger getCounter_for_id() {
        return counter_for_id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLecturer() {
        return lecturer;
    }
    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public Course() {

    }

}