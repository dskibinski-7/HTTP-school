import org.glassfish.jersey.server.Uri;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;


//@Singleton
@Path("students")
public class StudentResource {


    StudentList students_repository = new StudentList();
    GradeList grades_repository = new GradeList();

    //?
    CourseList courses_repository = new CourseList();


    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Student> getStudents() throws ParseException {
        //return Response.status(Response.Status.OK).entity(students_repository.getStudents()).build();
        return students_repository.getStudents();
    }

    @GET @Path("/{index}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getStudent(@PathParam("index") String index) {
        Student student_to_show =  students_repository.getStudent(index);
        if (student_to_show!=null){
            //return student_to_show;
            return Response.status(Response.Status.OK).entity(student_to_show).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response addStudent(Student s, @Context UriInfo uriInfo) throws IllegalAccessException {

        //autoinkrementacja indeksu jednak
        s.setIndex(String.valueOf(s.getCounter_for_id().incrementAndGet()));

        //walidacja
        boolean non_empty_fields = Stream.of(s.getIndex(), s.getFirstName(), s.getLastName(), s.getBirthday())
                .allMatch(Objects::nonNull);
        boolean unique_id = true;
        boolean valid_name = s.getFirstName().matches("[a-zA-Z]+");
        boolean valid_surname = s.getLastName().matches("[a-zA-Z]+");
        //mozna dac ograniczenie na dlugosc indeksu
        boolean valid_index = s.getIndex().matches("[0-9]+");



        //jezeli w kolekcji istnieje juz student z podanym ID
        if (students_repository.getStudent(s.getIndex())!= null) {
            unique_id = false;
        }
        if (non_empty_fields && unique_id && valid_name && valid_surname && valid_index) {
            students_repository.addStudent(s);
            URI location_uri = uriInfo.getAbsolutePathBuilder().path(s.getIndex()).build();
            return Response.created(location_uri)//Response.status(Response.Status.CREATED)
                    .entity(s)
                    .build();

        }
        else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT @Path("/{index}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response updateStudent(@PathParam("index") String index, Student s) {
        s.setIndex(index);
        boolean valid_name = s.getFirstName().matches("[a-zA-Z]+");
        boolean valid_surname = s.getLastName().matches("[a-zA-Z]+");
        //mozna dac ograniczenie na dlugosc indeksu
        boolean valid_index = s.getIndex().matches("[0-9]+");

        //co jezeli pole puste? wywalac blad czy pobrac stare dane?
        if (valid_name && valid_surname && valid_index) {
            students_repository.updateStudent(s);
            return Response.status(Response.Status.NO_CONTENT).entity(s).build();
        }
        else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

    }

    @DELETE @Path("/{index}")
    @Produces(MediaType.APPLICATION_XML)
    public Response deleteStudent(@PathParam("index") String index) {
        var output = students_repository.deleteStudent(index);
        if (output!=null){
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }


    @GET @Path("/{index}/grades")
    @Produces(MediaType.APPLICATION_XML)
    public List<Grade> getGrades(@PathParam("index") String student_index) {
        return grades_repository.getGrades(student_index);
    }

    @GET @Path("/{index}/grades/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getGrade(@PathParam("id") int index) {
        //return grades_repository.getGrade(index);
        Grade grade_to_show =  grades_repository.getGrade(index);
        if (grade_to_show!=null){
            return Response.status(Response.Status.OK).entity(grade_to_show).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    //dopisanie oceny konkretnemu użytkownikowi
    @POST @Path("/{index}/grades")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response addGrade(Grade g, @Context UriInfo uriInfo) {

        //autoinkrementacja indeksu jednak
        g.setId(g.getCounter_for_id().incrementAndGet());

        //walidacja danych -> wszystkie wypełnione
        boolean non_empty_fields = Stream.of(g.getId(), g.getValue(), g.getCourse(), g.getDate(), g.getOwner())
                .allMatch(Objects::nonNull);
        //czy przedmiot istnieje
        boolean course_exists = false;
        //?
        if (courses_repository.getCourse(g.getCourse().getId()) != null)
        {
            course_exists = true;
        }
        //unikalne id oceny
        boolean unique_id = true;
        //jezeli w kolekcji istnieje juz ocena z podanym ID
        if (grades_repository.getGrade(g.getId())!= null) {
            unique_id = false;
        }
        boolean valid_grade = false;
        if (g.getValue() >= 2 && g.getValue()%0.5 == 0 && g.getValue() <= 5){
            valid_grade = true;
        }

        if (non_empty_fields && unique_id && valid_grade && course_exists) {
            grades_repository.addGrade(g);
            //return Response.status(Response.Status.CREATED).entity(g).build();
            URI location_uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(g.getId())).build();
            return Response.created(location_uri)//Response.status(Response.Status.CREATED)
                    .entity(g)
                    .build();
        }
        else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        //ściągnąć już wcześniej info o studencie? w sensie od razu dopisać do oceny, podobnie z ID oceny

    }

    @PUT @Path("/{index}/grades/{id}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response updateGrade(@PathParam("id") int index, Grade g) {
        g.setId(index);
        //też dodac walidacje - wszystkei wypelnione //mozliwe ze wymagana zmiana na: mozliwe puste
        // i uzupelnienie "starymi/dotychczasowymi" danymi, czyli najpierw get z indeksa, dopiero potem get z
        // przekazanego obiektu

//        Grade old_grade_info = grades_repository.getGrade(index); //dane "poprzednie", w g sa nowo przekazane
//        //testowy if
//        if (index == 79) {
//            System.out.println("taktyczny breakpoint");
//            System.out.println(old_grade_info.getId());
//        }

        boolean non_empty_fields = Stream.of(g.getId(), g.getValue(), g.getCourse(), g.getDate(), g.getOwner())
                .allMatch(Objects::nonNull);

        //czy przedmiot istnieje
        boolean course_exists = false;
        if (courses_repository.getCourse(g.getCourse().getId()) != null)
        {
            course_exists = true;
        }

        boolean valid_grade = false;
        if (g.getValue() >= 2 && g.getValue()%0.5 == 0 && g.getValue() <= 5){
            valid_grade = true;
        }


        if (valid_grade && non_empty_fields && course_exists) {
            grades_repository.updateGrade(g);
            return Response.status(Response.Status.NO_CONTENT).entity(g).build();
        }
        else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

    }

    @DELETE @Path("/{index}/grades/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response deleteGrade(@PathParam("id") int index) {
        //grades_repository.deleteGrade(index);
        //return Response.status(Response.Status.OK).build();

        var output = grades_repository.deleteGrade(index);
        if (output!=null){
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}