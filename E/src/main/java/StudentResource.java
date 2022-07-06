import org.glassfish.jersey.server.Uri;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


//@Singleton
@Path("students")
public class StudentResource {

    /*TODO
     *  Dorobic POST, PUT, DELETE  *
     * ,*/

    StudentList students_repository = new StudentList();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> GetStudents() throws ParseException {
        //return Response.status(Response.Status.OK).entity(students_repository.getStudents()).build();
        return students_repository.getStudents();
    }

    @GET @Path("/{index}")
    @Produces(MediaType.APPLICATION_XML)
    public Student GetStudent(@PathParam("index") String index) {
        return students_repository.getStudent(index);
    }

    //TODO
    @GET @Path("/{index}/grades")
    @Produces(MediaType.TEXT_PLAIN)
    public String PrintStudentGrades() {
        return "Certain student grades...";
    }

    //TODO
    @GET @Path("/{index}/grades/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String PrintStudentCertainGrade() {
        return "Certain student certain grade...";
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response addStudent(Student s) {
        students_repository.addStudent(s);
        return Response.status(Response.Status.CREATED).entity(s).build();
        //return s;
    }

    @PUT @Path("/{index}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response updateStudent(@PathParam("index") String index, Student s) {
        s.setIndex(index);
        students_repository.updateStudent(s);
        return Response.status(Response.Status.NO_CONTENT).entity(s).build();
    }
}