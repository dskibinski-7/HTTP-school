import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@XmlRootElement
public class Grade {
    private static final AtomicInteger counter_for_id = new AtomicInteger(0);
    int id;
    double value;
    Date date;
    Course course;
    Student owner;

    public AtomicInteger getCounter_for_id() {
        return counter_for_id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }
    public void setValue(double value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getOwner() {
        return owner;
    }
    public void setOwner(Student owner) {
        this.owner = owner;
    }

    public Grade() {

    }

}

