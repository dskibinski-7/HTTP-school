import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class Student {
    private String index;
    private String name;
    private String surname;
    private Date birth_date;
    private double[] grades;

    public String getIndex() {
        return index;
    }
    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirth_date() {
        return birth_date;
    }
    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    //tutaj może bardziej dodanie oceny do zbioru?
    //plus funkcja ekstrahująca pojedynczą ocenę ze zbioru (na podstawie id)
    //zatem oceny może jako słownik?
    //OCENA POWINNA BYC PODZASOBEM STUDENTA (extends) - ZATEM ODDZIELNA KLASA DO OCEN
    public double[] getGrades() {
        return grades;
    }
    public void setGrades(double[] grades) {
        this.grades = grades;
    }

    //non-arg Constructor
    public Student() {

    }
}
