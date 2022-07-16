import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StudentList {
    //List<Student> students = new ArrayList<>();

    public static Map<String, Student> students;
    static {
        students = new HashMap<>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Student s1 = new Student();
        //s1.setIndex("135889");
        s1.setIndex(String.valueOf(s1.getCounter_for_id().incrementAndGet()));
        s1.setFirstName("Dawid");
        s1.setLastName("Skibiński");
        try {
            s1.setBirthday(formatter.parse("04/07/1998"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //s1.setGrades(new double[]{2,5,3,4.5});

        Student s2 = new Student();
        //s2.setIndex("142234");
        s2.setIndex(String.valueOf(s2.getCounter_for_id().incrementAndGet()));
        s2.setFirstName("Przemo");
        s2.setLastName("Kaszalot");
        try {
            s2.setBirthday(formatter.parse("02/01/1996"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //s2.setGrades(new double[]{4,5,3.5,3.5});


        students.put(s1.getIndex(), s1);
        students.put(s2.getIndex(), s2);

    }



//    public static Map<String, Student> students = new HashMap<>();
//    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//
//    public StudentList() {
//        Student s1 = new Student();
//        s1.setIndex("135889");
//        s1.setFirstName("Dawid");
//        s1.setLastName("Skibiński");
//        try {
//            s1.setBirthday(formatter.parse("04/07/1998"));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        //s1.setGrades(new double[]{2,5,3,4.5});
//
//        Student s2 = new Student();
//        s2.setIndex("142234");
//        s2.setFirstName("Przemo");
//        s2.setLastName("Kaszalot");
//        try {
//            s2.setBirthday(formatter.parse("02/01/1996"));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        //s2.setGrades(new double[]{4,5,3.5,3.5});
//
//
//        students.put(s1.getIndex(), s1);
//        students.put(s2.getIndex(), s2);
//    }

    public List<Student> getStudents() {
        return new ArrayList<Student>(students.values());
    }

    public Student getStudent(String index) {
        return students.get(index);
//        for(Student s : students) {
//            if(s.getIndex().equals(index)){
//                return s;
//            }
//        }
//        return null;
    }

    public void addStudent(Student s) {
        students.put(s.getIndex(), s);
    }

    //
    public void updateStudent(Student s) {
        students.put(s.getIndex(), s);
    }

    public Student deleteStudent(String index) {
        var output = students.remove(index);
        return output;
        //zwraca null jezeli danego studenta nie ma juz na liscie
    }
}
