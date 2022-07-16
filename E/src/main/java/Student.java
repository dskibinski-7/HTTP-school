import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@XmlRootElement
public class Student {
    //private String index;
    private static final AtomicInteger counter_for_id = new AtomicInteger(0);
    private String index; //zostawiam jako stringa aby w razie co szybko wrocic do poprzedniego rozwiazania
    private String firstName;
    private String lastName;
    private Date birthday;
    //private double[] grades;

    public AtomicInteger getCounter_for_id() {
        return counter_for_id;
    }

    public String getIndex() {
        return index;
    }
    public void setIndex(String index) {
        this.index = index;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }



    //tutaj może bardziej dodanie oceny do zbioru?
    //plus funkcja ekstrahująca pojedynczą ocenę ze zbioru (na podstawie id)
    //zatem oceny może jako słownik?
    //OCENA POWINNA BYC PODZASOBEM STUDENTA (extends) - ZATEM ODDZIELNA KLASA DO OCEN
//    public double[] getGrades() {
//        return grades;
//    }
//    public void setGrades(double[] grades) {
//        this.grades = grades;
//    }

    //non-arg Constructor
    public Student() {

    }
}
