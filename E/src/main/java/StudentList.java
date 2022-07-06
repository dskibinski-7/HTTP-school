import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StudentList {
    List<Student> students = new ArrayList<>();
    //Map<Integer, Student> students = new HashMap<>();
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public StudentList() {
        Student s1 = new Student();
        s1.setIndex("135889");
        s1.setName("Dawid");
        s1.setSurname("Skibiński");
        try {
            s1.setBirth_date(formatter.parse("04/07/1998"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        s1.setGrades(new double[]{2,5,3,4.5});

        Student s2 = new Student();
        s2.setIndex("142234");
        s2.setName("Przemo");
        s2.setSurname("Kaszalot");
        try {
            s2.setBirth_date(formatter.parse("02/01/1996"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        s2.setGrades(new double[]{4,5,3.5,3.5});

        students.add(s1);
        students.add(s2);
    }

    public List<Student> getStudents() {
        return students;
    }

    public Student getStudent(String index) {
        for(Student s : students) {
            if(s.getIndex().equals(index)){
                return s;
            }
        }
        return null;
    }

    public void addStudent(Student s) {
        students.add(s);
    }

    //jak ma wyglądać update?
    public void updateStudent(Student s) {
        for (Student s_from_repo : students) {
            if (s_from_repo.getIndex().equals(s.getIndex())){
                s_from_repo.setName(s.getName());
                s_from_repo.setSurname(s.getSurname());
                s_from_repo.setBirth_date(s.getBirth_date());
                s_from_repo.setGrades(s.getGrades());
                //oceny ostatecznie i tak nie beda z tego poziomu ustawiane, a z poziomu id oceny
            }
        }
    }
}
