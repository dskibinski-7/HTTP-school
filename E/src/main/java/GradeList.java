import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GradeList {

    public static Map<Integer, Grade> grades = new HashMap<>();
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    CourseList courses_repository = new CourseList();
    StudentList students_repository = new StudentList();


    public GradeList() {
        Grade g1 = new Grade();
        //g1.setIndex(1);
        g1.setId(g1.getCounter_for_id().incrementAndGet());
        g1.setCourse(courses_repository.getCourse(1)); //moze po prostu course id?
        g1.setOwner(students_repository.getStudent("1")); //index Student //jezeli nie, to stworzyc tutaj student/course list, pobrac ja i wtedy geta zrobic
        g1.setValue(3.5);
        try {
            g1.setDate(formatter.parse("07/07/2022"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Grade g2 = new Grade();
        //g2.setIndex(2);
        g2.setId(g2.getCounter_for_id().incrementAndGet());
        g2.setCourse(courses_repository.getCourse(1)); //moze po prostu course id?
        g2.setOwner(students_repository.getStudent("1")); //index Student //jezeli nie, to stworzyc tutaj student/course list, pobrac ja i wtedy geta zrobic
        g2.setValue(4.0);
        try {
            g2.setDate(formatter.parse("03/07/2022"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Grade g3 = new Grade();
        //g3.setIndex(3);
        g3.setId(g3.getCounter_for_id().incrementAndGet());
        g3.setCourse(courses_repository.getCourse(2)); //moze po prostu course id?
        g3.setOwner(students_repository.getStudent("1")); //index Student //jezeli nie, to stworzyc tutaj student/course list, pobrac ja i wtedy geta zrobic
        g3.setValue(4.0);
        try {
            g3.setDate(formatter.parse("01/07/2022"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Grade g4 = new Grade();
        //g4.setIndex(4);
        g4.setId(g4.getCounter_for_id().incrementAndGet());
        g4.setCourse(courses_repository.getCourse(1)); //moze po prostu course id?
        g4.setOwner(students_repository.getStudent("2")); //index Student //jezeli nie, to stworzyc tutaj student/course list, pobrac ja i wtedy geta zrobic
        g4.setValue(4.5);
        try {
            g4.setDate(formatter.parse("03/07/2022"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        grades.put(g1.getId(), g1);
        grades.put(g2.getId(), g2);
        grades.put(g3.getId(), g3);
        grades.put(g4.getId(), g4);
    }

    //Zwraca oceny określonego studenta
    public List<Grade> getGrades(String student_index) {
        //return new ArrayList<Grade>(grades.values());
        ArrayList<Grade> all_grades_list = new ArrayList<Grade>(grades.values());
        ArrayList<Grade> certain_student_grades_list = new ArrayList<>();

        for (Grade g : all_grades_list) {
            if(g.getOwner().getIndex().equals(student_index)) {
                certain_student_grades_list.add(g);
            }
        }

        return certain_student_grades_list;
    }

    public Grade getGrade(int index) {
        return grades.get(index);
    }

    public void addGrade(Grade g) {
        grades.put(g.getId(), g);
    }

    public void updateGrade(Grade g) {
        //ustawienie informacji o przedmiocie (obiekt) na podstawie id
        g.setCourse(courses_repository.getCourse(g.getCourse().getId()));
        grades.put(g.getId(), g);
    }

    public Grade deleteGrade(int index) {
        var output = grades.remove(index);
        return output;
    }

    public void deleteCourseWithGrades(int CourseId){
        //set z kluczami do usuniecia, czyli (warunek ponizej)
        Set<Integer> keys_to_remove = new HashSet<>();
        for (Map.Entry<Integer, Grade> entry: grades.entrySet()){
            Integer key = entry.getKey();
            Grade value = entry.getValue();


            //jezeli id kursu w ocenie pokrywa sie z usuwanym id kursu -> usun ocene
            if (value.getCourse().getId() == CourseId) {
                //grades.remove(key);
                keys_to_remove.add(key);
            }
        }
        grades.keySet().removeAll(keys_to_remove);

    }
}
